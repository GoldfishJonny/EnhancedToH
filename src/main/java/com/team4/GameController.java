package com.team4;


import java.awt.event.*;
/**
 * The controller for the Towers of Hanoi game.
 * It implements MouseListener, MouseMotionListener, and ComponentListener to handle mouse events and resizing.
 * It moves disks when dragged and dropped, and repaints the game when resized.
 * Base code provided by Professor.
 * @Author Jonathan Jara
 *
 */

public class GameController implements MouseListener, MouseMotionListener, ComponentListener {

    private int initialX;
    private int initialY;
    private final ProgressPanel progressPanel;

    public GameController() {
        this.progressPanel = GameData.getInstance().getProgressPanel();
    }

    public void mousePressed(MouseEvent e) {
        for (int i = GameData.getInstance().getNewDisks().size() - 1; i >= 0; i--) {
            NewDisk disk = GameData.getInstance().getNewDisks().get(i);
            if (disk.contains(e.getX(), e.getY())) {
                SoundManager.playPickUpSound();
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

            for (Tower tower : GameData.getInstance().getTowers()) {
                if (tower.contains(e.getX(), e.getY()) && tower.canAddDisk(GameData.getInstance().getSelectedDisk())){
                    Tower oldTower = GameData.getInstance().getSelectedDisk().getTower();
                    oldTower.removeDisk(GameData.getInstance().getSelectedDisk());
                    tower.dropDisk(GameData.getInstance().getSelectedDisk());
                    GameData.getInstance().getSelectedDisk().setTower(tower);
                    GameData.getInstance().setAskedForHelp(null, null);
                    tower.setSelected(true);
                    moved = true;
                    if (GameData.getInstance().getMoves() == 0) {
                        GameData.getInstance().setCounter(1);
                    }
                    GameData.getInstance().iterateMoves();
                    if (GameData.getInstance().getTowers().get(2).getDisksOnTower().size() == GameData.getInstance().getNDisks()) {
                        GameData.getInstance().setGameOver(true);
                        // Call to update the leaderboard if the game is over
                        String formattedTime = GameData.getInstance().getFormattedTime(); // Assuming this method exists
                        LeaderBoardManager.getInstance().addTime(formattedTime);
                    }

                    if (tower != oldTower) {
                        System.out.println("Progress update triggered");
                        progressPanel.updateProgress(GameData.getInstance().getProgress());
                        System.out.println("Current progress: " + GameData.getInstance().getProgress());
                    }

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
            for (Tower tower : GameData.getInstance().getTowers()) {
                tower.setSelected(tower.contains(e.getX(), e.getY()));
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