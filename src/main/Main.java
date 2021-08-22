package main;

import main.cripto.Cripto;
import main.customExceptions.CustomExceptions;

import java.awt.image.BufferedImage;

import static main.utils.Utils.*;

public class Main {
    public static void main(String[] args) throws CustomExceptions {
        Cripto cripto = new Cripto();

        /* Pega Mensagem do bloco de Texto */
        String message = getMessageFromFile("message.txt");

        /* Transforma cada char da mensagem em 8bit */
        String binaryMessage = cripto.get8BitMessage(message);

        /* Carrega a imagem para esconder a mensagem */
        BufferedImage inputImage = getInputImage("inputImage.png");

        /* Gera o objeto da imagem de output */
        BufferedImage outputImage = cripto.encode(binaryMessage, inputImage);

        /* Gera imagem de output */
        createOutputImage(outputImage);

        /* Pega a imagem para decodificar */
        BufferedImage decodeImage = getOutputImage();

        /* Pega a mensagem em bit decodificada da imagem */
        String decode = cripto.decode(decodeImage);

        String frase = cripto.getWordsFromBit(decode);


        System.out.println("Mensagem Codificada: \n" + binaryMessage);
        System.out.println("Mensagem Decodificada: \n" + decode);
        System.out.println("Mensagem Traduzida: \n" + frase);


    }
}