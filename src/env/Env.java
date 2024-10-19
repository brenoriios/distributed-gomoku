package env;

import jdk.jshell.execution.Util;
import utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Env {
   public static final String serverName = "engine.Server";
   public static final String hostAddress = "";
   public static final int serverPort = 1099;

   public static final String pieceWhite = " W ";
   public static final String pieceBlack = " B ";
   public static final String emptyCell = " E ";

   public static final String menuBgPath = "menu_bg.png";

   public static final String whitePiecePath = "blue_piece.png";
   public static final String blackPiecePath = "red_piece.png";
   public static final String cellBackgroundPath = "cross.png";
   public static final String versusPath = "versus.png";
   public static final String customFontPath = "orbitron.ttf";

   public static BufferedImage menuBg = Utils.loadImage(menuBgPath);
   public static BufferedImage whitePiece = Utils.loadImage(whitePiecePath);
   public static BufferedImage blackPiece = Utils.loadImage(blackPiecePath);
   public static BufferedImage cellBackground = Utils.loadImage(cellBackgroundPath);
   public static BufferedImage versus = Utils.loadImage(versusPath);

   public static Font customFont = Utils.loadFont(customFontPath);
}
