package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Utils {
    public static String getMessageFromFile(){
        StringBuilder message = new StringBuilder();
        try {
            File messageText = new File("message.txt");
            Scanner myReader = new Scanner(messageText);
            while (myReader.hasNextLine()) {
                message.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return message.toString();
    }

    public static BufferedImage getInputImage(){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("inputImage.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static BufferedImage getOutputImage(){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("outputImage.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static void createOutputImage(BufferedImage image){
        File outputFile = new File("outputImage.png");
        try {
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
