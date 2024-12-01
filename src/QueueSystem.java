import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class QueueSystem {

    Queue<Card> cardQueue = new LinkedList<>();
    ArrayList<File> fileDeck = new ArrayList<File>();

    ArrayList<Card> cardDeck = new ArrayList<>();


    File folder;

    File key;
    public QueueSystem(String input, String k, boolean isChallenge){

        if (!isChallenge){ // if it isnt a challenge then everything gets loaded normally
            folder = new File(input);  // use inputted  path name to get the folder
            key = new File(k); // use inputted key path name the key file name to get the key file

            try {
                getKeysAndLoad(); // this loads everything into the cardQueue
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            // looks at folder with all the decks and puts them into a folder
            File decks = new File("src/decks");
            File[] listOfDecks = decks.listFiles();

            // goes through the decks folder and then looks at each individual deck -> getKeysAndLoad then loads every deck one by one onto cardQueue
            for (int i = 0; i < listOfDecks.length; i++){


                String pathName = "src/decks/" + listOfDecks[i].getName(); // have to add the name of the deck to the path name so we can keep looking at the next deck


                File topicFolder = new File(pathName); // use that path name to get the folder
                folder = topicFolder; // makes that the folder that the getKeysAndLoad function will use to load it into the cardQueue queue

                String keyPath = pathName + "/key.txt"; // use that path name combined with the key file name to get the key file
                key = new File(keyPath);

                try {
                    getKeysAndLoad(); // this loads everything into the cardQueue
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }




    public void getKeysAndLoad() throws IOException { // <-- throws an exception if the file isn't found
        // add files to arraylist
        File[] listOfCards = folder.listFiles();
        for (int i = 0; i < listOfCards.length; i++){
            if(!listOfCards[i].getName().equals("key.txt")){

                fileDeck.add(listOfCards[i]);
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


    public void removeCard(){
        cardQueue.remove();
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
    public int getCardsLeft(){

        return cardQueue.size();
    }

    public String[] parseDescription(String input){
        // split the file name to get just the file name
        String[] splitNames = input.split(" : ", 5);

        // should split the key into three parts : [0] is file name, [1] is the front descriptor, [2] is the back descriptor
        return splitNames;

    }






}
