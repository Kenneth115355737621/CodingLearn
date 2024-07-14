package Project_2;

/**
 * Task 1
 * You will store the game status in this class
 * These method does not have to be void,
 * feel free to change or add any new method you want in this class
 */
public class TikTacToe {
    
    private int[] board;
    private int movesNum;

    private final String player1Symbol = "o";
    private final String player2Symbol = "x";
    private final String emptySymbol = "-";

    /**
     * initialize this class
     */
    public TikTacToe() {
        board = new int[9];
        for (int i = 0; i < 9; i++) {
            board[i] = 0;
        }
        movesNum = 0;
    }

    /**
     * The move by one of the player, modify something on the board
     * @param x location
     * @param player 1 as player 1, 2 as player2 
     */
    public void move(int x, int player) throws Exception {
        if (x < 0 || x >= 9) {
            throw new Exception("Please enter a number from 1 to 9");
        }
        if (board[x] != 0) {
            throw new Exception("This location already has a block");
        }
        board[x] = player;
        movesNum++;
    }

    /**
     * determinate whether this game is win by player 1, player 2, or a hang
     * @return the status code
     * 0: continue
     * 1: player 1 win
     * 2: player 2 win
     * 3: hang
     */
    public int gameStatus() {
        return gameStatusHelper(board, movesNum);
    }

    private int gameStatusHelper(int[] board, int movesNum) {
        for (int i = 0; i < 3; i++) {
            if (board[3*i] != 0 && board[3 * i] == board[3 * i + 1] && board[3 * i] == board[3 * i + 2]) 
                return board[3*i];
            if (board[i] != 0 && board[i] == board[i + 3] && board[i] == board[i + 6])
                return board[i];
        }
        if (board[0] != 0 && board[0] == board[4] && board[0] == board[8])
            return board[0];
        if (board[2] != 0 && board[2] == board[4] && board[2] == board[6])
            return board[0];

        if (movesNum == 7 || movesNum == 8) {
            return fillBoard(board);
        } else if (movesNum == 9) {
            return 3;
        }
        return 0;
    }

    private int fillBoard(int[] board) {
        int result;
        int j = 1;
        int[] newBoard = new int[9];
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0)
                newBoard[i] = j++;
            else
                newBoard[i] = board[i];
        }
        result = gameStatusHelper(newBoard, 9) == 3 ? 3 : 0;
        if (result == 3) {
            for (int i = 0; i < 9; i++) {
                if (board[i] == 0)
                    newBoard[i] = j--;
                else
                    newBoard[i] = board[i];
            }
            result = gameStatusHelper(newBoard, 9) == 3 ? 3 : 0;
        }
        return result;
    }
 
    /**
     * print the board
     */
    public void printBoard() {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            System.out.print(getBlock(board[i]) + " ");
            if (count++ == 2) {
                count = 0;
                System.out.println();
            }
        }
    }

    private String getBlock(int code) {
        switch (code) {
            case 0:
                return emptySymbol;
            case 1:
                return player1Symbol;
            case 2:
                return player2Symbol;
            default:
                return " ";
        }
    }
}
