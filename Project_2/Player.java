package Project_2;

/**
 * Do not modify the content from this file
 */
public class Player {
    private String name;
    private int winNum;
    private int loseNum;
    private int rank;

    private final String[] rankMap = {"Rookie", "Beginner", "Intermediate", "Advance", "Master"};

    public Player(String name, int winNum, int loseNum) {
        this.name = name;
        this.winNum = winNum;
        this.loseNum = loseNum;
        calculateRank();
    }

    private void calculateRank() {
        int rankNum = winNum - loseNum;
        if (rankNum < 1) {
            this.rank = (winNum + loseNum) > 10 ? 1 : 0;
        } else if (rankNum < 3) {
            this.rank = 2;
        } else if (rankNum < 5) {
            this.rank = 3;
        } else {
            this.rank = 4;
        }
    }

    @Override
    public String toString() {
        return String.format("Player: %s, Rank: %s", getName(), getRank());
    }

    public String getRecord() {
        return String.format("Player: %s, Win: %d, Lose: %d", getName(), getWinNum(), getLoseNum());
    }
 
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWinNum() {
        return this.winNum;
    }

    public void win() {
        this.winNum++;
        calculateRank();
    }

    public int getLoseNum() {
        return this.loseNum;
    }

    public void lose() {
        this.loseNum++;
        calculateRank();
    }

    public String getRank() {
        return rankMap[rank];
    }

}
