package engine;

import java.io.Serializable;

public class Player implements Serializable  {
    private String id;
    private String name;
    private String pieceColor;

    public Player(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setId(String id) {
        this.id = id;
    }
}
