package com.javiergs;
import java.awt.event.*;

/**
 * The controller for the Towers of Hanoi game.
 * It implements MouseListener, MouseMotionListener, and ComponentListener to handle mouse events and resizing.
 * It moves disks when dragged and dropped, and repaints the game when resized.
 *
 * @author Professor
 */
public class GameController implements MouseListener, MouseMotionListener, ComponentListener {

  private int initialX;
  private int initialY;

  public void mousePressed(MouseEvent e) {
    for (int i = GameData.getInstance().getDisks().size() - 1; i >= 0; i--) {
      Disk disk = GameData.getInstance().getDisks().get(i);
      if (disk.contains(e.getX(), e.getY())) {
        GameData.getInstance().setSelectedDisk(disk);
        GameData.getInstance().setMouseXOffset( e.getX() - disk.getDiskX());
        GameData.getInstance().setMouseYOffset( e.getY() - disk.getDiskY());
        initialX = disk.getDiskX();
        initialY = disk.getDiskY();
        break;
      }
    }
  }

  public void mouseReleased(MouseEvent e) {
    boolean moved = false;
    if (GameData.getInstance().getSelectedDisk() != null) {

      for (Tower tower : GameData.getInstance().getTowers()) {
        if (tower.contains(e.getX(), e.getY())) {
          tower.dropDisk(GameData.getInstance().getSelectedDisk());
          tower.setSelected(true);
          moved = true;
        } else {
          tower.setSelected(false);
        }
      }

      if (moved == false) {
        GameData.getInstance().getSelectedDisk().setDiskX(initialX);
        GameData.getInstance().getSelectedDisk().setDiskY(initialY);
      }
      GameData.getInstance().setSelectedDisk(null);
      GameData.getInstance().repaint();
    }
  }

  public void mouseDragged(MouseEvent e) {
    if (GameData.getInstance().getSelectedDisk() != null) {
      for (Tower tower : GameData.getInstance().getTowers()) {
        if (tower.contains(e.getX(), e.getY())) {
          tower.setSelected(true);
        } else {
          tower.setSelected(false);
        }
      }
      GameData.getInstance().getSelectedDisk().setDiskX(e.getX() - GameData.getInstance().getMouseXOffset());
      GameData.getInstance().getSelectedDisk().setDiskY(e.getY() - GameData.getInstance().getMouseYOffset());
      GameData.getInstance().repaint();
    }
  }

  public void componentResized(ComponentEvent e) {
    // Handle resizing event
    System.out.println("Panel resized to: " + e.getComponent().getSize());
    GameData.getInstance().setSize(e.getComponent().getWidth(), e.getComponent().getHeight());
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  }

  @Override
  public void componentMoved(ComponentEvent e) {
  }

  @Override
  public void componentShown(ComponentEvent e) {
  }

  @Override
  public void componentHidden(ComponentEvent e) {
  }
}
