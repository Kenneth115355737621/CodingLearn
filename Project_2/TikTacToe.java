package Project_2;

/**
 * Task 1
 * You will store the game status in this class
 * These method does not have to be void,
 * feel free to change or add any new method you want in this class
 */
public class TikTacToe {
    
    private int[][] board;
    // think of what other variables are needed here

    private final char player1Symbol = 'o';
    private final char player2Symbol = 'x';

    /**
     * initialize this class
     */
    public TikTacToe() {
        // to be implemented
    }

    /**
     * The move by one of the player, modify something on the board
     * @param x
     * @param y
     */
    public void move(int x, int y, int player) {
        // to be implemented
    }

    /**
     * determinate whether this game is win by player 1, player 2, or a hang
     * @return the status code
     */
    public int gameStatus() {
        // to be implemented
        return 0;
    }

    /**
     * print the board
     */
    public void printBoard() {
        // to be implemented
    }
}
