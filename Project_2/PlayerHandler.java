package Project_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class PlayerHandler {
    private File playerInfoFile;
    private Map<String, Integer[]> playerInfo;
    private Player player1;
    private Player player2;

    private final String playerInfoFileDefaultName = "PlayerInfo.txt";
    private final String clearDataPassword = "k766sd";

    public PlayerHandler() throws FileNotFoundException {
        playerInfoFile = new File(playerInfoFileDefaultName);
        fetchPlayerInfo();
    }

    public PlayerHandler(String playerInfoFileName) throws FileNotFoundException{
        playerInfoFile = new File(playerInfoFileName);
        fetchPlayerInfo();
    }

    /**
     * print all the name of existed players
     */
    public void printAllPlayers() {
        System.out.println("Below are all the account names");
        for (String name: playerInfo.keySet()) {
            System.out.println(name);
        }
    }

    /**
     * set player1 property by its name
     * @param name name of the account of that player
     * @throws Exception the name is not found in the system
     */
    public void setPlayer1(String name) throws Exception {
        player1 = getPlayer(name);
    }

    /**
     * set player1 property by its name
     * @param name name of the account of that player
     * @throws Exception the name is not found in the system
     */
    public void setPlayer2(String name) throws Exception {
        player2 = getPlayer(name);
    }

    /**
     * call this when player 1 win
     */
    public void player1Win() throws Exception {
        if (player1 == null || player2 == null) {
            throw new Exception("Two players are not fully initialized");
        }
        player1.win();
        player2.lose();
        updatePlayersInfo();
    }

    /**
     * call this when player 2 win
     */
    public void player2Win() throws Exception {
        if (player1 == null || player2 == null) {
            throw new Exception("Two players are not fully initialized");
        }
        player1.lose();
        player2.win();
        updatePlayersInfo();
    }

    /**
     * create a new account for new player
     * @param name name of the new player
     * @throws Exception when player name is already existed in the system
     */
    public void createNewPlayer(String name) throws Exception {
        if (playerInfo.containsKey(name)) {
            throw new Exception("Player name is already exist");
        }
        Integer[] gameRecord = {0, 0};
        playerInfo.put(name, gameRecord);
    }

    /**
     * this save the player information
     * @throws IOException
     */
    public void savePlayerInfo() throws IOException {
        FileWriter writer = new FileWriter(playerInfoFile);
        for (String name: playerInfo.keySet()) {
            Integer[] record = playerInfo.get(name);
            writer.write(name);
            writer.append(' ');
            writer.write(record[0].toString());
            writer.append(' ');
            writer.write(record[1].toString());
            writer.append("\n");
        }
        writer.flush();
        writer.close();
    }

    /**
     * print the information of player 1
     */
    public void printPlayer1Info() throws Exception {
        if (player1 == null) {
            throw new Exception("player 1 is not initialized");
        }
        System.out.println(player1);
    }

    /**
     * print the information of player 2
     */
    public void printPlayer2Info() throws Exception {
        if (player2 == null) {
            throw new Exception("player 2 is not initialized");
        }
        System.out.println(player2);
    }

    /**
     * print the win and lose record of player 1
     */
    public void printPlayer1Record() throws Exception {
        if (player1 == null) {
            throw new Exception("player 1 is not initialized");
        }
        System.out.println(player1.getRecord());
    }

    /**
     * print the win and lose record of player 2
     */
    public void printPlayer2Record() throws Exception {
        if (player2 == null) {
            throw new Exception("player 2 is not initialized");
        }
        System.out.println(player2.getRecord());
    }

    /**
     * return player 1 object
     * @return player 1 object
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * return player 2 object
     * @return player 2 object
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Clear all the data stored in the info file
     * @param password enter the password to clear the data
     * @throws Exception exception when password is not correct or file is not found
     */
    public void clearAllData(String password) throws Exception {
        if (password.equals(clearDataPassword)) {
            FileWriter writer = new FileWriter(playerInfoFile);
            writer.flush();
            writer.close();
        } else {
            throw new Exception("Password you enter for clearing all data is not correct");
        }
    }
 
    private void updatePlayersInfo() {
        Player[] players = {player1, player2};
        for (Player player: players) {
            Integer[] record = {player.getWinNum(), player.getLoseNum()};
            playerInfo.put(player.getName(), record);
        }
    }

    private Player getPlayer(String name) throws Exception {
        Integer[] record = playerInfo.get(name);
        if (record == null) {
            throw new Exception("Account name is not found in the system");
        }
        return new Player(name, record[0], record[1]);
    }

    private void fetchPlayerInfo() throws FileNotFoundException {
        Scanner scan = new Scanner(playerInfoFile);
        playerInfo = new HashMap<>();
        while (scan.hasNextLine()) {
            String name = scan.next();
            int win = scan.nextInt();
            int lose = scan.nextInt();
            Integer[] gameRecord = {win, lose};
            playerInfo.put(name, gameRecord);
            scan.nextLine();
        }
        scan.close();
    }

    private boolean playerInfoIsFetch() {
        return playerInfo != null;
    }
}
