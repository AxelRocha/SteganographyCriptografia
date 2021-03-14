package main;

import java.awt.image.BufferedImage;

import static main.Utils.*;

public class Main {
    public static void main(String[] args) {
        Cripto cripto = new Cripto();

        /* Pega Mensagem do bloco de Texto */
        String message = getMessageFromFile();

        /* Transforma cada char da mensagem em 8bit */
        String binaryMessage = cripto.get8BitMessage(message);

        /* Carrega a imagem para esconder a mensagem */
        BufferedImage inputImage = getInputImage();

        /* Gera o objeto da imagem de output */
        BufferedImage outputImage = cripto.encode(binaryMessage, inputImage);

        /* Gera imagem de output */
        createOutputImage(outputImage);

        /* Pega a imagem para decodificar */
        BufferedImage decodeImage = getOutputImage();

        /* Pega a mensagem em bit decodificada da imagem */
        String decode = cripto.decode(decodeImage);


        System.out.println(binaryMessage);
        System.out.println(decode);

    }
}