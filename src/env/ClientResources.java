package env;

import utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ClientResources {
    public static final String menuBgPath = "background.png";

    public static final String redPiecePath = "black.png";
    public static final String greenPiecePath = "white.png";
    public static final String cellBackgroundPath = "empty_cell.png";
    public static final String versusPath = "versus.png";

    public static BufferedImage menuBg = Utils.loadImage(menuBgPath);

    public static BufferedImage redPiece = Utils.loadImage(redPiecePath);
    public static BufferedImage greenPiece = Utils.loadImage(greenPiecePath);
    public static BufferedImage cellBackground = Utils.loadImage(cellBackgroundPath);
    public static BufferedImage versus = Utils.loadImage(versusPath);

    public static final String customFontPath = "orbitron.ttf";
    public static Font customFont = Utils.loadFont(customFontPath);
}
