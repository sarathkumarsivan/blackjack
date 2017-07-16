package com.casino.blackjack.core;

import java.util.Arrays;
import java.util.Collections;

/**
 * A deck has 52 cards total, without jokers. With jokers, it’s 54, but most serious card games have
 * you remove the two joker cards, leaving you with 52 cards. A single deck is being used in this
 * Blackjack game implementation.
 * 
 * Within these 52 cards are four suits: hearts, clubs, diamonds, and spades. In a standard deck,
 * the heart suit is represented with a red heart, the club suit is represented with a black clover,
 * the diamond suit is represented with a red diamond shape, and the spade suit is represented with
 * a black, spiked leaf-like symbol.
 * 
 */

public class Deck {

  /**
   * {@code SIZE} represents the size of a standard deck.
   */
  public static final int SIZE = 52;

  /**
   * An array of 52 Cards, representing the deck.
   */
  public Card[] deck = new Card[SIZE];

  /**
   * How many cards have been dealt from the deck.
   */
  public boolean[] status = new boolean[SIZE];

  /**
   * Create an unshuffled deck of cards.
   */
  public Deck() {
    int i = 0;

    for (int suit = 0; suit < 4; suit++) {

      for (int num = 0; num < 13; num++) {
        deck[i] = new Card(suit, num);
        i++;
      }
    }

    for (int j = 0; j < SIZE; j++) {
      status[j] = true;
    }
  }

  public Card[] getDeck() {
    return deck;
  }

  public boolean[] getStatus() {
    return status;
  }

  /**
   * Shuffle all 52 cards in this deck.
   */
  public void shuffle() {
    Collections.shuffle(Arrays.asList(deck));
  }

}
