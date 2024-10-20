package env;

import utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Env {
    public static final String serverName = "engine.server";
    public static final String hostAddress = "192.168.15.12";
    public static final int serverPort = 1099;

    public static final String pieceWhite = " W ";
    public static final String pieceBlack = " B ";
    public static final String emptyCell = " E ";

    public static final String menuBgPath = "menu_bg.png";

    public static final String redPiecePath = "red.png";
    public static final String greenPiecePath = "red.png";
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
