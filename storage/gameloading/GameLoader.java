/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hawkge.storage.gameloading;

import hawkge.game.Game;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felix
 */
public class GameLoader {

    public void addURL(URL u) {
        URLClassLoader sysloader = (URLClassLoader) GameLoader.class.getClassLoader();
        Class sysclass = URLClassLoader.class;

        try {
            Method method = sysclass.getDeclaredMethod("addURL", new Class[]{URL.class});
            method.setAccessible(true);
            method.invoke(sysloader, new Object[]{u});
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void addFile(File f) {
        try { addURL(f.toURI().toURL()); }
        catch (MalformedURLException e) { e.printStackTrace(); }
    }

    public Class<? extends Game> load(String name) throws ClassNotFoundException {
        ClassLoader cl = GameLoader.class.getClassLoader();
        Class<?> c = cl.loadClass(name);
        Class<? extends Game> game = c.asSubclass(Game.class);
        return game;
    }

    public static void main(String[] args) {
        GameLoader loader = new GameLoader();
        loader.addFile(new File(GameManager.getGameRoot(), "Dammen.jar"));
        Class<? extends Game> g = null;
        try {
            g = loader.load("Dammen");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        try {
            Game game = g.getConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
