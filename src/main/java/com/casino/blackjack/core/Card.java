package com.casino.blackjack.core;

/**
 * A card represents one of the 52 cards in a standard deck of playing cards. Each card has a suit
 * and a value. When playing Blackjack the suits of the card do not matter. An Ace of clubs and a
 * Jack of clubs is the exact same (Blackjack) as an Ace of hearts and a 10 of spades.
 *
 */
public class Card {

  /**
   * {@code suit} represents the integer value of Suit in a standard deck of 52 cards. A standard
   * deck of 52 cards contains four suits: hearts, clubs, diamonds, and spades.
   */
  private int suit;

  /**
   * {@code num} represents the integer value on the card. For each suit, there are nine numbered
   * cards, 2 through 10, and four face cards, a King, Queen, Jack, and an Ace.
   */
  private int num;

  /**
   * Constructs a card with the specified value and suit. Value must be between 1 and 13. Suit must
   * be between 0 and 3. If the parameters are outside these ranges, the constructed card object
   * will be invalid.
   * 
   * @param suit the value of suit to be used to construct the card.
   * @param num the value of card number to be used to construct the card.
   */
  public Card(int suit, int num) {
    this.suit = suit;
    this.num = num;
  }

  /**
   * Returns the value of suit where this card belongs.
   * 
   * @return suit the value of suit where this card belongs.
   */
  public int getSuit() {
    return suit;
  }

  /**
   * Returns the value of card number.
   * 
   * @return the value of card number.
   */
  public int getNum() {
    return num;
  }

  /**
   * Return a String representing the card's suit and value.
   */
  public String toString() {
    String suitstr = "", numstr = "";

    switch (num) {
      case 0:
        numstr = "2";
        break;
      case 1:
        numstr = "3";
        break;
      case 2:
        numstr = "4";
        break;
      case 3:
        numstr = "5";
        break;
      case 4:
        numstr = "6";
        break;
      case 5:
        numstr = "7";
        break;
      case 6:
        numstr = "8";
        break;
      case 7:
        numstr = "9";
        break;
      case 8:
        numstr = "10";
        break;
      case 9:
        numstr = "Jack";
        break;
      case 10:
        numstr = "Queen";
        break;
      case 11:
        numstr = "King";
        break;
      case 12:
        numstr = "Ace";
        break;
    }

    switch (suit) {
      case 0:
        suitstr = "Hearts";
        break;
      case 1:
        suitstr = "Diamonds";
        break;
      case 2:
        suitstr = "Clubs";
        break;
      case 3:
        suitstr = "Spades";
        break;
    }

    return String.format("%s of %s", numstr, suitstr);
  }
}
