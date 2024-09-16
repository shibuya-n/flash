import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class QueueSystem {

    Queue<Card> cardQueue = new LinkedList<>();
    ArrayList<File> cardDeck = new ArrayList<File>();

    File folder;
    public QueueSystem(String input){
        folder = new File(input);
    }


    public void shuffleAndLoad(){
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++){
            cardDeck.add(listOfFiles[i]);
        }

        for (int i = 0; i < cardDeck.size(); i++){
            int randInt = (int) (Math.random() * (cardDeck.size()-1) + 1);
            File toInput = cardDeck.remove(i);
        }
    }

    public String parse(){

    }


}
