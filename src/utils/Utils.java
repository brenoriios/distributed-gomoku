package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public abstract class Utils {
    public static BufferedImage loadImage(String fileName) {
        try {
            String filePath = "src/resources/images/" + fileName;
            File file = new File(filePath);
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Font loadFont(String fileName) {
        try {
            String filePath = "src/resources/fonts/" + fileName;
            return Font.createFont(Font.TRUETYPE_FONT, new File(filePath)).deriveFont(12f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
