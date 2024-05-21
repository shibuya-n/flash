import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
//        subPanel.add(unitSelect);
//        subPanel.add(goButton);
        subPanel.add(dropdownCombo);
        subPanel.add(challengeButton);

        menu.add(subPanel);



        return menu;
    }

    public JPanel makeSouthPanel(JPanel window){

        // boolean has to be final to be accessed within
        final boolean[] isHelpOpen = {false};
        final boolean[] isSettingsOpen = {false};

        // grid layout


        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(1,3,15,20));

        // help button
        JButton helpButton = new JButton("help");

        helpButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){

                // makes sure that help can only be opened once 
                while (!isHelpOpen[0]) {
                    if (!isHelpOpen[0]) {


                        JFrame helpWindow = new JFrame("help");


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
                while (!isSettingsOpen[0]) {
                    if (!isSettingsOpen[0]) {


                        JFrame helpWindow = new JFrame("settings");


                        helpWindow.setBounds(500, 500, 720, 650);
                        helpWindow.setVisible(true);
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

    // function that replaces panel with
    public JPanel makeQuiz(JFrame window){


        // replace old menu with the quiz ui
        JPanel newQuiz = new JPanel();


        // test button
        JButton backButton = new JButton("back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // readds the main menu
                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BorderLayout());

                JPanel menu = new JPanel();
                menu.setLayout(new FlowLayout());

                JPanel southPanel = new JPanel();
                southPanel.setLayout(new FlowLayout());

                // make title
                JLabel title = new JLabel("flash_review");
                title.setHorizontalAlignment(JLabel.CENTER);


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

        return newQuiz;

    }

}
