package engine;

import javax.swing.*;
import java.io.*;

/**
 * Created by Leo on 25-Dec-15.
 */
public class Player implements Serializable
{
    private String username = "user";
    private String password = "1234";
    private String folderPath;
    private String filePath = "";
    private File folder;
    private File file;

    /* Saved game data */
    private int racesWon = 0;
    private int racesLost = 0;
    private int deaths = 0;
    private int score = 0;
    private long time = 0;

    /* Temporary game data */
    private int currentScore = 0;
    private long currentTime = 0;

    public void died()
    {
        this.deaths++;
    }

    public void wonRace()
    {
        this.racesWon++;
    }

    public void lostRace()
    {
        this.racesLost++;
    }

    public void addScoreInRace(int score)
    {
        currentScore += score;
    }

    public void addScore()
    {
        score += currentScore;
        currentScore = 0;
    }

    public void addTimeInRace(int time)
    {
        currentTime += time;
    }

    public void addTime()
    {
        time += (currentTime) / 1000;
        currentTime = 0;
    }

    public static boolean isUser(String username)
    {
        String filePath = new JFileChooser().getFileSystemView().getDefaultDirectory().toPath().toAbsolutePath().toString() + "\\Traffic Racer\\" + username + ".trsf";

        return new File(filePath).exists();
    }

    public static boolean isValid(String username, String password)
    {
        Player a = Player.readFromFile(username);

        if (a.password.contentEquals(password))
            return true;
        else
            return false;
    }

    public Player(String username, String password)
    {
        this.username = username;
        this.password = password;

        this.folderPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toPath().toAbsolutePath().toString() + "\\Traffic Racer";
        this.filePath = new JFileChooser().getFileSystemView().getDefaultDirectory().toPath().toAbsolutePath().toString() + "\\Traffic Racer\\" + username + ".trsf";

        folder = new File(folderPath);
        file = new File(filePath);

        if (!folder.exists())
            folder.mkdir();
    }

    private void copyFrom(Player other)
    {
        this.username = other.username;
        this.password = other.password;

        this.folderPath = other.folderPath;
        this.filePath = other.filePath;
        this.folder = other.folder;
        this.file = other.file;
        this.racesWon = other.racesWon;
        this.racesLost = other.racesLost;
        this.deaths = other.deaths;
        this.score = other.score;
        this.time = other.time;
        this.currentScore = 0;
        this.currentTime = 0;
    }

    public void saveToFile()
    {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Player data was saved!");
    }

    public void readFromFile()
    {
        this.copyFrom(Player.readFromFile(this.username));

        System.out.println("Player data was read!");
    }

    public static Player readFromFile(String username)
    {
        String filePath = new JFileChooser().getFileSystemView().getDefaultDirectory().toPath().toAbsolutePath().toString() + "\\Traffic Racer\\" + username + ".trsf";
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        Player tempPlayer = null;

        try {
            fileInputStream = new FileInputStream(filePath);
            objectInputStream = new ObjectInputStream(fileInputStream);
            tempPlayer = (Player) objectInputStream.readObject();
            objectInputStream.close();
            return tempPlayer;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }


    public int getScore() {
        return score;
    }

    public long getTime() {
        return time;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRacesLost() {
        return racesLost;
    }

    public int getRacesWon() {
        return racesWon;
    }
}
