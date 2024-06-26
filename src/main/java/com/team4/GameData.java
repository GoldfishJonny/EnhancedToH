package com.team4;

import org.json.JSONArray;
import javax.swing.*;
import java.util.Timer;
import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Game data for the Towers of Hanoi game.
 * It contains the disks and towers, and recalculate their positions.
 * It extends PropertyChangeSupport to notify observers of changes in the game data.
 * <p>
 * Base code provided by Professor.
 *
 * @author Jonathan Jara
 */

public class GameData extends PropertyChangeSupport {
    private static GameData instance;
    private int windowsWidth;
    private int windowHeight;
    private int nDisks;
    private final List<NewDisk> disks;
    private final List<Tower> towers;
    private NewDisk selectedDisk = null;
    private int mouseYOffset = 0;
    private final Tutor tutor;
    private long bestTime = 0;
    private int counter = 0;

    private Currency currency;
    private CoinPanel coinPanel;

    private int moves = 0;
    private Solver solver;
    private NewDisk askedForHelpD = null;
    private Tower askedForHelpT = null;
    private int mouseXOffset = 0;

    private final ProcessData processData;
    private JFrame frame;
    private JPanel scene;

    private long bestTimeStopwatch = 0;
    private long bestTimeTimer = 0;

    private JLabel bestTimeLabel;
    private JLabel timeLabel;
    private boolean running;
    private Mode mode;
    private Timer timer;

    private int elapsedTime;

    private int selectedTime;

    private List<Long> topScoresTimer;
    private List<Long> topScoresStopwatch;

