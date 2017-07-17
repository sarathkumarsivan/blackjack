package com.casino.blackjack.exec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import com.casino.blackjack.core.Game;
import com.casino.blackjack.util.Scoreboard;

/**
 * This program lets the user play Blackjack game. The computer acts as the dealer. The user has a
 * stake of $1000, and makes a bet on each game. The user can leave at any time, or will be kicked
 * out when he loses all the money. House rules: Dealer must hit on soft 17. Single Deck is being
 * used. The deck is shuffled every 6 rounds. Player is not allowed to split cards. Keeps track of
 * win percentage of the player. Dealer wins ties. A new deck of cards is used for each game.
 */
public class Blackjack {

  /**
   * The main method which calls the run method to start the Blackjack Game.
   * 
   * @param args command line arguments
   */
  public static void main(String[] args) {
    Blackjack blackjack = new Blackjack();
    blackjack.run();
  }

  /**
   * Starts the game of Blackjack. The user has a stake of $1000, and makes a bet on each game.
   */
  public void run() {
    Scoreboard scoreboard = Scoreboard.getInstance();
    boolean gameOver = false;
    double playerMoney = 1000; // $1000 -> the amount of money the user has

    while (gameOver == false) {
      Game game = new Game();
      scoreboard.setRound(scoreboard.getRound() + 1);

      if (playerMoney > 0) {
        DecimalFormat formatter = new DecimalFormat("#.00");
        System.out.println(String.format("You have $%s left.", formatter.format(playerMoney)));
      } else if (playerMoney <= 0) {
        System.out.println("Sorry. You are out of money!");
        break;
      }

      game.getPlayer().placeBet(playerMoney);
      game.playBlackjack();

      if (game.getWinner().equals("player")) {
        playerMoney += game.getPlayer().bet;
        scoreboard.setWin(scoreboard.getWin() + 1);
      } else if (game.getWinner().equals("dealer")) {
        playerMoney -= game.getPlayer().bet;
        scoreboard.setLose(scoreboard.getLose() + 1);
      }

      if (playerMoney > 0) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DecimalFormat formatter = new DecimalFormat("#.00");
        boolean validInput = false;

        while (validInput == false) {
          try {
            System.out.print(
                String.format("\nYou have $%s left. Do you want to keep playing? (yes or no): ",
                    formatter.format(playerMoney)));

            String input = reader.readLine();
            if (!(input.toLowerCase().equals("yes") || input.toLowerCase().equals("no"))) {
              throw new IOException();
            } else if (input.toLowerCase().equals("yes")) {
              gameOver = false;
              validInput = true;
            } else if (input.toLowerCase().equals("no")) {
              gameOver = true;
              validInput = true;
            }
          } catch (IOException ex) {
            System.out.print("Please try again. Type \"yes\" or \"no\".");
          }

        }
      } else if (playerMoney <= 0) {
        System.out.println("Sorry. You are out of money!");
        break;
      }
    }
    Scoreboard.getInstance().display();
  }

}
