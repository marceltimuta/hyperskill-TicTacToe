package tictactoe;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class TicTacBoard {
    private final char[][] board = new char[][] {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    private char winner = ' ';
    private char currentPlayer = 'X';
    private final Scanner scanner = new Scanner(System.in);

    public void draw() {
        System.out.println("---------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.printf(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public boolean isWinner() {
        return winner != ' ';
    }

    public void getCoordinates() {
        boolean coordinatesLoop = true;

        while (coordinatesLoop) {
            System.out.print("Enter the coordinates: ");
            try {
                int y = scanner.nextInt();
                int x = scanner.nextInt();
                boolean goodCoordinates = (x > 0 && x < 4) && (y > 0 && y < 4);

                if (goodCoordinates) {
                    y = convertY(y);
                    x = convertX(x);

                    if(board[x][y] != ' ') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        board[x][y] = currentPlayer;
                        winner = getWinner();
                        if (winner != ' ') {
                            draw();
                            System.out.println( winner + " wins");
                        }
                        currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
                        coordinatesLoop = false;
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
            }
            scanner.nextLine();
        }
    }

    private int convertY(int y) {
        return --y;
    }

    private int convertX(int x) {
        x = x - 1;
        if(x == 2) {
            x = 0;
        } else if (x == 0) {
            x = 2;
        }
        return x;
    }

    private boolean threeAreEqual(char a, char b, char c) {
        return (a == b) && (b == c);
    }

    private char getWinner() {
        char winner = ' ';

        for (char[] chars : board) {
            if (threeAreEqual(chars[0], chars[1], chars[2])) {
                winner = chars[0];
            }
        }

        for (int i = 0; i < board.length; i++) {
            if (threeAreEqual(board[0][i],board[1][i],board[2][i])) {
                winner = board[0][i];
            }
        }

        if (threeAreEqual(board[0][0], board[1][1], board[2][2]) ||
                threeAreEqual(board[0][2], board[1][1], board[2][0])) {
            winner = board[1][1];
        }

        return winner;
    }

    private Map<Character,Integer> countSymbols() {
        Map<Character, Integer> count = new HashMap<>();

        for (char[] chars : board) {
            for (int j = 0; j < chars.length; j++) {
                count.put(chars[j], count.getOrDefault(chars[j], 0) + 1);
            }
        }
        return count;
    }

}
