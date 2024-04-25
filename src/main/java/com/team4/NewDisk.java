package com.team4;
import com.javiergs.Disk;

import java.awt.*;

public class NewDisk extends Disk{
    private int   diskX;
    private int   diskY;
    private int   width;
    private int  height;
    private Color color;
    private int weight;
    private NewTower tower;
    public NewDisk(int x, int y, int width,int height, Color color, int weight) {
        super(x, y, width, color);
        this.diskX =x - width / 2;
        this.diskY = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.weight = weight;
    }

    @Override
    public void draw(Graphics g) {
        if (GameData.getInstance().getSelectedDisk() == this) {
            g.setColor(Color.YELLOW);
            g.fillRect(diskX, diskY, width, height);
            g.setColor(Color.RED);
            g.drawRect(diskX, diskY, width, height);
        } else {
            g.setColor(color);
            g.fillRect(diskX, diskY, width, height);
        }
    }

    public boolean contains(int x, int y) {
        return x >= diskX && x <= diskX + width && y >= diskY && y <= diskY + 20;
    }

    public int getDiskX() { return diskX; }

    public void setDiskX(int diskX) { this.diskX = diskX; }

    public int getDiskY() { return diskY; }

    public void setDiskY(int diskY) { this.diskY = diskY; }

    public int getWidth() { return width; }

    public NewTower getTower() { return tower; }

    public void setTower(NewTower tower) { this.tower = tower; }
}
