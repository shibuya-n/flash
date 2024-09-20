import java.io.File;

public class Card {

    File cardFile;
    String frontDescription;
    String backDescription;

    public Card (File input){

        cardFile = input;

        // uses parseDescription to get the front/back portions of the name
        frontDescription = parseDescription(input, true);
        backDescription = parseDescription(input, false);

    }

    public String getFrontDescription(){
        return frontDescription;
    }

    public String parseDescription(File input, boolean frontOrBack){
        // split the file name to get just the file name
        String[] tokens = input.getName().split("\\.(?=[^\\.]+$)");
        String fileName = tokens[0];

        String[] splitName = fileName.split("_", 0);

        // if true then return the "front" portion of the name
        if (frontOrBack){
            return splitName[0];
        }
        // otherwise return the "back" portion of the name
        else {
            return splitName[1];
        }

    }





}
