import com.formdev.flatlaf.util.StringUtils;

import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class GUI extends JFrame {
    File folder = new File("src/decks");
    // test array
    File[] listOfFiles = folder.listFiles();

    // use method to split folder names and reformat it
    String[] listOfTopics = convertToTopic(listOfFiles);


    // make middle portion of GUI
    public JPanel makeMenu(JPanel menu, JFrame window){


        // boolean has to be final to be accessed within
        final boolean[] isHelpOpen = {false};

        WindowListener checkOpenWindow = new WindowAdapter() {
            public void windowOpened(WindowEvent evt) {
                Frame frame = (Frame) evt.getSource();
                String frameName =  frame.getTitle();

                if (frameName.equals("help")){
                    isHelpOpen[0] = true;
                }
            }
            public void windowClosing(WindowEvent evt) {
                Frame frame = (Frame) evt.getSource();
                String frameName =  frame.getTitle();

                if (frameName.equals("help")){
                    isHelpOpen[0] =  false;
                }

            }
        };
        JButton helpButton = new JButton("need help?");
        helpButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                JFrame helpWindow = new JFrame("help");
                helpWindow.addWindowListener(checkOpenWindow);



                // makes sure that help can only be opened once
                while (!isHelpOpen[0]) {
                    if (!isHelpOpen[0]) {

                        // set size and position
                        helpWindow.setBounds(500, 500, 720, 650);

                        JPanel container = new JPanel();
                        container.setLayout(new FlowLayout());

                        // create editor pane to contain the html code
                        JEditorPane editorPane = new JEditorPane();
                        editorPane.setEnabled(true);
                        editorPane.setEditable(false); // so you can't edit what is said

                        // create scroll pane for the editor so there can be a scrollbar
                        JScrollPane scroll = new JScrollPane(editorPane);

                        scroll.setPreferredSize(new Dimension(680,600));
                        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


                        // get url of the html file through the GUI class
                        URL htmlPage = GUI.class.getResource("help.html");

                        // try catch in case there isn't an html file
                        try {
                            // displays the html in the editor pane
                            editorPane.setPage(htmlPage);
                        }
                        catch(IOException j){
                            editorPane.setContentType("text/html");
                            editorPane.setText("<html>Page not found.</html>");
                        }

                        // adds components to the helpWindwow
                        container.add(scroll);
                        helpWindow.getContentPane().add(container, BorderLayout.CENTER);



                        helpWindow.setVisible(true);
                    }
                    isHelpOpen[0] = true;
                }
            }



        });


        // grid layout
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(4, 1, 5,10));

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
        subPanel.add(helpButton);

        menu.add(subPanel);
        return menu;
    }


    // function that replaces main menu with quiz
    public JPanel makeQuiz(JFrame window, int selected){
        // uses selected to find indice of the folder that was selected and to get its path and key to make new QueueSystem object
        String folderPath = listOfFiles[selected].getPath();
        String key = folderPath + "/key.txt";

        // make new quiz
        QueueSystem quiz = new QueueSystem(folderPath, key);



        // replace old menu with the quiz ui
        JPanel newQuiz = new JPanel();

        newQuiz.setLayout(new GridBagLayout());
        newQuiz.setBorder(BorderFactory.createEmptyBorder(10,10,15,10));

        // so the panel can hear key presses

        newQuiz.setFocusable(true);
        newQuiz.grabFocus();
        newQuiz.requestFocus();



        // back button + its layout
        JButton backButton = new JButton("back");
        GridBagConstraints buttonLayout = layoutSetter(1,1,0,0);
        buttonLayout.anchor = GridBagConstraints.NORTHWEST;

        // display # of cards left
        JPanel numberContainer = new JPanel();

        JLabel cardsLeft = new JLabel( quiz.getCardsLeft() + " cards left", SwingConstants.CENTER);
        cardsLeft.setForeground(Color.GREEN);
        GridBagConstraints remainingCardsLayout = layoutSetter(1,1,0,0);
        remainingCardsLayout.fill = GridBagConstraints.HORIZONTAL;
        cardsLeft.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        remainingCardsLayout.anchor = GridBagConstraints.SOUTH;
        numberContainer.add(cardsLeft, remainingCardsLayout);

        // display card img and information

        JPanel cardHolder = new JPanel();
        cardHolder.setLayout(new GridLayout(2,0));

        Card x = quiz.getCard();

        // gets the image of the card and adds it to the display panel
        JPanel imageContainer = new JPanel();
        imageContainer.add(new JLabel(new ImageIcon(x.getImage())));
        cardHolder.add(imageContainer);

        // gets the card's description and adds it to the display panel
        cardHolder.add(new JLabel(x.getFrontDescription(), SwingConstants.CENTER));
        GridBagConstraints cardHolderLayout = layoutSetter(1,1,0,0);
        cardHolderLayout.anchor = GridBagConstraints.CENTER;

        // allows user to click on panel
        KeyEventDispatcher myKeyEventDispatcher = new DefaultFocusManager();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(myKeyEventDispatcher);
        newQuiz.addMouseListener(new MouseAdapter() {

            // variable to turn clicking on/off
            boolean enabled = true;

            // gets a new card
            Card newCard = quiz.getCard();
            // makes the front and back of the card
            JPanel[] frontAndBack = flipCard(newCard);



            // flips the flashcard on mouse click
            @Override
            public void mouseClicked(MouseEvent e) {
                // stops allowing you to flip flashcards after you've completed it
                if (enabled){
                    newQuiz.remove(cardHolder);
                    newQuiz.remove(frontAndBack[0]);


                    // display card img and information and hold the buttons
                    JPanel newCardHolder = new JPanel();
                    newCardHolder.setLayout(new GridLayout(2,0));

                    // panel to hold the buttons
                    JPanel buttonHolder = new JPanel();
                    buttonHolder.setLayout(new GridLayout(0,2));


                    // button if you get it wrong
                    JButton noButton = new JButton("i got it wrong");

                    noButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // lets you click when you flip to the next card
                            enabled = true;

                            // moves the old card to the back of the queue
                            quiz.removeCard();
                            quiz.shiftToBack(newCard);

                            // updates text
                            cardsLeft.setText(quiz.getCardsLeft() + " cards left");

                            // gets a new card and creates new panels from it
                            newCard = quiz.getCard();
                            frontAndBack = flipCard(newCard);

                            // replaces the backside with the new card's panel
                            newQuiz.remove(newCardHolder);
                            JPanel frontSide = frontAndBack[0];
                            newQuiz.add(frontSide, cardHolderLayout);


                            window.revalidate();
                            window.repaint();
                        }
                    });

                    // button if you get it right
                    JButton yesButton = new JButton("i got it right");

                    yesButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            // removes old card from the deck and updates label
                            quiz.removeCard();

                            cardsLeft.setText(quiz.getCardsLeft() + " cards left");


                            // remove the backside of the card and change to the next card if the deck isn't empty
                           if(!quiz.checkIfEmpty()){
                                // lets you click when you flip to the next card
                                enabled = true;



                                // gets a new card
                                newCard = quiz.getCard();
                                // replaces the old panels with the new card's panels
                                frontAndBack = flipCard(newCard);
                                newQuiz.remove(newCardHolder);

                                // adds the new card
                                JPanel frontSide = frontAndBack[0];
                                newQuiz.add(frontSide, cardHolderLayout);
                            }
                            else{
                                // when cards run out it disables clicking
                                enabled = false;
                                newQuiz.remove(newCardHolder);


                                JLabel completion = new JLabel("congrats! you completed this quiz. return to home by pressing the back button.", SwingConstants.CENTER);
                                newQuiz.add(completion, cardHolderLayout);
                            }




                            window.revalidate();
                            window.repaint();
                        }
                    });




                    buttonHolder.add(noButton);
                    buttonHolder.add(yesButton);

                    newCardHolder.add(frontAndBack[1]);
                    newCardHolder.add(buttonHolder);
                    newQuiz.add(newCardHolder, cardHolderLayout);



                    window.revalidate();
                    window.repaint();

                    // prevents you from flipping on the backside of the flashcard
                    enabled = false;

                }


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
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
        newQuiz.add(cardHolder, cardHolderLayout);
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

    public JPanel[] flipCard(Card x){

        // holds front and back of cards -> front is 0 and back is 1
        JPanel[] toReturn= new JPanel[2];

        // front panel
        JPanel frontCardHolder = new JPanel();
        frontCardHolder.setLayout(new GridLayout(2,0));


        // gets the image of the card and adds it to the display panel
        JPanel imageContainer = new JPanel();
        imageContainer.add(new JLabel(new ImageIcon(x.getImage())));
        frontCardHolder.add(imageContainer);

        // gets the card's description and adds it to the display panel
        frontCardHolder.add(new JLabel(x.getFrontDescription(), SwingConstants.CENTER));
        GridBagConstraints cardHolderLayout = layoutSetter(1,1,0,0);
        cardHolderLayout.anchor = GridBagConstraints.CENTER;

        KeyEventDispatcher myKeyEventDispatcher = new DefaultFocusManager();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(myKeyEventDispatcher);

        toReturn[0] = frontCardHolder;

        // back panel

        JPanel backCardHolder = new JPanel();
        backCardHolder.setLayout(new GridLayout(4,0));
        // gets the image of the card and adds it to the display panel
        JPanel backImageContainer = new JPanel();
        backImageContainer.add(new JLabel(new ImageIcon(x.getImage())));
        backCardHolder.add(backImageContainer);

        // gets the card's front description and adds it to the display panel
        backCardHolder.add(new JLabel(x.getFrontDescription(), SwingConstants.CENTER));


        JSeparator descSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        descSeparator.setForeground(Color.white);
        descSeparator.setBackground(Color.white);
        backCardHolder.add(descSeparator);


        // gets the card's back description and adds it to the display panel
        backCardHolder.add(new JLabel(x.getBackDescription(), SwingConstants.CENTER));

        // creates buttons/button holders so you can answer whether you won -> yes or no
        JPanel buttonHolder = new JPanel();
        buttonHolder.setLayout(new GridLayout(0,2));


        cardHolderLayout.anchor =  GridBagConstraints.CENTER;


        toReturn[1]= backCardHolder;

        return toReturn;

    }
}
