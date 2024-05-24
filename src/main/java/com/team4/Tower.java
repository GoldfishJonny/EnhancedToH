package com.team4;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tower {
    private int x;
    private int y;
    private boolean selected;
    private final List<NewDisk> disksOnTower;
    private int id;
    private SoundManager soundManager;

    public Tower(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.disksOnTower = new ArrayList<>();
        this.soundManager = new SoundManager();
    }

    public void dropDisk(NewDisk disk) {
        disk.setDiskX(x - disk.getWidth() / 2);
        disk.setDiskY(y - (disksOnTower.size() + 1) * 20);
        disksOnTower.add(disk);
        soundManager.playDropSound();
        Currency.getInstance().addCoins(10);
    }

    public void removeDisk(NewDisk disk) {
        disksOnTower.remove(disk);
    }

    public void draw(Graphics g) {
        int width = 10;
        if (selected) {
            g.setColor(Color.RED);
            g.fillRect(x - 5, y - 100, width, 100);
            g.setColor(Color.YELLOW);
            g.drawRect(x - 5, y - 100, width, 100);
        } else if (GameData.getInstance().getAskedForHelpT() == this) {
            g.setColor(Color.WHITE);
            g.fillRect(x - 5, y - 100, width, 100);
            g.setColor(Color.BLACK);
            g.drawRect(x - 5, y - 100, width, 100);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(x - 5, y - 100, width, 100);
        }
    }

    public void setSelected(boolean b) {
        selected = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean contains(int x, int y) {
        return Math.abs(x - this.x) <= 5 && Math.abs(y - this.y) <= 100;
    }

    public boolean canAddDisk(NewDisk disk) {
        if (disksOnTower.isEmpty()) {
            return true;
        }
        return disk.getWidth() < disksOnTower.get(disksOnTower.size() - 1).getWidth();
    }

    public int getID() {
        return id;
    }

    public List<NewDisk> getDisksOnTower(){
        return disksOnTower;
    }
    public void setDisksOnTower (NewDisk disk){
        disksOnTower.add(disk);
    }
}
