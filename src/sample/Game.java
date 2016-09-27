package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Game extends Application {

    public Cell[][] grid;
    public boolean currentCOLOR = true; //TRUE-white, False-black
    public boolean endOfGame;
    public Cell[] gameHistory;
    public int numTurns;


    public void createBoard() {
        grid = new Cell[15][15];
        gameHistory = new Cell[255];
        numTurns = 0;
        endOfGame = false;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Cell cell = new Cell();
                cell.occupied = false;
                cell.col = -1;
                cell.row = -1;
                grid[i][j] = cell;
            }
        }
    }


    private boolean winner(int row, int col) {
        // This is called just after a piece has been played on the
        // square in the specified row and column. If there are 5 squares (or more)
        // in a row in any direction, then the game is won.

        if (count(row, col, 1, 0 ) >= 5)
            return true;
        if (count(row, col, 0, 1 ) >= 5)
            return true;
        if (count(row, col, 1, -1 ) >= 5)
            return true;
        if (count(row, col, 1, 1 ) >= 5)
            return true;

        // When we get to this point, we know that the game is not won.

        return false;

    }


    private int count(int row, int col, int dirX, int dirY) {


        int ct = 1;  // Holds current number of pieces in a 'row'

        int r;
        int c;      // A row and column to be examined

        r = row + dirX;  // Look at square in specified direction.
        c = col + dirY;
        while ( r >= 0 && r < 15 && c >= 0 && c < 15 && grid[r][c].occupied && grid[r][c].blackORWHITE == currentCOLOR ) {
            ct++;
            r += dirX;  // Go on to next square in this direction.
            c += dirY;
        }

        r = row - dirX;  // Look in the opposite direction.
        c = col - dirY;
        while ( r >= 0 && r < 15 && c >= 0 && c < 15 && grid[r][c].occupied && grid[r][c].blackORWHITE == currentCOLOR ) {
            ct++;
            r -= dirX;   // Go on to next square in this direction.
            c -= dirY;
        }

        return ct;

    }



    public boolean makeMove(int row, int col) {
        Cell cell = grid[row][col];

        if (!cell.occupied && !endOfGame) {
            cell.occupied = true;
            cell.blackORWHITE = currentCOLOR;
            cell.row = row;
            cell.col = col;
            gameHistory[numTurns] = cell;
            numTurns++;

            print();
            printHistory();

            if(winner(row, col)){
                endOfGame = true;
                System.out.println("===== Winner Found =====");
            }

            currentCOLOR = !currentCOLOR; //change of turns
            return true;
        }

        else return false;

    }

    public void undoMove(){
        if(numTurns>0) {
            numTurns--;
            Cell cell = grid[gameHistory[numTurns].row][gameHistory[numTurns].col];
            cell.occupied = false;
            currentCOLOR = cell.blackORWHITE;
            endOfGame = false;
            print();
        }
    }

    private void printHistory(){
        for (int i = 0; i < numTurns; i++) {
            System.out.print("["+gameHistory[i].row+", "+ gameHistory[i].col+"]");
        }
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
        int row;
        int col;

    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Gomoku");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 515, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
