package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;

public class Controller {

    @FXML
    private Button[][] btnMatrix;

    @FXML
    private GridPane gp;

    @FXML
    private Button restart;

    @FXML
    private Button quitOne;

    @FXML
    private Button Back;


    @FXML
    private MenuButton system;

    @FXML
    private MenuItem changeBG;

    @FXML
    private MenuItem quitTwo;

    private Game Boardgame;
    private int BGimage;

    private void runGame(){
        Boardgame = new Game();
        Boardgame.createBoard();
        makeButtonMatrix();

    }

    private void giveEachBtnAnAction(int i, int j){
        final int currRow = i;
        final int currCol = j;

        btnMatrix[i][j].setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //System.out.println("Button["+i+"]["+j+"]");
                if(Boardgame.makeMove(i,j)) {
                    if (Boardgame.grid[i][j].blackORWHITE) {
                        btnMatrix[i][j].setStyle("-fx-background-color: white");
                    } else
                        btnMatrix[i][j].setStyle("-fx-background-color: black");
                }


            }
        });

    }

    private void makeButtonMatrix(){

        gp.getRowConstraints().removeAll(gp.getRowConstraints());
        gp.getColumnConstraints().removeAll(gp.getColumnConstraints());
        gp.getChildren().removeAll(gp.getChildren());

        int row = 15;
        int col = 15;

        for (int i = 0; i < row; i++) {
            RowConstraints rConstraints = new RowConstraints();
            rConstraints.setPrefHeight(30);
            gp.getRowConstraints().add(rConstraints);
        }

        for (int j = 0; j < col; j++) {
            ColumnConstraints cConstraints = new ColumnConstraints();
            cConstraints.setPrefWidth(30);
            gp.getColumnConstraints().add(cConstraints);
        }

        btnMatrix =  new Button[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Button btn = new Button();
                double r=15;
                btn.setShape(new Circle(r));
                btn.setMinSize(2*r, 2*r);
                btn.setMaxSize(2*r, 2*r);
                btn.setStyle(
                        "-fx-background-radius: 5em; " +
                                "-fx-min-width: 30px; " +
                                "-fx-min-height: 30px; " +
                                "-fx-max-width: 30px; " +
                                "-fx-max-height: 30px; " +
                                "-fx-background-color: transparent;" +
                                "-fx-background-insets: 0px; " +
                                "-fx-padding: 0px;"
                );


                btnMatrix[i][j] = btn;
                gp.add(btn, j, i);

                final int currRow = i;
                final int currCol = j;

                giveEachBtnAnAction(i, j);

            }
        }


    }

    @FXML
    private void initialize() {

        BGimage = 0;
        runGame();

        changeBG.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (BGimage == 0){
                    //SET BACKGROUND IMAGES
                    BGimage++;
                }
                if (BGimage == 1){
                    BGimage++;
                }
                if (BGimage == 2){
                    BGimage = 0;
                }
            }
        });

        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                runGame();
            }
        });
        quitOne.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        quitTwo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        Back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Boardgame.numTurns>0) {
                    int row = Boardgame.gameHistory[Boardgame.numTurns - 1].row;
                    int col = Boardgame.gameHistory[Boardgame.numTurns - 1].col;
                    btnMatrix[row][col].setStyle("-fx-background-color: transparent");
                    Boardgame.undoMove();
                }
            }
        });

    }
}
