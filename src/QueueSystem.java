import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class QueueSystem {

    Queue<Card> cardQueue = new LinkedList<>();
    ArrayList<File> fileDeck = new ArrayList<File>();

    ArrayList<Card> cardDeck = new ArrayList<>();

    ArrayList<String> keys = new ArrayList<>();

    File folder;

    File key;
    public QueueSystem(String input, String k){
        folder = new File(input);
        key = new File(k);

        try {
            getKeysAndLoad();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void getKeysAndLoad() throws IOException { // <-- throws an exception if the file isn't found
        // add files to arraylist
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++){
            if(!listOfFiles[i].getName().equals("key.txt")){
                fileDeck.add(listOfFiles[i]);
            }

        }


        // first need to make a fileReader object
        FileReader input = new FileReader(key);
        // then use buffered reader to read through it
        BufferedReader buffReader = new BufferedReader(input);

        while (buffReader.ready()){
            // gets the information for the card and its fileName
            String[] parsedKey = parseDescription(buffReader.readLine());

            // finds the corresponding file by looking through the fileDeck with a for loop -> if the fileName and the file's name match then a new card is created -> added to the cardDeck
            for (int i = 0; i < fileDeck.size(); i++){

                File getFile = fileDeck.get(i);
                String file = getFile.getName();
                String[] splitFile = file.split("\\.", 2);
                String fileName = splitFile[0];

                String frontDescription = parsedKey[1];
                String backDescription = parsedKey[2];

                if (parsedKey[0].equals(fileName)){
                    
                    Card x = new Card(getFile, frontDescription, backDescription);
                    cardDeck.add(x);
                }
            }
        }
        // items are taken out of the cardDeck and randomly put into the cardQueue to randomize the deck
        while (!cardDeck.isEmpty()){
            int randInt = (int) (Math.random() * (cardDeck.size()));
            cardQueue.add(cardDeck.remove(randInt));
        }


    }

    public Card removeCard(){
        return cardQueue.remove();
    }
    public void shiftToBack(Card input){
        cardQueue.add(input);

    }
    public Card getCard(){
        return cardQueue.peek();
    }

    public boolean checkIfEmpty(){
        if (cardQueue.isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }

    public String[] parseDescription(String input){
        // split the file name to get just the file name
        String[] splitNames = input.split(" : ", 5);

        // should split the key into three parts : [0] is file name, [1] is the front descriptor, [2] is the back descriptor
        return splitNames;

    }






}
