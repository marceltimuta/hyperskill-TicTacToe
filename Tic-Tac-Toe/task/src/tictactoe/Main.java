package tictactoe;


import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        TicTacBoard ticTacBoard = new TicTacBoard();

        while (!ticTacBoard.isWinner()) {
            ticTacBoard.draw();
            ticTacBoard.getCoordinates();
        }

//        ticTacBoard.getResult();





    }

}
