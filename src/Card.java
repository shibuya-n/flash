import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Card {

    private File    cardFile;
    private String frontDescription;
    private String backDescription;

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

    public ImageIcon getImageIcon(){
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(cardFile.getAbsolutePath()));
        } catch (java.io.IOException ioe) {
            ioe.printStackTrace();
        }

        ImageIcon imageIcon = new ImageIcon(myPicture);

        return imageIcon;
    }
}
