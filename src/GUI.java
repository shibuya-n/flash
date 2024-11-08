import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class GUI extends JFrame {
    File folder = new File("src/decks");
    // test array
    File[] listOfFiles = folder.listFiles();

    // use method to split folder names and reformat it
    String[] listOfTopics = convertToTopic(listOfFiles);


    // make middle portion of GUI
    public JPanel makeMenu(JPanel menu, JFrame window){


        // grid layout
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(3, 1, 5,10));

        // button + dropdown container
        JPanel dropdownCombo = new JPanel();
        dropdownCombo.setLayout(new GridLayout(1, 2, 10, 10));

        // Label
        JLabel unitDescription = new JLabel("select unit");
        unitDescription.setHorizontalAlignment(JLabel.CENTER);
        unitDescription.setFont(new Font("Tahoma", Font.BOLD, 20));

        // Dropdown
        JComboBox unitSelect = new JComboBox(listOfTopics);

        // Button
        JButton goButton = new JButton("go");
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // change from main menu to quiz
                int selected = unitSelect.getSelectedIndex();

                window.getRootPane().getContentPane().removeAll();
                window.add(makeQuiz(window, selected));
                window.revalidate();
                window.repaint();
            }
        });

        // Challenge Mode
        JButton challengeButton = new JButton("challenge mode");
        challengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            }
        });

        // add to dropdown and button container
        dropdownCombo.add(unitSelect);
        dropdownCombo.add(goButton);
        subPanel.add(unitDescription);
        subPanel.add(dropdownCombo);
        subPanel.add(challengeButton);

        menu.add(subPanel);
        return menu;
    }

    public JPanel makeSouthPanel(JPanel window){

        // boolean has to be final to be accessed within
        final boolean[] isHelpOpen = {false};
        final boolean[] isSettingsOpen = {false};
        final boolean[] isCreateDeckOpen = {false};

        //check if smaller windows are opened
        WindowListener checkOpenWindow = new WindowAdapter() {
            public void windowOpened(WindowEvent evt) {
                Frame frame = (Frame) evt.getSource();
                String frameName =  frame.getTitle();

                if (frameName.equals("help")){
                    isHelpOpen[0] = true;
                }
                else if (frameName.equals("settings")){
                    isSettingsOpen[0] = true;
                }
                else {
                    isCreateDeckOpen[0] = true;
                }
            }
            public void windowClosing(WindowEvent evt) {
                Frame frame = (Frame) evt.getSource();
                String frameName =  frame.getTitle();

                if (frameName.equals("help")){
                    isHelpOpen[0] =  false;
                }
                else if (frameName.equals("settings")){
                    isSettingsOpen[0] = false;
                }
                else {
                    isCreateDeckOpen[0] = false;
                }
            }
        };

        // grid layout
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(1,3,15,20));

        // help button
        JButton helpButton = new JButton("help");


        helpButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                JFrame helpWindow = new JFrame("help");
                helpWindow.addWindowListener(checkOpenWindow);



                // makes sure that help can only be opened once
                while (!isHelpOpen[0]) {
                    if (!isHelpOpen[0]) {

                        helpWindow.setBounds(500, 500, 720, 650);
                        helpWindow.setVisible(true);
                    }
                    isHelpOpen[0] = true;
                }
                }



        });

        // create deck

        JButton createDeckButton = new JButton("create deck");

        createDeckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

            }
        });

        // create settings
        JButton settingsButton = new JButton("settings");

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                // makes sure that help can only be opened once
                JFrame settings = new JFrame("settings");
                settings.addWindowListener(checkOpenWindow);



                // makes sure that help can only be opened once
                while (!isSettingsOpen[0]) {
                    if (!isSettingsOpen[0]) {

                        settings.setBounds(500, 500, 720, 650);
                        settings.setVisible(true);
                    }
                    isSettingsOpen[0] = true;
                }
            }

        });


        subPanel.add(helpButton);
        subPanel.add(settingsButton);
        subPanel.add(createDeckButton);

        window.add(subPanel);

        return window;


    }

    // function that replaces main menu with quiz
    public JPanel makeQuiz(JFrame window, int selected){
        // uses selected to find indice of the folder that was selected and to get its path and key to make new QueueSystem object
        String folderPath = listOfFiles[selected].getPath();
        String key = folderPath + "/key.txt";

        // make new quiz
        QueueSystem quiz = new QueueSystem(folderPath, key);
        try {
            quiz.getKeysAndLoad();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // replace old menu with the quiz ui
        JPanel newQuiz = new JPanel();
        newQuiz.setLayout(new GridBagLayout());
        newQuiz.setBorder(BorderFactory.createEmptyBorder(10,10,15,10));

        // back button + its layout
        JButton backButton = new JButton("back");
        GridBagConstraints buttonLayout = layoutSetter(1,1,0,0);
        buttonLayout.anchor = GridBagConstraints.NORTHWEST;

        // display # of cards left
        JPanel numberContainer = new JPanel();

        JLabel cardsLeft = new JLabel( quiz.getCardsLeft() + " cards left");
        cardsLeft.setForeground(Color.GREEN);
        GridBagConstraints remainingCardsLayout = layoutSetter(1,1,0,0);
        remainingCardsLayout.fill = GridBagConstraints.HORIZONTAL;
        cardsLeft.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        remainingCardsLayout.anchor = GridBagConstraints.SOUTH;
        numberContainer.add(cardsLeft, remainingCardsLayout);

        // display card img and information

        JPanel cardHolder = new JPanel();
        cardHolder.setLayout(new GridLayout(0,2));

        Card x = quiz.getCard();
        JPanel imageContainer = new JPanel();
        imageContainer.add(new JLabel(new ImageIcon(x.getImage())));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // readds the main menu
                window.getContentPane().removeAll();

                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BorderLayout());

                JPanel menu = new JPanel();
                menu.setLayout(new FlowLayout());

                JPanel southPanel = new JPanel();
                southPanel.setLayout(new FlowLayout());

                // make title
                JLabel title = new JLabel("flash_review");
                title.setHorizontalAlignment(JLabel.CENTER);
                title.setFont(Main.loadFonts("Regular").deriveFont(Font.PLAIN, 30f));


                // make center menu
                GUI makeGUI = new GUI();

                makeGUI.makeMenu(menu, window);
                makeGUI.makeSouthPanel(southPanel);

                mainPanel.add(title, BorderLayout.NORTH);
                mainPanel.add(menu, BorderLayout.CENTER);
                mainPanel.add(southPanel, BorderLayout.SOUTH);
                mainPanel.setBorder(BorderFactory.createEmptyBorder(0,10,15,10));


                mainPanel.add(title, BorderLayout.NORTH);
                mainPanel.add(menu, BorderLayout.CENTER);
                mainPanel.add(southPanel, BorderLayout.SOUTH);
                mainPanel.setBorder(BorderFactory.createEmptyBorder(0,10,15,10));

                
                window.add(mainPanel);

                window.revalidate();
                window.repaint();

            }
        }
        );

        // add all the items and the location of object
        newQuiz.add(backButton, buttonLayout);
        newQuiz.add(cardHolder, CENTER_ALIGNMENT);
        newQuiz.add(cardsLeft, remainingCardsLayout);



        return newQuiz;

    }


    // helps simplify setting up a layout with Gridbag
    public GridBagConstraints layoutSetter(double xWeight, double yWeight, int xGrid, int yGrid){
        GridBagConstraints toReturn = new GridBagConstraints();
        toReturn.weightx = xWeight;
        toReturn.weighty = yWeight;
        toReturn.gridx = xGrid;
        toReturn.gridy = yGrid;

        return toReturn;
    }

    // convert decks folder into a list of Strings of the topic names
    public String[] convertToTopic(File[] input){
        String[] toReturn = new String[input.length];
        for (int i = 0; i < input.length; i++){

            String toSplit = input[i].getName();

            String[] splitArray = toSplit.split("_",5);
            String topic = splitArray[0];
            String number = splitArray[1];

            toReturn[i] = topic + " " + number;
        }

        return toReturn;
    }

}
