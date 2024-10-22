import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.formdev.flatlaf.themes.*;


public class Main {

    static ArrayList<Font> customFonts = new ArrayList<>();
    public static void main(String[] args) {
       // formatting

        try {
            UIManager.setLookAndFeel( new FlatMacDarkLaf() );
        } catch(Exception ex) {
            System.err.println( "Failed to initialize LaF" );
        }
        UIManager.getLookAndFeelDefaults().put("defaultFont",loadFonts("Regular").deriveFont(Font.PLAIN, 14f));

        // set up JFrame border layout
        JFrame window = new JFrame("flash_review");


        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout());

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());

        // make title
        JLabel title = new JLabel("flash_review");
        title.setFont(loadFonts("Regular").deriveFont(Font.PLAIN, 30f));
        title.setHorizontalAlignment(JLabel.CENTER);


        // make center menu
        GUI makeGUI = new GUI();
        makeGUI.makeMenu(menu, window);

        // makes the bottom bar
        makeGUI.makeSouthPanel(southPanel);

        // loads everything
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(menu, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0,10,15,10));
        window.add(mainPanel);
        window.pack();
        window.setBounds(500,500,920,850);
        window.setVisible(true);

        // test space for queuesystem

        QueueSystem test = new QueueSystem("src/test-deck", "src/test-deck/key.txt");
        try {
            test.getKeysAndLoad();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean run = true;

        while(run & !test.checkIfEmpty()){
            Card thisCard = test.getCard();

            System.out.println(thisCard.getFrontDescription());
            Scanner inputObject = new Scanner(System.in);
            System.out.println("Did you get it right? (y/n)");
            String input = inputObject.nextLine();

            if (input.equals("done")){
                run = false;
            }
            else{
                if (input.equals("y")){
                    test.removeCard();
                }
                else {
                    test.shiftToBack(test.removeCard());
                }

            }
        }

    }

    public static Font loadFonts(String fontType){
        // https://stackoverflow.com/questions/5652344/how-can-i-use-a-custom-font-in-java

        File folder = new File("src/Space_Mono");
        File[] listOfFiles = folder.listFiles();
        try {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].exists()) {
                    if (listOfFiles[i].getName().contains(fontType)){
                        Font font = Font.createFont(Font.TRUETYPE_FONT, listOfFiles[i]);
                        customFonts.add(font);

                        return font;
                    }


                }
            }
        }
        catch (FontFormatException | IOException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        return null;
    }



}