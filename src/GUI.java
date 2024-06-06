import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GUI extends JFrame {


    // make middle portion of GUI
    public JPanel makeMenu(JPanel menu, JFrame window){
        // test array
        String[] test = {"test1", "test2"};

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
        JComboBox unitSelect = new JComboBox(test);

        // Button
        JButton goButton = new JButton("go");
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // change from main menu to quiz
                window.getRootPane().getContentPane().removeAll();
                window.add(makeQuiz(window));
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
    public JPanel makeQuiz(JFrame window){


        // replace old menu with the quiz ui
        JPanel newQuiz = new JPanel();
        newQuiz.setLayout(new GridBagLayout());
        newQuiz.setBorder(BorderFactory.createEmptyBorder(10,10,15,10));

        // back button + its layout
        JButton backButton = new JButton("back");
        GridBagConstraints buttonLayout = layoutSetter(1,1,0,0);
        buttonLayout.anchor = GridBagConstraints.NORTHWEST;

        // display # of cards left
        JLabel cardsLeft = new JLabel("x cards left");
        cardsLeft.setForeground(Color.GREEN);
        GridBagConstraints remainingCardsLayout = layoutSetter(1,1,0,0);
        remainingCardsLayout.anchor = GridBagConstraints.SOUTH;

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
}
