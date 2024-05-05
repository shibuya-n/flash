import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {


    // make middle portion of GUI
    public JPanel makeMenu(JPanel window){
        // test array
        String[] test = {"test1", "test2"};

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(3, 1));

        // Label
        JLabel unitDescription = new JLabel("select unit");

        // Dropdown
        JComboBox unitSelect = new JComboBox(test);

        // Button
        JButton goButton = new JButton("go");

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

            }
        });



        subPanel.add(unitDescription);
        subPanel.add(unitSelect);
        subPanel.add(goButton);

        window.add(subPanel);



        return window;
    }

}
