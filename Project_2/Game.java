package Project_2;
import java.util.Scanner;

/**
 * Task 1
 * You will walk through all your game process here
 * 
 * Task 2
 * You will use player handler class to create new player as well as saving them in local storage
 * Check the PlayerHandler.java file to see the function of its public method and the way to use them
 * Remember to handle all the exception
 */
public class Game {
    /**
     * Task 2
     * Ignore this in task 1, you will initialze and use this in task 2
     */
    private PlayerHandler handler;
    private Scanner scan;
    private TikTacToe game;

    private final boolean isDownward = true;

    /**
     * Initialize this class
     */
    public Game() throws Exception {
        handler = new PlayerHandler();
        scan = new Scanner(System.in);
        game = new TikTacToe();
    }

    public void gameStart() {
        loggin();
        intro();
        try {
            play();
            status();
            finish();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void finish() throws Exception {
        handler.savePlayerInfo();
        System.out.println("This game is finish, thank you for playing");
    }

    private void status() throws Exception {
        System.out.println("Do you want to see the players' status?");
        if (getBooleanResponse(scan)) {
            System.out.println("Below are the players' status:");
            handler.printPlayer1Info();
            handler.printPlayer2Info();    
        }
        System.out.println("Do you want to see the players' record?");
        if (getBooleanResponse(scan)) {
            System.out.println("Below are the players' records: ");
            handler.printPlayer1Record();
            handler.printPlayer2Record();
        }
    }

    private void play() throws Exception {
        System.out.println("Are you ready for this game? (Yes/No)");
        String response = getResponse(scan, "yes", "no", "kill me", "i hate this game", "");
        printReply(response);
        boolean isPlayer1 = true;
        while (game.gameStatus() == 0) {
            playerMove(isPlayer1 ? handler.getPlayer1().getName() : handler.getPlayer2().getName(), isPlayer1 ? 1 : 2);
            isPlayer1 = !isPlayer1;
        }
        switch (game.gameStatus()) {
            case 1:
                System.out.println("Player 1 win!");
                handler.player1Win();
                break;
            case 2:
                System.out.println("Player 2 win!");
                handler.player2Win();
                break;
            case 3:
                System.out.println("Unfortunately, this game is resulted in a hang");
                break;
            default:
                break;
        }
    }

    private void printReply(String response) {
        switch (response) {
            case "yes":
                System.out.println("Let's go!!");
                break;
            case "no":
                System.out.println("come on you can do it!");
                break;
            case "kill me":
                System.out.println("Don't be so negative");
                break;
            case "i hate this game":
                System.out.println("Well I hate it too");
                break;
            default:
                System.out.println("Did you just enter something random?");
                break;
        }
    }

    private void playerMove(String playerName, int player) {
        while (true) {
            System.out.printf("%s, please enter the block to place your item\n", playerName);
            printNumberMap();
            try {
                game.move(processIsDownword(getIntResponse(scan)), player);
                game.printBoard();
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                game.printBoard();
            }
        }
    }

    private int processIsDownword(int num) {
        if (!isDownward) {
            return num - 1;
        } else {
            num--;
            switch (num / 3) {
                case 2:
                    return num % 3;
                case 1:
                    return 3 + num % 3;
                case 0:
                    return 6 + num % 3;
                default:
                    return 10;
            }
        }
    }

    private void intro() {
        System.out.println("Now the game start, you would need to enter 1 to 9 to place an item into each of the blocks in the map");
        System.out.println("The following are the number map: ");
        printNumberMap();
        System.out.println("If three of the items from the same player are connected horizontally, vertically, or diagonally, that player win");
    }

    private void printNumberMap() {
        if (!isDownward) {
            int count = 0;
            for (int i = 0; i < 9; i++) {
                System.out.print((i + 1) + " ");
                if (count++ == 2) {
                    count = 0;
                    System.out.println();
                }
            }
        } else {
            for (int i = 7; i > 0; i-=3) {
                for (int j = i; j < i + 3; j++) {
                    System.out.print(j + " ");
                }
                System.out.println();
            }
        }
    }

    private void loggin() {
        System.out.println("---- Game Start ----");
        System.out.println("This is a Tik Tac Toe Game, do two of you already have game accounts?");
        if (getBooleanResponse(scan)) {
            System.out.println("The following are the list of all our accounts");
            handler.printAllPlayers();
            getPlayerAccount(scan, 1);
            getPlayerAccount(scan, 2);
        } else {
            createAccount(scan, 1);
            createAccount(scan, 2);
        }
    }

    private void getPlayerAccount(Scanner scan, int player) {
        String response;
        while (true) {
            System.out.printf("Please enter the name of player %d's account\n", player);
            response = getStringResponse(scan);
            try {
                setPlayer(player, response);
                System.out.printf("Congrat, %s is logged into the system as player %d\n", response, player);
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Do you want to create a new account instead?");
                if (getBooleanResponse(scan)) {
                    boolean success = createPlayerAccount(scan, player);
                    if (success)
                        return;
                }
            }
        }
    }

    private void createAccount(Scanner scan, int player) {
        while (!createPlayerAccount(scan, player)) {

        }
    }

    private boolean createPlayerAccount(Scanner scan, int player) {
        System.out.println("Please enter the name of your new account");
        String response = getStringResponse(scan);
        try {
            handler.createNewPlayer(response);
            System.out.println("Congrat, a new account is created");
            setPlayer(player, response);
            System.out.printf("Congrat, %s is logged into the system as player %d\n", response, player);
            return true;
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
            return false;
        }
    }

    private void setPlayer(int player, String name) throws Exception {
        if (player == 1) 
            handler.setPlayer1(name);
        else if (player == 2)
            handler.setPlayer2(name);
    }

    private boolean getBooleanResponse(Scanner scan) {
        System.out.println("Please enter Yes or No");
        String response = getResponse(scan, "yes", "no");
        if (response.equals("yes"))
            return true;
        else if (response.equals("no"))
            return false;
        return false;
    }

    private String getResponse(Scanner scan, String... expectValues) {
        String response;
        while (true) {
            response = scan.nextLine();
            for (String value: expectValues) {
                if (response.toLowerCase().contains(value)) {
                    return value;
                }
            }
            System.out.println("Please enter valid reponse");
        }
    }

    private String getStringResponse(Scanner scan) {
        String response;
        while (true) {
            try {
                response = scan.next();
                scan.nextLine();
                return response;
            } catch (Exception e) {
                System.out.println("Please enter valid string");
            }
        }
    }

    private int getIntResponse(Scanner scan) {
        int response;
        while (true) {
            try {
                response = scan.nextInt();
                scan.nextLine();
                return response;
            } catch (Exception e) {
                System.out.println("Please enter valid number");
            }
        }
    }
    
}
