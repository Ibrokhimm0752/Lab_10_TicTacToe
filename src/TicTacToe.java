import java.util.Scanner;
public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        boolean playAgain;

        do {
            clearBoard();
            String currentPlayer = "X";
            int moveCount = 0;
            boolean gameWon = false;

            while (!gameWon && moveCount < ROWS * COLS) {
                display();
                System.out.println("Player " + currentPlayer + ", it's your turn.");
                int row = SafeInput.getRangedInt(console, "Enter row: ", 1, 3) - 1;
                int col = SafeInput.getRangedInt(console, "Enter column: ", 1, 3) - 1;

                while (!isValidMove(row, col)) {
                    System.out.println("Invalid move! The cell is already occupied. Try again.");
                    row = SafeInput.getRangedInt(console, "Enter row: ", 1, 3) - 1;
                    col = SafeInput.getRangedInt(console, "Enter column: ", 1, 3) - 1;
                }

                board[row][col] = currentPlayer;
                moveCount++;

                if (isWin(currentPlayer)) {
                    gameWon = true;
                    display();
                    System.out.println("Player " + currentPlayer + " wins!");
                } else if (isTie()) {
                    display();
                    System.out.println("It's a tie!");
                    break;
                }

                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            }

            playAgain = SafeInput.getYNConfirm(console, "Would you like to play again?: ");
        } while (playAgain);

        System.out.println("Thanks for playing!");
    }

    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("  1 2 3");
        for (int i = 0; i < ROWS; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j]);
                if (j < COLS - 1) System.out.print("|");
            }
            System.out.println();
            if (i < ROWS - 1) System.out.println("  -----");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int i = 0; i < COLS; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return !isWin("X") && !isWin("O");
    }
}