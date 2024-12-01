public class codeForLater {

    //    public JPanel makeSouthPanel(JPanel window){
//
//        // boolean has to be final to be accessed within
//        final boolean[] isHelpOpen = {false};
//        final boolean[] isSettingsOpen = {false};
//        final boolean[] isCreateDeckOpen = {false};
//
//        //check if smaller windows are opened
//        WindowListener checkOpenWindow = new WindowAdapter() {
//            public void windowOpened(WindowEvent evt) {
//                Frame frame = (Frame) evt.getSource();
//                String frameName =  frame.getTitle();
//
//                if (frameName.equals("help")){
//                    isHelpOpen[0] = true;
//                }
//                else if (frameName.equals("settings")){
//                    isSettingsOpen[0] = true;
//                }
//                else {
//                    isCreateDeckOpen[0] = true;
//                }
//            }
//            public void windowClosing(WindowEvent evt) {
//                Frame frame = (Frame) evt.getSource();
//                String frameName =  frame.getTitle();
//
//                if (frameName.equals("help")){
//                    isHelpOpen[0] =  false;
//                }
//            }
//        };
//
//
//
//        // grid layout
//        JPanel subPanel = new JPanel();
//        subPanel.setLayout(new GridLayout(1,1,15,20));
//
//        // help button
//        JButton helpButton = new JButton("help");
//
//
//        helpButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e){
//                JFrame helpWindow = new JFrame("help");
//                helpWindow.addWindowListener(checkOpenWindow);
//
//
//
//                // makes sure that help can only be opened once
//                while (!isHelpOpen[0]) {
//                    if (!isHelpOpen[0]) {
//
//                        // set size and position
//                        helpWindow.setBounds(500, 500, 720, 650);
//
//                        JPanel container = new JPanel();
//                        container.setLayout(new FlowLayout());
//
//                        // create editor pane to contain the html code
//                        JEditorPane editorPane = new JEditorPane();
//                        editorPane.setEnabled(true);
//                        editorPane.setEditable(false); // so you can't edit what is said
//
//                        // create scroll pane for the editor so there can be a scrollbar
//                        JScrollPane scroll = new JScrollPane(editorPane);
//
//                        scroll.setPreferredSize(new Dimension(680,600));
//                        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//
//
//                        // get url of the html file through the GUI class
//                        URL htmlPage = GUI.class.getResource("help.html");
//
//                        // try catch in case there isn't an html file
//                        try {
//                            // displays the html in the editor pane
//                            editorPane.setPage(htmlPage);
//                        }
//                        catch(IOException j){
//                            editorPane.setContentType("text/html");
//                            editorPane.setText("<html>Page not found.</html>");
//                        }
//
//                        // adds components to the helpWindwow
//                        container.add(scroll);
//                        helpWindow.getContentPane().add(container, BorderLayout.CENTER);
//
//
//
//                        helpWindow.setVisible(true);
//                    }
//                    isHelpOpen[0] = true;
//                }
//                }
//
//
//
//        });
//
//        // create deck
//
//        JButton createDeckButton = new JButton("create deck");
//
//        createDeckButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e){
//
//            }
//        });
//
//        // create settings
//        JButton settingsButton = new JButton("settings");
//
//        settingsButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e){
//
//                // makes sure that help can only be opened once
//                JFrame settings = new JFrame("settings");
//                settings.addWindowListener(checkOpenWindow);
//
//
//
//
//
//
//
//
//
//                // makes sure that help can only be opened once
//                while (!isSettingsOpen[0]) {
//                    if (!isSettingsOpen[0]) {
//
//                    }
//                    isSettingsOpen[0] = true;
//                }
//            }
//
//        });
//
//
//        subPanel.add(helpButton);
//        subPanel.add(settingsButton);
//        subPanel.add(createDeckButton);
//
//        window.add(subPanel);
//
//        return window;
//
//
//    }
}
