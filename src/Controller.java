import engine.Client;
import engine.IGomoku;
import view.GUI;

public class Controller {
    private final GUI view;
    private final Client model;

    public Controller(GUI view, Client model){
        this.view = view;
        this.model = model;

        setupMenuListeners();
        setupBoardListeners();

        view.init();
    }

    public void setupMenuListeners() {
        this.view.getMenu().connect.addActionListener(e -> {
            this.model.connect(this.view.getMenu().nameInput.getText());
            this.view.showBoard();
        });
    }

    public void setupBoardListeners(){
        for(int x = 0; x < IGomoku.width; x++){
            for(int y = 0; y < IGomoku.height; y++) {
                int finalX = x;
                int finalY = y;
                this.view.getBoard().getButtons()[x][y].addActionListener(e -> {
                    this.model.placePiece(finalX, finalY);
                    this.view.getBoard().update(this.model.getBoard());
                });
            }
        }
    }

    public static void main(String[] args) {
        GUI ui = new GUI();
        Client cl = new Client();
        Controller ct = new Controller(ui, cl);
    }
}
