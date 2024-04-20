package com.team4;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tower in the Towers of Hanoi game.
 * It can be drawn, checked if it contains a point, and drop a disk on the tower.
 *
 * @author Professor
 */
class Tower {

  private int x;
  private int y;
  private boolean selected;
  private final List<Disk> disksOnTower;

  public Tower(int x, int y, int width) {
    this.x = x;
    this.y = y;
    this.disksOnTower = new ArrayList<>();
  }

  public void dropDisk(Disk disk) {
    disk.setDiskX(x - disk.getWidth() / 2);
    disk.setDiskY(y - (disksOnTower.size() + 1) * 20);
    disksOnTower.add(disk);
  }

  public void draw(Graphics g) {
    int width = 10;
    if (selected) {
      g.setColor(Color.RED);
      g.fillRect(x - 5, y - 100, width, 100);
      g.setColor(Color.YELLOW);
      g.drawRect(x - 5, y - 100, width, 100);
    } else {
      g.setColor(Color.BLACK);
      g.fillRect(x - 5, y - 100, width, 100);
    }
  }

  public boolean contains(int x, int y) {
    return Math.abs(x - this.x) <= 5 && Math.abs(y - this.y) <= 100;
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

}
