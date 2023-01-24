package me.emsockz.rosecg.mechanics;

import static me.emsockz.rosecg.Main.defaultPath;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;;

public class Generate {

    public static void generate() {
        try {
            BufferedImage arrow = ImageIO.read(new File(defaultPath+"files"+File.separator+"arrow.png"));
            BufferedImage line = ImageIO.read(new File(defaultPath+"files"+File.separator+"line.png"));
            
            WritableRaster arrowR = arrow.getRaster();

            int width = arrow.getWidth();
            int height = arrow.getHeight();

            int a = 0;
            for (int i = 0; i <= 720; i+=2) {
                BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        int argb = line.getRGB(x + i, y);
                        newImage.setRGB(x, y, argb);
                    }
                }

                // Draw arrow on top of the new image
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        int argb = arrow.getRGB(x, y);
                        int[] ab = arrowR.getPixel(x, y, new int[4]);
                        if (ab[3] != 0) { // if pixel is not transparent
                            newImage.setRGB(x, y, argb);
                        }
                    }
                }

                // Save the new image
                ImageIO.write(newImage, "png", new File(defaultPath+"output"+File.separator+"png"+File.separator+a+".png"));
                a++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
