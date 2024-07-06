package Project_2;

public class Run {
    public static void main(String[] args) {
        test();

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
