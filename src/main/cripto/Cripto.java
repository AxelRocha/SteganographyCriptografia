package main.cripto;

import main.customExceptions.CustomExceptions.ImageSizeNotEnoughException;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cripto {
    StringBuilder builder = new StringBuilder();

    public Cripto() {
    }

    public String get8BitMessage(String message){
        StringBuilder builder = new StringBuilder();

        for (char letra : message.toCharArray()){
            builder.append(String.format("%8s", Integer.toBinaryString(letra)).replace(' ', '0'));
        }

        return builder.toString();
    }

    public BufferedImage encode(String message, BufferedImage image) throws ImageSizeNotEnoughException {
        int linha = image.getHeight();
        int coluna = image.getWidth();
        int newRed, newGreen, newBlue;
        int count = 0;
        int rgb;


        /* Se Mensagem não cabe na imagem */
        if (linha*coluna*3 < message.length()){
            throw new ImageSizeNotEnoughException("Tamanho da imagem não é suficiente para esconder a imagem");
        }

        BufferedImage newImage = new BufferedImage(coluna, linha, BufferedImage.TYPE_3BYTE_BGR);

        for(int i = 0; i < linha; i++) {
            for(int j = 0; j < coluna; j++) {
                Color color = new Color(image.getRGB(j,i)); //(coluna,linha)
                newRed = color.getRed();
                newGreen = color.getGreen();
                newBlue = color.getBlue();

                newRed = updateColor(newRed, count++, message);

                newGreen = updateColor(newGreen, count++, message);

                newBlue = updateColor(newBlue, count++, message);

                rgb = new Color(newRed, newGreen, newBlue).getRGB();
                newImage.setRGB(j,i, rgb);
            }
        }
        return newImage;
    }

    private int updateColor(int color, int count, String message){
        int bit;

        if (count > message.length() - 1){
            return color;
        }

        bit = message.charAt(count);

        /* se numero par e bit impar entao aumenta a cor*/
        if (color % 2 == 0){
            if (bit % 2 == 1){
                color++;
            }
        } else {
            /* se numero impar e bit par entao diminui a cor*/
            if (bit % 2 == 0){
                color--;
            }
        }
        return color;
    }

    public String decode(BufferedImage image){
        int linha = image.getHeight();
        int coluna = image.getWidth();
        int red, green, blue;

        if (image == null){
            return null;
        }

        for(int i = 0; i < linha; i++) {
            for(int j = 0; j < coluna; j++) {
                Color color = new Color(image.getRGB(j, i));

                red = color.getRed();
                green = color.getGreen();
                blue = color.getBlue();

                if (appendInt(red) || appendInt(green) || appendInt(blue)){
                    return builder.toString();
                }
            }
        }
        return builder.toString();
    }

    private boolean appendInt(int color){
        builder.append(color % 2);

        /* Para de ler os bits qndo encontra ## */
        if (builder.toString().contains("0010001100100011")){
            return true;
        }
        return false;
    }

    public String getWordsFromBit(String bitMessage){
        String str = "";
        for (int i = 0; i < bitMessage.length()/8; i++) {

            int a = Integer.parseInt(bitMessage.substring(8*i,(i+1)*8),2);
            str += (char)(a);
        }
        /* Retorna a frase sem o ## */
        return str.substring(0,str.length()-2);
    }
}
