package com.casino.blackjack.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class Player {

  private boolean bust;

  private ArrayList<Card> hand;

  protected int handValue;

  private boolean blackJack;

  public boolean isDealer;

  public String name;

  private boolean stand = false;

  public double bet;

  public Player() {
    hand = new ArrayList<Card>();
  }

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


  public void getBet(double playerMoney) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    boolean inputValid = false;

    while (inputValid == false) {
      try {
        System.out.print("How much do you want to bet?: ");
        String input = reader.readLine();

        if (Double.parseDouble(input) <= playerMoney) {
          bet = Double.parseDouble(input);
          inputValid = true;
        } else if (Double.parseDouble(input) > playerMoney) {
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
