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


    private boolean winner(int row, int col) {
        // This is called just after a piece has been played on the
        // square in the specified row and column.  It determines
        // whether that was a winning move by counting the number
        // of squares in a line in each of the four possible
        // directions from (row,col).  If there are 5 squares (or more)
        // in a row in any direction, then the game is won.

        if (count(row, col, 1, 0 ) >= 5)
            return true;
        if (count(row, col, 0, 1 ) >= 5)
            return true;
        if (count(row, col, 1, -1 ) >= 5)
            return true;
        if (count(row, col, 1, 1 ) >= 5)
            return true;

          /* When we get to this point, we know that the game is not
             won.  The value of win_r1, which was changed in the count()
             method, has to be reset to -1, to avoid drawing a red line
             on the board. */

        //win_r1 = -1;
        return false;

    }  // end winner()


    private int count(int row, int col, int dirX, int dirY) {


        int ct = 1;  // Number of pieces in a row belonging to the player.

        int r, c;    // A row and column to be examined

        r = row + dirX;  // Look at square in specified direction.
        c = col + dirY;
        while ( r >= 0 && r < 15 && c >= 0 && c < 15 && grid[r][c].occupied &&grid[r][c].blackORWHITE == currentCOLOR ) {
            // Square is on the board and contains one of the players's pieces.
            ct++;
            r += dirX;  // Go on to next square in this direction.
            c += dirY;
        }

        //win_r1 = r - dirX;  // The next-to-last square looked at.
        //win_c1 = c - dirY;  //    (The LAST one looked at was off the board or
        //    did not contain one of the player's pieces.

        r = row - dirX;  // Look in the opposite direction.
        c = col - dirY;
        while ( r >= 0 && r < 15 && c >= 0 && c < 15 && grid[r][c].occupied && grid[r][c].blackORWHITE == currentCOLOR ) {
            // Square is on the board and contains one of the players's pieces.
            ct++;
            r -= dirX;   // Go on to next square in this direction.
            c -= dirY;
        }

        //win_r2 = r + dirX;
        //win_c2 = c + dirY;

        // At this point, (win_r1,win_c1) and (win_r2,win_c2) mark the endpoints
        // of the line of pieces belonging to the player.

        return ct;

    }

    

    public void makeMove(int row, int col) {

        Cell cell = grid[row][col];

        if (!cell.occupied) {
            cell.occupied = true;
            cell.blackORWHITE = currentCOLOR;

            print();

            if(winner(row, col)){
                System.out.println("===== Winner Found =====");
            }

            currentCOLOR = !currentCOLOR; //change of turns
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
        m.makeMove(0,10); //W
        m.makeMove(5,6); //B
        m.makeMove(1,11); //W
        m.makeMove(5,7); //B
        m.makeMove(2,12); //W
        m.makeMove(5,8); //B
        m.makeMove(3,13); //W
        m.makeMove(5,9); //B
        m.makeMove(4,14); //W

        launch(args);
    }
}
