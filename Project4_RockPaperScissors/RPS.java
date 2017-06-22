package Project4_RockPaperScissors;

import java.util.Random;

public class RPS {
    private int numCompWins;
    private int numUserWins;
    private int numOfTies;
    private boolean betting;
    private int betAmount;
    private int userBalance;

    public enum Moves {ROCK, PAPER, SCISSORS}
    public enum GameResult {TIE, USER_WIN, COMP_WIN}


    public RPS(int betAmount) {
        this.numCompWins = 0;
        this.numUserWins = 0;
        this.numOfTies = 0;
        this.userBalance = 0;
        if(betAmount !=0){
            betting = true;
        }
        this.betAmount = betAmount;
    }
    public RPS(){
        this(0);
    }

    public boolean isBetting() {
        return betting;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public int getUserBalance() {
        return userBalance;
    }

    public int getNumCompWins() {
        return numCompWins;
    }

    public int getNumUserWins() {
        return numUserWins;
    }

    public int getNumOfTies() {
        return numOfTies;
    }

    public GameResult findWinner(Moves userMove, Moves compMove) {

        if (userMove == compMove) {
            numOfTies++;
            return GameResult.TIE;
        } else if (userMove == Moves.ROCK) {
            if (compMove == Moves.PAPER) {
                numCompWins++;
                userBalance -= betAmount;
                return GameResult.COMP_WIN;
            } else {
                numUserWins++;
                userBalance += betAmount;
                return GameResult.USER_WIN;
            }
        } else if (userMove == Moves.SCISSORS) {
            if (compMove == Moves.ROCK) {
                numCompWins++;
                userBalance -= betAmount;
                return GameResult.COMP_WIN;
            } else {
                numUserWins++;
                userBalance += betAmount;
                return GameResult.USER_WIN;
            }
        } else {
            if (compMove == Moves.ROCK) {
                numUserWins++;
                userBalance += betAmount;
                return GameResult.USER_WIN;
            } else {
                numCompWins++;
                userBalance -= betAmount;
                return GameResult.COMP_WIN;
            }
        }
    }


    public Moves generateComputerPlay() {
        Random random = new Random();
        return Moves.values()[(random.nextInt(Moves.values().length))];
    }
}



