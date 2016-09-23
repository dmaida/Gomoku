package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public Cell[][] grid;
    public boolean currentCOLOR = true; //TRUE-white, False-black

    public void createBoard() {
        grid = new Cell[15][15];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Cell cell = new Cell();
                cell.occupied = false;
                grid[i][j] = cell;
            }
        }
    }

    public boolean winCheck() {
        return false;
    }

    public void makeMove(int row, int col) {

        Cell cell = grid[row][col];

        if (!cell.occupied) {
            cell.occupied = true;
            cell.blackORWHITE = currentCOLOR;
            currentCOLOR = !currentCOLOR; //change of turns
        }
        print();
    }

    private void print() {

        for (int i = 0; i < grid.length; i++) {
            System.out.println();
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j].occupied){
                    if(!grid[i][j].blackORWHITE){
                        System.out.print("|X|");
                    }
                    else{
                        System.out.print("|O|");
                    }
                }
                else{
                    System.out.print("|_|");
                }
            }
        }
        System.out.println();
    }

    public class Cell{
        boolean occupied;
        boolean blackORWHITE;

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Gomoku");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Main m = new Main();
        m.createBoard();
        m.makeMove(5,5);
        m.makeMove(5,6);
        m.makeMove(5,7);
        launch(args);
    }
}
