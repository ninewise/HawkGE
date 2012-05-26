
package hawkge.storage.gameloading;

import hawkge.event.EventQueue;
import hawkge.storage.userstats.GameStat;
import hawkge.storage.userstats.events.UserStatRequestEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import hawkge.game.Game;
import hawkge.storage.Storable;
import hawkge.storage.StoreException;
import hawkge.storage.User;
import hawkge.storage.userstats.events.UserStatEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jdom.Element;

public class GameManager extends Storable {

    private static ExecutorService runner = Executors.newSingleThreadExecutor();

    private User user;
    private Map<String, GameStat> map;
    private GameLoader gameLoader;

    public GameManager(User user) {
        this.user = user;
        this.map = new HashMap<String, GameStat>();
        this.gameLoader = new GameLoader();
    }

    private GameManager(User user, Element element) throws StoreException {
        this.user = user;
        this.map = new HashMap<String, GameStat>();
        List<Element> games = element.getChildren("game");
        for(Element e : games) {
            this.map.put(
                    e.getChildText("name"),
                    GameStat.readXML(e.getChild("gamestat"))
            );
        }
        this.gameLoader = new GameLoader();
    }

    public static File getGameRoot() {
        File gameRoot = new File(getStorageRoot(), "games");
        if(!gameRoot.exists()) gameRoot.mkdir();
        return gameRoot;
    }

    public void addGame(final String name) {
        final GameStat stat = new GameStat();
        final GameStat put = map.put(name, stat);
        if(put != null) throw new RuntimeException("Game exists yet: " + name);
    }

    public void addGame(final File game) {
        String subname = game.getName().substring(0, game.getName().length() - 4);
        File copy = getGameLocation(subname);
        copy(game, copy);
        addGame(subname);
    }

    public String testGame(final File file) {
        String name = file.getName();
        String subname = name.substring(0, name.length() - 4);
        if(!name.endsWith(".jar")) return "File is not a jar.";
        if(this.hasGame(subname)) {
            return "Game already exists.";
        }
        gameLoader.addFile(file);
        Class<? extends Game> gameClass = null;
        try {
            gameClass = gameLoader.load(subname);
        } catch (ClassNotFoundException ex) {
            return "This jar does not contain the " + subname + " class.";
        }
        Constructor<? extends Game> constructor = null;
        try {
            constructor = gameClass.getConstructor();
        } catch (NoSuchMethodException ex) {
            return "Unable to load " + subname + ": No empty constructor found.";
        } catch (SecurityException ex) {
            return "Unable to load the Game due to inadequate permissions.";
        }
        Game game = null;
        try {
            game = constructor.newInstance();
        } catch (InstantiationException ex) {
            return "Unable to instantiate Game: Is "+subname+" an abstract class?";
        } catch (IllegalAccessException ex) {
            return "Unable to instantiate Game: It would seem we don't have acces"
                    + "to the constructor.";
        } catch (IllegalArgumentException ex) {
            return "Unable to instantiate Game: Not an empty constructor.";
        } catch (InvocationTargetException ex) {
            return "Unable to instantiate Game: Throwing exceptions in your"
                    + "constructor. Seriously?";
        }
        if(game == null) return "Game not instantiated for unknown reason. What?";
        if(!game.getClassName().equals(subname)) return "Your method \"getClassName()\" does not return the class name.";
        return null;
    }

    public void removeGame(final String name) {
        map.remove(name);
        getGameLocation(name).delete();
    }

    public boolean hasGame(String name) {
        return map.containsKey(name);
    }

    public Collection<Game> getGames() {
        Collection<Game> games = new HashSet<Game>();
        for(String game : map.keySet()) {
            Class<? extends Game> gameClass = getGameClass(game);
            try {
                games.add(gameClass.getConstructor().newInstance());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return games;
    }

    public GameStat getGameStat(String name) {
        return map.get(name);
    }

    public Class<? extends Game> getGameClass(String name) {
        if(!map.containsKey(name)) return null;
        gameLoader.addFile(new File(getGameRoot(), name + ".jar"));
        try {
            return gameLoader.load(name);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public File getLocation() {
        return new File(getStorageRoot(), "gamemanager_" + user.getName() + ".hgedata");
    }

    public File getGameLocation(String game) {
        File userRoot = new File(getGameRoot(), user.getName());
        if(!userRoot.exists()) userRoot.mkdir();
        return new File(userRoot, game + ".jar");
    }

    @Override
    public Element getXML() {
        Element e = new Element("gamemanager");
        for(String game : map.keySet()) {
            e.addContent(new Element("game")
                    .addContent(new Element("name").addContent(game))
                    .addContent(map.get(game).getXML())
            );
        }
        return e;
    }

    /**
     * Parses <code>element</code> to create a GameManager out of it.
     * @param element A Xml element representing a GameManager.
     * @return The GameManager represented by <code>element</code>
     * @throws StoreException If <code>element</code> does not represent a
     * GameManager.
     */
    public static GameManager readXML(Element element, User user) throws StoreException {
        if(element == null || ! "gamemanager".equals(element.getName())) {
            throw new StoreException("Failed parsing element as GameManager.");
        }
        GameManager gameManager = new GameManager(user, element);
        return gameManager;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameManager other = (GameManager) obj;
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.user != null ? this.user.hashCode() : 0);
        return hash;
    }

    public void sendStats(UserStatRequestEvent userStatRequestEvent) {
        EventQueue.queue(new UserStatEvent(
                userStatRequestEvent.getRequestor(),
                userStatRequestEvent.getAbout(),
                map
        ));
    }

    public String[] getNames() {
        Iterator<String> set = map.keySet().iterator();
        String[] ret = new String[map.size()];
        for(int i = 0; i < ret.length; i++) ret[i] = set.next();
        return ret;
    }

    /**
     * Copy the file.
     * @param from
     * @param to
     */
    private void copy(File from, File to) {
        InputStream istream = null;
        try {
            istream = new FileInputStream(from);
        } catch (FileNotFoundException ex) {
            // Won't happen, we checked earlier.
            ex.printStackTrace();
        }
        OutputStream ostream = null;
        try {
            ostream = new FileOutputStream(to);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            // TODO warn user;
        }
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = istream.read(buffer)) > 0) {
                ostream.write(buffer, 0, len);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            // Shouldnt happen.
            // TODO warn user.
        }
        try {
            istream.close();
            ostream.close();
        } catch (IOException ex) {
            // Dammit.
            // TODO warn user.
            ex.printStackTrace();
        }
    }
}
