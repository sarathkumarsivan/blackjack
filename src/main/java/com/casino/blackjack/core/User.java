package com.casino.blackjack.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class User extends Player {

  public User(boolean isDealer) {
    this.isDealer = isDealer;
    if (isDealer) {
      name = "Dealer";
    } else if (!isDealer) {
      name = "Player";
    }
  }

  @Override
  public void decide() {
    handValue = checkHandValue();
    if (isDealer == true) {
      dealerDecide(checkHandValue());
    } else {
      playerDecide(checkHandValue());
    }
  }

  private void dealerDecide(int value) {
    if (value > 17) {
      setStand(true);
      System.out.println("\nDealer has decided to stand.");
    } else {
      System.out.println("\nDealer has decided to hit.");
    }
  }

  private void playerDecide(int value) {
    System.out.println(String.format("Your hand is worth ", value));
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    boolean inputValid = false;
    while (inputValid == false) {
      try {
        System.out.print("Do you want to hit or stand? (hit/stand): ");
        String input = reader.readLine();
        if (!(input.toLowerCase().equals("hit") || input.toLowerCase().equals("stand"))) {
          throw new IOException();
        } else if (input.toLowerCase().equals("hit")) {
          setStand(false);
          inputValid = true;
        } else if (input.toLowerCase().equals("stand")) {
          setStand(true);
          inputValid = true;
        }
      } catch (IOException ex) {
        System.out.println("Please try again. Type \"hit\" or \"stand\".");
      }
    }
  }

}
