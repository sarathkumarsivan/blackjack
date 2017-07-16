package com.casino.blackjack.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A user of the Blackjack game. The user could be either player or dealer. User is extended from
 * Player. A user is created based on the boolean value {@code isDealer}.
 *
 */
public class User extends Player {

  /**
   * Constructs a user. The user could be either player or dealer.
   * 
   * @param isDealer the value to be used to construct a player or dealer.
   */
  public User(boolean isDealer) {
    this.isDealer = isDealer;
    if (isDealer) {
      name = "Dealer";
    } else if (!isDealer) {
      name = "Player";
    }
  }

  /**
   * The most common decision a player must make during the game is whether to draw another card to
   * the hand ("hit"), or stop at the current total ("stand"). If this user is a dealer it
   * internally invokes the dealerDecide method with the hand value.
   */
  @Override
  public void decide() {
    handValue = checkHandValue();
    if (isDealer == true) {
      dealerDecide(checkHandValue());
    } else {
      playerDecide(checkHandValue());
    }
  }

  /**
   * Dealer decides whether to draw another card to the hand ("hit"), or stop at the current total
   * ("stand").
   * 
   * @param value the hand value to be used to make the decision.
   */
  private void dealerDecide(int value) {
    if (value > 17) {
      setStand(true);
      System.out.println("\nDealer has decided to stand.");
    } else {
      System.out.println("\nDealer has decided to hit.");
    }
  }

  /**
   * Player decides whether to draw another card to the hand ("hit"), or stop at the current total
   * ("stand").
   * 
   * @param value the hand value to be used to make the decision.
   */
  private void playerDecide(int value) {
    System.out.println(String.format("Your hand is worth %d", value));
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    boolean validInput = false;
    while (validInput == false) {
      try {
        System.out.print("Do you want to hit or stand? (hit/stand): ");
        String input = reader.readLine();
        if (!(input.toLowerCase().equals("hit") || input.toLowerCase().equals("stand"))) {
          throw new IOException();
        } else if (input.toLowerCase().equals("hit")) {
          setStand(false);
          validInput = true;
        } else if (input.toLowerCase().equals("stand")) {
          setStand(true);
          validInput = true;
        }
      } catch (IOException exception) {
        System.out.println("Please try again. Type \"hit\" or \"stand\".");
      }
    }
  }

}
