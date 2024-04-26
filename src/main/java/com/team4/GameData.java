package com.team4;
import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameData extends PropertyChangeSupport {
    private static GameData instance;
    private int windowsWidth;
    private int windowHeight;
    private int nDisks;
    private List<NewDisk> disks;
    private List<Tower> towers;
    private NewDisk selectedDisk = null;
    private int mouseYOffset = 0;
    private Tutor tutor;
    private long bestTime = 0;

    private int counter = 0;
    private GameData(int nDisks) {
        super(new Object());
        this.nDisks = nDisks;
        towers = new ArrayList<>();
        disks = new ArrayList<>();
        tutor = new Tutor();
        this.recalculate();
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

    public void start() {
        firePropertyChange("counter", null, counter);
    }

}
