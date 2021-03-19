package main.utils;

import main.customExceptions.CustomExceptions.InputImageNotFoundException;
import main.customExceptions.CustomExceptions.MessageArchiveNotFoundException;
import main.customExceptions.CustomExceptions.EndMessageTokenNotFoundException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Utils {
    public static String getMessageFromFile() throws MessageArchiveNotFoundException, EndMessageTokenNotFoundException {
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
            throw new MessageArchiveNotFoundException("Arquivo \"message.txt\" de mensagem não foi encontrado");
        }
        if (!message.toString().contains("##")){
            throw new EndMessageTokenNotFoundException("É necessário possuir o token ## ao final da mensagem");
        }
        return message.toString();
    }

    public static BufferedImage getInputImage() throws InputImageNotFoundException {
        BufferedImage image;
        try {
            image = ImageIO.read(new File("inputImage.png"));
        } catch (IOException e) {
            throw new InputImageNotFoundException("Imagem \"inputImage.png\" não encontrada");
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
