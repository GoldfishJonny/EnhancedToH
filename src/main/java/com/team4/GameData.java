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
 *
 * Base code provided by Professor.
 * @author Jonathan Jara
 */

public class GameData extends PropertyChangeSupport {
    private static GameData instance;
    private int windowsWidth;
    private int windowHeight;
    private ProgressPanel progressPanel;
    private int nDisks;
    private List<NewDisk> disks;
    private List<Tower> towers;
    private NewDisk selectedDisk = null;
    private int mouseYOffset = 0;
    private Tutor tutor;
    private long bestTime = 0;
    private int counter = 0;

    private int moves = 0;
    private int gameOver = 0;
    private Solver solver;
    private GameData(int nDisks) {
        super(new Object());
        this.nDisks = nDisks;
        towers = new ArrayList<>();
        disks = new ArrayList<>();
        tutor = new Tutor();
        this.recalculate();
        this.solver = new Solver();
    }

    public void recalculate () {
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

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData(0);
        }
        return instance;
    }

    public List<Tower> getNewTowers(){ return towers; }

    public List<NewDisk> getNewDisks(){ return disks; }

    public NewDisk getSelectedDisk() { return selectedDisk; }
    public void setSelectedDisk(NewDisk selectedDisk) { this.selectedDisk = selectedDisk; }
    public void setnDisks(int nDisks) {
        this.nDisks = nDisks;
        this.recalculate();
    }
    public int getnDisks() {
        return nDisks;
    }
    public void setSize(int windowWidth, int windowHeight) {
        this.windowsWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.recalculate();
    }

    public void solveGame(){
        solver.getMoves().clear();
        solver.hanoi(towers.get(0).getID(), towers.get(2).getID(), towers.get(1).getID(), towers.get(0).getDisksOnTower().size());
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

    private int mouseXOffset = 0;

    public int getMouseYOffset() {
        return mouseYOffset;
    }

    public void setMouseYOffset(int mouseYOffset) {
        this.mouseYOffset = mouseYOffset;
    }

    public Tutor getTutor(){
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

    public void iterateMoves(){
        moves++;
        firePropertyChange("moves", null, moves);
        System.out.println("Moves incremented: " + moves);
    }

    public int getMoves(){
        return moves;
    }

    public int getProgress() {
        // Calculate the total number of moves required to solve the Tower of Hanoi puzzle
        int totalMoves = (int) Math.pow(2, getnDisks()) - 1;

        // Calculate the current progress percentage
        double progressPercentage = (double) getMoves() / totalMoves * 100;

        // Ensure the progress percentage is within the range [0, 100]
        return (int) Math.round(progressPercentage);
    }


    public Solver getSolver() {
        return solver;
    }
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

    public void restart() {
        for (Tower tower : towers) {
            tower.getDisksOnTower().clear();
        }
        recalculate();
        solver = new Solver();
        moves = 0;
        counter = 0;
        gameOver = 0;
        firePropertyChange("moves", null, moves);
        firePropertyChange("counter", null, counter);
        firePropertyChange("gameOver", null, 1);
    }
}
