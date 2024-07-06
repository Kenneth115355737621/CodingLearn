package Project_2;
/**
 * Name: 
 * You need to write a Tik Tac Toe game following object oriented programming principle.
 * 
 * Task 1: Create a Tik Tac Toe game
 * In this task you will be implementing two classes, Game.java and TicTacToe.java
 * In the TikTacToe class, you will be saving all the moves of the players,
 * and determinating whether any player win or it result in a hang.
 * In the Game class, you will be writing an entire game process, designing how the program interact with the user using Scanner class
 * In this Run class, you will be running the game from the main method
 * All the detailed implementation requirement is inside the file
 * 
 * Task 2: Integrating the Player Rank System into your game
 * In this task you will be using the two class Player.java and PlayerHandler.java that is already implemented for you.
 * In Game you will be initiating the PlayerHandler Class, which has stock method to create new player, make player win or lose, and save 
 * the player info into the local storage, which can be access in another game.
 * Some excample of how to use the PlayerHandler Class is in the test method below.
 * Please do not modify the content of these two class
 * 
 * Extra Credit:
 * Someone has invented a new Tik Tac Toe
 * When you already put all three moves on the board, you can still put your fourth move.
 * However, the first move you put would be erase
 * This avoid the game to result in a hang
 * Try modify your code to implement this new game
 */
public class Run {
    public static void main(String[] args) {
        // test();

    }

    public static void test() {
        PlayerHandler handler;
        try {
            handler = new PlayerHandler();
            handler.printAllPlayers();
            // handler.clearAllData("k766sd");
            // handler.createNewPlayer("Thomas");
            // handler.createNewPlayer("Kenneth");
            handler.setPlayer1("Thomas");
            handler.printPlayer1Info();
            handler.printPlayer1Record();
            handler.setPlayer2("Kenneth");
            handler.player1Win();
            handler.player1Win();
            handler.printPlayer1Info();
            handler.printPlayer1Record();
            handler.printPlayer2Info();
            handler.printPlayer2Record();

            handler.savePlayerInfo();
        } catch (Exception e) {
            System.out.println("error occur");
            System.out.println(e.getMessage());
            return;
        }
    }
}
