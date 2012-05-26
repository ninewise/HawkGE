
package hawkge.storage.userstats;

import hawkge.storage.Storable;
import hawkge.storage.StoreException;
import hawkge.storage.userstats.gui.GameStatsFrame;
import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.SwingUtilities;
import org.jdom.Element;

public class GameStat extends Storable implements Serializable {

    private int won;
    private int lost;
    private int left;

    private int highscore;
    private int totalScore;

    private long lastPlayed;

    private double hours;

    public GameStat() {
        this.won = 0;
        this.lost = 0;
        this.left = 0;
        this.lastPlayed = System.currentTimeMillis();
        this.hours = 0.0;
        this.totalScore = 0;
        this.highscore = 0;
    }

    public GameStat(Element element) throws StoreException {
        if(element == null || ! "gamestat".equals(element.getName())) {
            throw new StoreException("Element not a gamestat.");
        }
        this.won = Integer.parseInt(element.getChildText("won"));
        this.lost = Integer.parseInt(element.getChildText("lost"));
        this.left = Integer.parseInt(element.getChildText("left"));
        this.hours = Double.parseDouble(element.getChildText("hours"));
        this.lastPlayed = Long.parseLong(element.getChildText("played"));
        this.highscore = Integer.parseInt(element.getChildText("high"));
        this.totalScore = Integer.parseInt(element.getChildText("total"));
    }

    public int getGamesWon() { return won; }
    public int getGamesLost() { return lost; }
    public int getGamesLeft() { return left; }

    public void incrementGamesWon() { won++; }
    public void incrementGamesLost() { lost++; }
    public void incrementGamesLeft() { left++; }

    public int getHighscore() { return highscore; }
    public int getTotalScore() { return totalScore; }

    public void testHighScore(int score) { if(score > highscore) highscore = score; }
    public void increaseTotalScore(int score) { totalScore += score; }

    public void updateLastPlayed() { lastPlayed = System.currentTimeMillis(); }
    public void increaseHoursPlayed(double increment) { hours += increment; }

    public String getLastPlayed() {
        return DateFormat.getDateInstance().format(new Date(lastPlayed));
    }

    public double getHoursPlayed() { return hours; }

    /**
     * Shows the Game statistics. This can be implemented either by GUI or by
     * printing to standard out.
     */
    public void showStats() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameStatsFrame(GameStat.this);
            }
        });
    }

    @Override
    public File getLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Element getXML() {
        Element element = new Element("gamestat");
        element.addContent(new Element("won").addContent(""+won));
        element.addContent(new Element("lost").addContent(""+lost));
        element.addContent(new Element("left").addContent(""+left));
        element.addContent(new Element("played").addContent(""+lastPlayed));
        element.addContent(new Element("hours").addContent(""+hours));
        element.addContent(new Element("high").addContent(""+highscore));
        element.addContent(new Element("total").addContent(""+totalScore));
        return element;
    }

    public static GameStat readXML(Element e) throws StoreException {
        return new GameStat(e);
    }
}
