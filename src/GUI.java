import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {


    // make middle portion of GUI
    public JPanel makeMenu(JPanel window){
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

        window.add(subPanel);



        return window;
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
    public JFrame makeQuiz(JFrame window){
        //https://stackoverflow.com/questions/35960292/how-to-make-back-button-work-from-the-second-frame-to-the-first-frame


        window.setVisible(false);
        JFrame quiz = new JFrame("tab2");

        // test button
        JButton backButton = new JButton("back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(true);
            }
        }
        );

        return quiz;

    }

}
