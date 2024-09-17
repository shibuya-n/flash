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

        while (!cardDeck.isEmpty()){
            int randInt = (int) (Math.random() * (cardDeck.size()));
            File toInput = cardDeck.remove(randInt);
            Card x = new Card(toInput, toInput.getName(), "backPlaceHolder");
            cardQueue.add(x);
            System.out.println(x.getFrontDescription());
        }
//        for (int i = 0; i < cardDeck.size(); i++){
//            int randInt = (int) (Math.random() * (cardDeck.size()));
//            File toInput = cardDeck.remove(randInt);
//            Card x = new Card(toInput, toInput.getName(), "backPlaceHolder");
//            cardQueue.add(x);
//            System.out.println(x.getFrontDescription());
//        }
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
