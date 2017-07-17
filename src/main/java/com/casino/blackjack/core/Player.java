package com.casino.blackjack.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A Player is an abstraction of players of Blackjack game.
 *
 */
public abstract class Player {

  /**
   * If the dealer or player goes over the point value of 21 it is called a bust and they will lose
   * the hand. If the player and the dealer both bust the player will still lose the hand, which is
   * the house advantage in the game of Blackjack.
   */
  private boolean bust;

  /**
   * Hand of the player who plays Blackjack
   */
  private ArrayList<Card> hand;

  /**
   * Hand value of the player.
   */
  protected int handValue;

  /**
   * True if the hand of this player has a Blackjack and false otherwise.
   */
  private boolean blackJack;

  /**
   * True if this player is a dealer and false otherwise.
   */
  public boolean isDealer;

  /**
   * Name of the player. It could be dealer or player.
   */
  public String name;

  /**
   * If player like the cards he has and want to play those cards he can stand, which means taking
   * no more cards. To let the dealer know that he want to stand wave his hands across his area of
   * the table and in a single deck game place his cards under the wager he made.
   */
  private boolean stand = false;

  /**
   * Amount of player money to be used to place a wager or a bet.
   */
  public double bet;

  /**
   * Construct a player. Player hand is initially empty.
   */
  public Player() {
    hand = new ArrayList<Card>();
  }

  /**
   * Accepts both cards to the player hand. i.e. the two cards are dealt into each player's hand If
   * the player's hand has a value of 21 at this point, then the player wins. (This is called a
   * "Blackjack".)
   * 
   * @param card1 the first card to be added to the player hand.
   * @param card2 the second card to be added to the player hand.
   */
  public void acceptHand(Card card1, Card card2) {
    hand.add(card1);
    hand.add(card2);
  }

  void acceptHit(Card card) {
    hand.add(card);
  }

  public abstract void decide();

  /**
   * Returns the hand value.
   * 
   * @return
   */
  protected int checkHandValue() {
    int numOfAces = 0;
    int handValue = 0;

    for (int i = 0; i < hand.size(); i++) {

      switch (hand.get(i).getNum()) {

        case 0:
          handValue += 2;
          break;
        case 1:
          handValue += 3;
          break;
        case 2:
          handValue += 4;
          break;
        case 3:
          handValue += 5;
          break;
        case 4:
          handValue += 6;
          break;
        case 5:
          handValue += 7;
          break;
        case 6:
          handValue += 8;
          break;
        case 7:
          handValue += 9;
          break;
        case 8:
          handValue += 10;
          break;
        case 9:
          handValue += 10; // Jack
          break;
        case 10:
          handValue += 10; // Queen
          break;
        case 11:
          handValue += 10; // King
          break;
        case 12:
          handValue += 11; // Ace
          numOfAces++;
          break;
      }
    }

    /**
     * Decides whether to use Aces as 11s or 1s. An Ace can also be counted as 11, which is what is
     * needed to get a Blackjack. There can be no Blackjack without an Ace. An Ace counts as an 11
     * unit a player has an Ace and then with another card has a hand that over 21, which is when
     * the Ace is now seen as a 1.
     */

    if (numOfAces != 0) {
      int aceIndex = numOfAces;

      while (handValue > 21) {
        if (aceIndex == 0) {
          break;
        }
        handValue -= 10;
        aceIndex--;
      }
    }

    if (handValue > 21) {
      bust = true;
    } else if (handValue == 21 && hand.size() == 2 && hand.get(0).getNum() != 8
        && hand.get(1).getNum() != 8) {
      blackJack = true;
    }
    return handValue;
  }

  /**
   * Place a bet with the player money. In order to play blackjack and to be dealt into a round, a
   * player needs to place a wager or a bet. In terms of payouts, a winning hand in blackjack will
   * receive a 1:1 payout. This means that if a player bets $20 and they win the hand, they will
   * receive $40 in payout. Player is not allowed to place a bet without enough money.
   * 
   * @param amount the player money to be used to place a wager or a bet.
   */
  public void placeBet(double amount) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    boolean inputValid = false;

    while (inputValid == false) {
      try {
        System.out.print("How much do you want to bet?: ");
        String input = reader.readLine();

        if (Double.parseDouble(input) <= amount) {
          bet = Double.parseDouble(input);
          inputValid = true;
        } else if (Double.parseDouble(input) > amount) {
          throw new NumberFormatException();
        }

      } catch (NumberFormatException exception) {
        System.out
            .println("Please try again. Type a number not greater than your current balance.");
      } catch (IOException exception) {
        System.out.println("Please try again. Type a number.");
      }
    }
  }

  public boolean isBust() {
    return bust;
  }

  public void setBust(boolean bust) {
    this.bust = bust;
  }

  public ArrayList<Card> getHand() {
    return hand;
  }

  public void setHand(ArrayList<Card> hand) {
    this.hand = hand;
  }

  public boolean hasBlackJack() {
    return blackJack;
  }

  public void setBlackJack(boolean blackJack) {
    this.blackJack = blackJack;
  }

  public boolean isStand() {
    return stand;
  }

  public void setStand(boolean stand) {
    this.stand = stand;
  }

}
