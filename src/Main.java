import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        // set up JFrame border layout
        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame window = new JFrame("FLASH REVIEW");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout());

        GUI makeGUI = new GUI();

        makeGUI.makeMenu(menu);

        mainPanel.add(menu, BorderLayout.CENTER);
        window.add(mainPanel);
        window.pack();
        window.setBounds(500,500,750,750);
        window.setVisible(true);



    }
}