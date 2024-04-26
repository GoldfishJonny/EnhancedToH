package com.javiergs;
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
 * @author Professor
 */
public class GameData extends PropertyChangeSupport {

  private static GameData instance;

  private int windowsWidth;
  private int windowHeight;
  private int nDisks;
  private List<Disk> disks;
  private List<Tower> towers;
  private Disk selectedDisk = null;
  private int mouseYOffset = 0;

  protected GameData(int nDisks) {
    super(new Object());
    this.nDisks = nDisks;
    towers = new ArrayList<>();
    disks = new ArrayList<>();
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
      towers.add(new Tower(x, y, towerWidth));
    }
    Random random = new Random();
    for (int i = 0; i < nDisks; i++) {
      Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
      int diskWidth = (towerWidth - 20) * (nDisks - i) / nDisks;
      int x = towers.get(0).getX();
      int y = towers.get(0).getY() - 20 * (i + 1);
      disks.add(new Disk(x, y, diskWidth, color));
    }
  }

  public static GameData getInstance() {
    if (instance == null) {
      instance = new GameData(0);
    }
    return instance;
  }

  public void setSize(int windowWidth, int windowHeight) {
    this.windowsWidth = windowWidth;
    this.windowHeight = windowHeight;
    this.recalculate();

  }

  public void setnDisks(int nDisks) {
    this.nDisks = nDisks;
    this.recalculate();

  }

  public List<Disk> getDisks() {
    return disks;
  }
  public Disk getSelectedDisk() {
    return selectedDisk;
  }

  public void setSelectedDisk(Disk selectedDisk) {
    this.selectedDisk = selectedDisk;
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

  public List<Tower> getTowers() {
    return towers;
  }

  public void repaint() {
    firePropertyChange("repaint", null, null);
  }
}
