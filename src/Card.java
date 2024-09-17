import java.io.File;

public class Card {

    File cardFile;
    String frontDescription;
    String backDescription;

    public Card (File input, String f, String b){

        cardFile = input;
        frontDescription = f;
        backDescription = b;

    }

    public String getFrontDescription(){
        return frontDescription;
    }





}
