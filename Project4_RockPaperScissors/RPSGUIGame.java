package Project4_RockPaperScissors;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import static javax.swing.JOptionPane.YES_OPTION;

public class RPSGUIGame extends JFrame {

    // buttons for the user to enter their move
    private JButton rockButton, paperButton, scissorsButton;

    // labels to display the number of wins/losses/ties
    private JLabel statusC, statusU, statusT;

    // images and labels to display the computer and user's moves and the outcome of a match-up
    private ImageIcon rockImage, paperImage, scissorsImage;
    private JLabel compPlay, userPlay;
    private JLabel outcome, userBalance;

    // the game object
    private RPS game;

    public RPSGUIGame(int bet) {

        // initializes the window
        super("Rock, Paper, Scissors, Go!");
        setSize(400, 300);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(Color.black);
        setResizable(false);

        // creates the game object
        // 	NOTE: IF COMPLETING THE EXTRA CREDIT, YOU WILL NEED TO ADD A PARAMETER TO REPRESENT THE BET AMOUNT
        game = new RPS(bet);

        // creates the labels for displaying the computer and user's move;
        // the images for the moves and the outcome of a match-up will be displayed
        // in a single panel
        rockImage = new ImageIcon("rock.jpg");
        paperImage = new ImageIcon("paper.jpg");
        scissorsImage = new ImageIcon("scissors.jpg");

        compPlay = new JLabel();
        compPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
        compPlay.setHorizontalTextPosition(SwingConstants.CENTER);
        compPlay.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        compPlay.setForeground(Color.cyan);
        userPlay = new JLabel();
        userPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
        userPlay.setHorizontalTextPosition(SwingConstants.CENTER);
        userPlay.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        userPlay.setForeground(Color.cyan);

        outcome = new JLabel();
        outcome.setHorizontalTextPosition(SwingConstants.CENTER);
        outcome.setForeground(Color.pink);

        JPanel imageOutcomePanel = new JPanel();
        imageOutcomePanel.setBackground(Color.black);
        imageOutcomePanel.setLayout(new BorderLayout());
        imageOutcomePanel.add(compPlay, BorderLayout.WEST);
        imageOutcomePanel.add(userPlay, BorderLayout.EAST);
        imageOutcomePanel.add(outcome, BorderLayout.SOUTH);

        // creates the labels for the status of the game (number of wins, losses, and ties);
        // the status labels will be displayed in a single panel
        statusC = new JLabel("Computer Wins: " + game.getNumCompWins());
        statusU = new JLabel("User Wins: " + game.getNumUserWins());
        statusT = new JLabel("Ties: " + game.getNumOfTies());
        userBalance = new JLabel("Balance: " + game.getUserBalance());
        statusC.setForeground(Color.white);
        statusU.setForeground(Color.white);
        statusT.setForeground(Color.white);
        userBalance.setForeground(Color.white);
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(Color.black);
        statusPanel.add(statusC);
        statusPanel.add(statusU);
        statusPanel.add(statusT);
        if(game.isBetting()) {
            statusPanel.add(userBalance);
        }

        // the play and status panels are nested in a single panel
        JPanel gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(250, 250));
        gamePanel.setBackground(Color.black);
        gamePanel.add(imageOutcomePanel);
        gamePanel.add(statusPanel);

        // creates the buttons and displays them in a control panel;
        // one listener is used for all three buttons
        rockButton = new JButton("Play Rock");
        rockButton.addActionListener(new GameListener());
        paperButton = new JButton("Play Paper");
        paperButton.addActionListener(new GameListener());
        scissorsButton = new JButton("Play Scissors");
        scissorsButton.addActionListener(new GameListener());

        JPanel controlPanel = new JPanel();
        controlPanel.add(rockButton);
        controlPanel.add(paperButton);
        controlPanel.add(scissorsButton);
        controlPanel.setBackground(Color.black);

        // the gaming and control panel are added to the window
        contentPane.add(gamePanel, BorderLayout.CENTER);
        contentPane.add(controlPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /* determines which button was clicked and updates the game accordingly */
    private class GameListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            RPS.Moves userPlayMove = determineUserPlayMove(event);
            RPS.Moves compPlayMove = determineCompPlayMove();
            RPS.GameResult gameStatus = game.findWinner(userPlayMove,compPlayMove);
            updateStatusDisplay(gameStatus, userPlayMove, compPlayMove);
        }

        private void updateStatusDisplay(RPS.GameResult gameStatus, RPS.Moves userMove, RPS.Moves compMove) {
            if (gameStatus == RPS.GameResult.TIE) {
                outcome.setText("It\'s a tie!");
            } else {
                String outputText;
                if (gameStatus == RPS.GameResult.USER_WIN) {
                    if (compMove == RPS.Moves.ROCK) {
                        outputText = "Paper smothers Rock!";
                    } else if (compMove == RPS.Moves.PAPER) {
                        outputText = "Scissors shred Paper!";
                    } else {
                        outputText = "Rock smashes Scissors!";
                    }

                    outcome.setText(outputText +" You win!");

                } else if (gameStatus == RPS.GameResult.COMP_WIN) {
                    if (userMove == RPS.Moves.ROCK) {
                        outputText ="Paper smothers Rock!" ;
                    } else if (userMove == RPS.Moves.PAPER) {
                        outputText = "Scissors shred Paper!";
                    } else {
                        outputText = "Rock smashes Scissors!";
                    }

                    outcome.setText(outputText +  " You lose!");
                }
            }

            statusT.setText("Ties: " + game.getNumOfTies());
            statusU.setText("User Wins: " + game.getNumUserWins());
            statusC.setText("Computer Wins: " + game.getNumCompWins());
            userBalance.setText("Your Balance: $" + game.getUserBalance());
        }
    }

    private RPS.Moves determineCompPlayMove() {
        RPS.Moves compPlayMove = game.generateComputerPlay();
        if (compPlayMove == RPS.Moves.ROCK) {
            compPlay.setIcon(rockImage);
        } else if (compPlayMove == RPS.Moves.PAPER) {
            compPlay.setIcon(paperImage);
        } else {
            compPlay.setIcon(scissorsImage);
        }

        compPlay.setText("Computer\'s Move");
        return compPlayMove;
    }

    private RPS.Moves determineUserPlayMove(ActionEvent event) {
        RPS.Moves userPlayMove;
        if (event.getSource() == rockButton) {
            userPlayMove = RPS.Moves.ROCK;
            userPlay.setIcon(rockImage);
        } else if (event.getSource() == paperButton) {
            userPlayMove = RPS.Moves.PAPER;
            userPlay.setIcon(paperImage);
        } else  {
            userPlayMove = RPS.Moves.SCISSORS;
            userPlay.setIcon(scissorsImage);
        }
        userPlay.setText("Your Move");
        return userPlayMove;
    }


    public static void main(String args[]) {
        // create an object of your class
        int bet = 0;

        if (JOptionPane.showConfirmDialog(null,"Do you want to make a bet?") == YES_OPTION) {
            bet = Integer.parseInt(JOptionPane.showInputDialog("How do you want to bet?"));
        }
        RPSGUIGame frame = new RPSGUIGame(bet);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
