package com.team4;

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
    private ProgressPanel progressPanel;
    private int nDisks;
    private final List<NewDisk> disks;
    private final List<Tower> towers;
    private NewDisk selectedDisk = null;
    private int mouseYOffset = 0;
    private final Tutor tutor;
    private long bestTime = 0;
    private int counter = 0;

    private int moves = 0;
    private Solver solver;
    private NewDisk askedForHelpD = null;
    private Tower askedForHelpT = null;
    private int mouseXOffset = 0;

    private GameData(int nDisks) {
        super(new Object());
        this.nDisks = nDisks;
        towers = new ArrayList<>();
        disks = new ArrayList<>();
        tutor = new Tutor();
        this.recalculate();
        this.solver = new Solver();
    }

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData(0);
        }
        return instance;
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

    public int getnDisks() {
        return nDisks;
    }

    public void setnDisks(int nDisks) {
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
            n[2 - i] = disks.get(i).getTower().getID();
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

//    public void setMoves(int moves) {
//        this.moves = moves;
//    }

    public int getProgress() {
        // Calculate the total number of moves required to solve the Tower of Hanoi puzzle
        int totalMoves = (int) Math.pow(2, getnDisks()) - 1;

        // Calculate the current progress percentage
        double progressPercentage = (double) getMoves() / totalMoves * 100;

        // Ensure the progress percentage is within the range [0, 100]
        return (int) Math.round(progressPercentage);
    }


//    public Solver getSolver() {
//        return solver;
//    }

    public ProgressPanel getProgressPanel() {
        if (progressPanel == null) {
            progressPanel = new ProgressPanel();
        }
        return progressPanel;
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

//    public long getBestTime() {
//        return bestTime;
//    }
//
//    public void setBestTime(long bestTime) {
//        this.bestTime = bestTime;
//    }

    public void restart() {
        for (Tower tower : towers) {
            tower.getDisksOnTower().clear();
        }
        recalculate();
        solver = new Solver();
        moves = 0;
        counter = 0;
        int gameOver = 0;
        this.progressPanel.resetProgress();
        firePropertyChange("moves", null, moves);
        firePropertyChange("counter", null, counter);
        firePropertyChange("gameOver", null, gameOver);
    }
}
