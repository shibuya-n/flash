import javax.swing.*;
import java.lang.Object;
import java.awt.*;
import com.formdev.flatlaf.FlatLightLaf;


public class Main {
    public static void main(String[] args) {
       // formatting
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        UIManager.getLookAndFeelDefaults()
                .put("defaultFont", new Font("URW Gothic", Font.PLAIN, 14));


        // set up JFrame border layout
        JFrame window = new JFrame("FLASH REVIEW");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout());

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());

        // make title
        JLabel title = new JLabel("FLASH REVIEW");
        title.setFont(new Font("URW Gothic", Font.PLAIN, 60));
        title.setHorizontalAlignment(JLabel.CENTER);


        // make center menu
        GUI makeGUI = new GUI();

        makeGUI.makeMenu(menu);
        makeGUI.makeSouthPanel(southPanel);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(menu, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0,10,15,10));
        window.add(mainPanel);
        window.pack();
        window.setBounds(500,500,920,850);
        window.setVisible(true);



    }
}