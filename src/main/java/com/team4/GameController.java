package com.team4;


import java.awt.event.*;

public class GameController implements MouseListener, MouseMotionListener, ComponentListener {

    private int initialX;
    private int initialY;

    public void mousePressed(MouseEvent e) {
        for (int i = GameData.getInstance().getNewDisks().size() - 1; i >= 0; i--) {
            NewDisk disk = GameData.getInstance().getNewDisks().get(i);
            if (disk.contains(e.getX(), e.getY())) {
                GameData.getInstance().setSelectedDisk(disk);
                GameData.getInstance().setMouseXOffset( e.getX() - disk.getDiskX());
                GameData.getInstance().setMouseYOffset( e.getY() - disk.getDiskY());
                initialX = disk.getDiskX();
                initialY = disk.getDiskY();
                System.out.println(initialY);
                break;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        boolean moved = false;
        if (GameData.getInstance().getSelectedDisk() != null) {

            for (NewTower tower : GameData.getInstance().getNewTowers()) {
                if (tower.contains(e.getX(), e.getY()) && tower.canAddDisk(GameData.getInstance().getSelectedDisk())){
                    NewTower oldTower = GameData.getInstance().getSelectedDisk().getTower();
                    oldTower.removeDisk(GameData.getInstance().getSelectedDisk());
                    tower.dropDisk(GameData.getInstance().getSelectedDisk());
                    GameData.getInstance().getSelectedDisk().setTower(tower);
                    tower.setSelected(true);
                    moved = true;
                }
                else {
                    System.out.println(initialY);
                    tower.setSelected(false);
                }
            }

            if (!moved) {
                GameData.getInstance().getSelectedDisk().setDiskX(initialX);
                GameData.getInstance().getSelectedDisk().setDiskY(initialY);
            }
            GameData.getInstance().setSelectedDisk(null);
            GameData.getInstance().repaint();
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (GameData.getInstance().getSelectedDisk() != null) {
            for (NewTower tower : GameData.getInstance().getNewTowers()) {
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