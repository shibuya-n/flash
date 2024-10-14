import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class QueueSystem {

    Queue<Card> cardQueue = new LinkedList<>();
    ArrayList<File> cardDeck = new ArrayList<File>();

    ArrayList<String> keys = new ArrayList<>();

    File folder;

    File key;
    public QueueSystem(String input, String k){
        folder = new File(input);
        key = new File(k);
    }


    public void shuffleAndLoad(){
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++){
            cardDeck.add(listOfFiles[i]);
        }

        while (!cardDeck.isEmpty()){
            int randInt = (int) (Math.random() * (cardDeck.size()));
            File toInput = cardDeck.remove(randInt);
            Card x = new Card(toInput);
            cardQueue.add(x);
            System.out.println(x.getFrontDescription());
        }

    }

    public void getKeys() throws FileNotFoundException { // <-- throws an exception if the file isn't found

        // first need to make a fileReader object
        FileReader input = new FileReader(key);
        // then use buffered reader to read through it
        //while ()

    }

    public Card removeCard(){
        return cardQueue.remove();
    }
    public void shiftToBack(Card input){
        cardQueue.add(input);
        System.out.println("queue " + cardQueue);
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






}
