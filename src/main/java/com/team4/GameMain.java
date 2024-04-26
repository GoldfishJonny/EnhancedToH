package com.team4;
import javax.swing.*;
import java.awt.*;

/**
 * The main class for the Towers of Hanoi game.
 * It extends creates a new TowerPanel object to display the game.
 * Define number of disks and window size.
 *
 * @author Jonathan Jara
 */

 public class GameMain extends JFrame {
    private MenuPanel menuPanel = new MenuPanel();
    private NavbarPanel navbarPanel;
    private TowerPanel towerPanel;
    private TutorPanel tutorPanel;
    private TimeTrialPanel timeTrial = new TimeTrialPanel(new TimeTrial());
    private ProgressPanel progressPanel;

    public GameMain(){
        this.setBackground(Color.WHITE);
        navbarPanel = new NavbarPanel();
        towerPanel = new TowerPanel();
        tutorPanel = new TutorPanel();
        progressPanel = new ProgressPanel();

        setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.CENTER);

        GameController controller = new GameController();
        towerPanel.addMouseListener(controller);
        towerPanel.addMouseMotionListener(controller);
        towerPanel.addComponentListener(controller);
        GameData.getInstance().setnDisks(3);
        GameData.getInstance().setSize(getWidth(), getHeight());
        GameData.getInstance().addPropertyChangeListener(towerPanel);
    }
        public static void main(String[] args) {
            GameMain main = new GameMain();
            main.setTitle("Towers of Hanoi");
            main.setSize(800, 400);
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main.setVisible(true);
        }


    public void startGame() {
        // Remove main menu panel
        remove(menuPanel);

        JPanel rightPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        this.setLayout(new BorderLayout());
        bottomPanel.setLayout(new GridLayout(1,2));
        rightPanel.setLayout(new GridLayout(2,1));
        leftPanel.setLayout(new GridLayout(2,1));
        towerPanel.setBackground(Color.decode("#EFF7F6"));
        leftPanel.setBackground(Color.decode("#EFF7F6"));
        leftPanel.add(towerPanel);
        leftPanel.add(timeTrial);

        rightPanel.add(tutorPanel);
        bottomPanel.add(leftPanel);
        bottomPanel.add(rightPanel);
        this.add(navbarPanel);
        this.add(bottomPanel);

        GameController controller = new GameController();
        towerPanel.addMouseListener(controller);
        towerPanel.addMouseMotionListener(controller);
        towerPanel.addComponentListener(controller);

        GameData.getInstance().setnDisks(3);
        GameData.getInstance().setSize(this.getWidth(), this.getHeight());
        GameData.getInstance().addPropertyChangeListener(towerPanel);

        ProgressPanel progressPanel = GameData.getInstance().getProgressPanel();
        this.add(progressPanel, BorderLayout.NORTH);
        System.out.println("ProgressPanel added to GameMain");

        // Revalidate and repaint
        revalidate();
        repaint();
    }

    public void other(){
        System.out.println("Other gamemode");
    }

    public void help(){
        // Create the frame
        JFrame frame = new JFrame("Towers of Hanoi Instructions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 300);

        // Create the panel
        JPanel panel = new JPanel();

        // Create a text area to display instructions
        JTextArea textArea = new JTextArea(20, 90);
        textArea.setText("How to Play Towers of Hanoi:\n\n"
                + "1. You have three rods and a number of disks of different sizes which can slide onto any rod.\n"
                + "2. The puzzle starts with the disks neatly stacked in ascending order of size on one rod, the smallest at the top, thus making a conical shape.\n"
                + "3. The objective of the puzzle is to move the entire stack to another rod, obeying the following rules:\n"
                + "   a. Only one disk can be moved at a time.\n"
                + "   b. Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack or on an empty rod.\n"
                + "   c. No disk may be placed on top of a smaller disk.\n"
                + "4. The minimum number of moves required to solve a Tower of Hanoi puzzle is 2^n - 1, where n is the number of disks.");
        textArea.setEditable(false);
        textArea.setFont(new Font("SansSerif", Font.BOLD, 15));

        // Wrap the text area in a scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add the scroll pane to the panel
        panel.add(scrollPane);

        // Add the panel to the frame
        frame.add(panel);

        // Set the frame's visibility to true to show it
        frame.setVisible(true);
    }
 }