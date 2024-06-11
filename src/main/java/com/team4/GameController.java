package com.team4;


import org.json.JSONObject;

import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public GameController() {
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
            Tower tower = GameData.getInstance().findNearestTower(e.getX());
//            for (Tower tower : GameData.getInstance().getTowers()) {
                if (tower.canAddDisk(GameData.getInstance().getSelectedDisk())){
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
                        //LeaderBoardManager.getInstance().notifyObservers(); //adds time to leaderboard when game ends
                        /// ^ Didnt work lmaoooo

                        // DELETE FILES FOR PY SCRIPT
                        Path path = Paths.get("nDisks.txt");
                        try {
                            boolean deleted = Files.deleteIfExists(path);
                            if (deleted) {
                                System.out.println("File deleted successfully");
                            } else {
                                System.out.println("File not found, not deleted");
                            }
                        } catch (IOException a) {
                            System.out.println("An error occurred while trying to delete the file.");
                            a.printStackTrace();
                        }
                        //

                        //When game is complete it updates the data.json file
                        JSONObject data = new JSONObject();
                        GameData.getInstance().getProcessData().saveData(data);

                        // Exports info from data.json to mongodb
                        QuickStart qs = new QuickStart();
                        qs.loadDataFromFile("Users/data.json");
                        qs.close();
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

    public void mouseDragged(MouseEvent e) {
        if (GameData.getInstance().getSelectedDisk() != null) {
            Tower tower = GameData.getInstance().findNearestTower(e.getX());
            for (Tower t : GameData.getInstance().getTowers()) {
                t.setSelected(false);
            }
            tower.setSelected(true);
//            for (Tower tower : GameData.getInstance().getTowers()) {
////                tower.setSelected(tower.contains(e.getX(), e.getY()));
//            }
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