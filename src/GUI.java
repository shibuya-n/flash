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
        final boolean[] isOpen = {false};

        // grid layout


        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(1,3,15,20));

        // help button
        JButton helpButton = new JButton("help");

        helpButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){

                // makes sure that help can only be opened once 
                while (!isOpen[0]) {
                    if (!isOpen[0]) {


                        JFrame helpWindow = new JFrame("help");


                        helpWindow.setBounds(500, 500, 920, 850);
                        helpWindow.setVisible(true);
                    }
                    isOpen[0] = true;


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
        JButton settingsButton = new JButton("help");

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){


            }
        });

        subPanel.add(helpButton);
        subPanel.add(createDeckButton);

        window.add(subPanel);

        return window;


    }

}
