import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Card {

    File cardFile;
    String frontDescription;
    String backDescription;

    public Card (File input, String front, String back){

        cardFile = input;

        // uses parseDescription to get the front/back portions of the name
        frontDescription = front;
        backDescription = back;

    }

    public String getFrontDescription(){
        return frontDescription;
    }

    public String getBackDescription(){
        return backDescription;
    }

    public Image getImage(){
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(cardFile);
        } catch (java.io.IOException ioe) {
            ioe.printStackTrace();
        }

        ImageIcon imageIcon = new ImageIcon(myPicture);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(60,60, Image.SCALE_SMOOTH);

        return newimg;
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
