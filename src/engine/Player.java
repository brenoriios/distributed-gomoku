package engine;

import java.io.Serializable;

public class Player implements Serializable {
    private final String id;
    private final String name;
    private String pieceColor;

    public Player() {
        this.id = "";
        this.name = "";
    }

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(String pieceColor) {
        this.pieceColor = pieceColor;
    }

    public String getId() {
        return id;
    }
}