    private String username = "Player";
    private GameData(int nDisks) {
        super(new Object());
        this.nDisks = nDisks;
        towers = new ArrayList<>();
        disks = new ArrayList<>();
        tutor = new Tutor();
        this.recalculate();
        this.solver = new Solver();
        this.processData = new ProcessData();
        this.running = false;
        this.topScoresTimer = new ArrayList<>();
        this.topScoresStopwatch = new ArrayList<>();
    }

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData(0);
        }
        return instance;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void recalculate() {
        if (nDisks == 0 || windowsWidth == 0 || windowHeight == 0) {
            return;
        }
        towers.clear();
        disks.clear();
        int towerWidth = windowsWidth / 4 - 20;
        for (int i = 0; i < 3; i++) {
            int x = windowsWidth / 4 * (i + 1);
            int y = windowHeight - 50;
            towers.add(new Tower(x, y, i));
        }
        Random random = new Random();
        for (int i = 0; i < nDisks; i++) {
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            int diskWidth = (towerWidth - 20) * (nDisks - i) / nDisks;
            int x = towers.get(0).getX();
            int y = towers.get(0).getY() - 20 * (i + 1);
            disks.add(new NewDisk(x, y, diskWidth, 20, color, i));
            disks.get(i).setTower(towers.get(0));
            towers.get(0).setDisksOnTower(disks.get(i));
        }
        repaint();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setTopScoresTimer(JSONArray topScoresTimer) {
        for (int i = 0; i < topScoresTimer.length(); i++) {
            this.topScoresTimer.add(topScoresTimer.getLong(i));
        }
    }

    public List<Long> getTopScoresTimer() {
        return topScoresTimer;
    }

    public void addTopScoreTimer(long score) {
        if (topScoresTimer.size() < 5) {
            topScoresTimer.add(score);
        } else {
            for (int i = 0; i < topScoresTimer.size(); i++) {
                if (score < topScoresTimer.get(i)) {
                    topScoresTimer.set(i, score);
                    break;
                }
            }
        }
    }

    public void addTopScoreStopwatch(long score) {
        if (topScoresStopwatch.size() < 5) {
            topScoresStopwatch.add(score);
        } else {
            for (int i = 0; i < topScoresStopwatch.size(); i++) {
                if (score < topScoresStopwatch.get(i)) {
                    topScoresStopwatch.set(i, score);
                    break;
                }
            }
        }
    }

    public void setTopScoresStopwatch(JSONArray topScoresStopwatch) {
        for (int i = 0; i < topScoresStopwatch.length(); i++) {
            this.topScoresStopwatch.add(topScoresStopwatch.getLong(i));
        }
    }

    public List<Long> getTopScoresStopwatch() {
        return topScoresStopwatch;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public List<NewDisk> getNewDisks() {
        return disks;
    }

    public NewDisk getSelectedDisk() {
        return selectedDisk;
    }

    public void setSelectedDisk(NewDisk selectedDisk) {
        this.selectedDisk = selectedDisk;
    }

    public int getNDisks() {
        return nDisks;
    }

    public void setNDisks(int nDisks) {
        this.nDisks = nDisks;
        this.recalculate();
    }

    public void setSize(int windowWidth, int windowHeight) {
        this.windowsWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.recalculate();
    }

    public List<int[]> solveGame() {
        int[] n = getDisks();
        return solver.solve(n, nDisks);
    }

    public int[] getDisks() {
        int[] n = new int[nDisks];
        for (int i = 0; i < nDisks; i++) {
            n[getNDisks()-1 - i] = disks.get(i).getTower().getID();
        }
        return n;
    }

    public void repaint() {
        firePropertyChange("repaint", null, null);
    }

    public int getMouseXOffset() {
        return mouseXOffset;
    }

    public void setMouseXOffset(int mouseXOffset) {
        this.mouseXOffset = mouseXOffset;
    }

    public int getMouseYOffset() {
        return mouseYOffset;
    }

    public void setMouseYOffset(int mouseYOffset) {
        this.mouseYOffset = mouseYOffset;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public String formatElapsedTime(long bestTime) {
        long minutes = bestTime / 60000;
        long seconds = (bestTime % 60000) / 1000;
        long milliseconds = bestTime % 1000;
        System.out.println(minutes + " " + seconds + " " + milliseconds);
        return String.format("%02d:%02d:%03d", minutes, seconds, milliseconds);
    }

    public void changeBestTime(long time) {
        if (bestTime == 0 || time < bestTime) {
            bestTime = time;
            firePropertyChange("bestTime", null, bestTime);
        }
    }

    public void setCounter(int counter) {
        this.counter += counter;
        firePropertyChange("counter", null, counter);
    }

    public void iterateMoves() {
        moves++;
        firePropertyChange("moves", null, moves);
        System.out.println("Moves incremented: " + moves);
    }

    public int getMoves() {
        return moves;
    }

    public ProcessData getProcessData() {
        return processData;
    }
    public void setGameOver(boolean b) {
        if (b) {
            firePropertyChange("gameOver", null, 1);
        }
    }

    public void setAskedForHelp(NewDisk b, Tower c) {
        askedForHelpD = b;
        askedForHelpT = c;
        firePropertyChange("askedForHelp", null, askedForHelpD);
        firePropertyChange("askedForHelpTower", null, askedForHelpT);
    }

    public NewDisk getAskedForHelpD() {
        return askedForHelpD;
    }

    public Tower getAskedForHelpT() {
        return askedForHelpT;
    }

    public long getBestTime() {
        return bestTime;
    }

    public void setBestTime(long bestTime) {
        this.bestTime = bestTime;
        firePropertyChange("bestTime", null, bestTime);
    }

    public void restart() {
        for (Tower tower : towers) {
            tower.getDisksOnTower().clear();
        }
        recalculate();
        solver = new Solver();
        moves = 0;
        counter = 0;
        int gameOver = 0;
        firePropertyChange("moves", null, moves);
        firePropertyChange("counter", null, counter);
        firePropertyChange("gameOver", null, gameOver);
        LeaderBoardManager.getInstance().notifyObservers();
        //QuickStart qs = new QuickStart();
        //qs.loadDataFromFile("Users/data.json");
        //qs.readAllDocuments();
        //qs.close();
    }

    public JPanel switchScene(Scene scene) {
        JPanel scenePanel = null;
        switch (scene) {
            case MENU:
                scenePanel = new MenuScene();
                break;
            case TIMER:
                enableTimerMode();
                scenePanel = new LevelScene();
                break;
            case STOPWATCH:
                elapsedTime = 0;
                selectedTime = 0;
                enableStopwatchMode();
                scenePanel = new LevelScene();
                break;
            case MODE:
                break;
            case HELP:
                scenePanel = new HelpPanel();
                break;
            case GAME:
                scenePanel = new GameScene();
                break;
            default:
                break;
        }
        this.scene = scenePanel;
        return scenePanel;
    }

    public void reloadScene(Scene scene) {
        this.frame.remove(this.scene);
        JPanel Scene = this.switchScene(scene);
        this.frame.add(Scene, BorderLayout.CENTER);
        this.frame.revalidate();
        this.frame.repaint();
    }
    public void setBestTimeTimer(long bestTimeTimer) {
        this.bestTimeTimer = bestTimeTimer;
    }

    public long getBestTimeTimer() {
        return bestTimeTimer;
    }

    public void setBestTimeLabel(JLabel bestTimeLabel) {
        this.bestTimeLabel = bestTimeLabel;
    }

    public JLabel getBestTimeLabel() {
        return bestTimeLabel;
    }

    public boolean getRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void enableTimerMode() {
        this.timer = new Timer();
        this.mode = new TimerMode();
    }

    public void enableStopwatchMode() {
        this.timer = new Timer();
        this.mode = new StopwatchMode();
    }

    public Mode getMode() {
        return mode;
    }

//    // Getter for backdrop image
//    public String getBackdropImage() {
//        return backdropImage;
//    }
//
//    // Setter for backdrop image
//    public void setBackdropImage(String backdropImage) {
//        this.backdropImage = backdropImage;
//    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public void resetElapsedTime() {
        this.timer = new Timer();
        this.elapsedTime = this.selectedTime;
    }

    public void setSelectedTime(int selectedTime) {
        this.elapsedTime = selectedTime;
        this.selectedTime = selectedTime;
    }

    public String getFormattedTime() {
        long elapsedTimeMillis = getElapsedTime();
        long minutes = elapsedTimeMillis / 60000;
        long seconds = (elapsedTimeMillis / 1000) % 60;
        long milliseconds = elapsedTimeMillis % 1000;

        return String.format("%02d:%02d:%03d", minutes, seconds, milliseconds);
    }

    public int getSelectedTime() {
        return selectedTime;
    }

    public Tower findNearestTower(int mouseX) {
        List<Tower> towers  = getTowers();
        Tower nearestTower = null;
        int minDistance = Integer.MAX_VALUE;

        for (Tower tower : towers) {
            int distance = Math.abs(tower.getX() - mouseX);
            if (distance < minDistance) {
                minDistance = distance;
                nearestTower = tower;
            }
        }

        return nearestTower;
    }
}