package com.casino.blackjack.core;

import java.util.Random;

import com.casino.blackjack.util.Scoreboard;

/**
 * Game represents the basic implementation of Blackjack game. The game goes like this: First, two
 * cards are dealt into each player's hand. If the dealer's hand has a value of 21 at this point,
 * then the dealer wins. Otherwise, if the user has 21, then the user wins. (This is called a
 * "Blackjack".) Note that the dealer wins on a tie, so if both players have Blackjack, then the
 * dealer wins.
 *
 * Now, if the game has not ended, the user gets a chance to add some cards to her hand. In this
 * phase, the user sees her own cards and sees one of the dealer's two cards. (In a casino, the
 * dealer deals himself one card face up and one card face down. All the user's cards are dealt face
 * up.) The user makes a decision whether to "Hit", which means to add another card to her hand, or
 * to "Stand", which means to stop taking cards.
 * 
 * If the user Hits, there is a possibility that the user will go over 21. In that case, the game is
 * over and the user loses. If not, then the process continues. The user gets to decide again
 * whether to Hit or Stand.
 * 
 * If the user Stands, the game will end, but first the dealer gets a chance to draw cards. The
 * dealer only follows rules, without any choice. The rule is that as long as the value of the
 * dealer's hand is less than or equal to 17, the dealer Hits (that is, takes another card). The
 * user should see all the dealer's cards at this point. Now, the winner can be determined: If the
 * dealer has gone over 21, the user wins. Otherwise, if the dealer's total is greater than or equal
 * to the user's total, then the dealer wins. Otherwise, the user wins.
 */
public class Game {

  private Deck deck;

  private Player player;

  private Player dealer;

  /**
   * Winner of the Blackjack game.
   */
  private String winner = "";

  /**
   * Constructs a Blackjack game instance with a standard deck of 52 cards. Create the player and
   * the dealer as well.
   */
  public Game() {
    deck = new Deck();
    player = new User(false);
    dealer = new User(true);
  }

  public void play() {
    if ((Scoreboard.getInstance().getRound() % 6 == 1)
        && (Scoreboard.getInstance().getRound() > 6)) {
      System.out.println("Shuffling Cards...");
      deck.shuffle();
    }

    dealCards();
    displayGameState();

    while (!(player.isStand() && dealer.isStand()) && !player.isBust() && !dealer.isBust()
        && !dealer.hasBlackJack() && !player.hasBlackJack()) {
      if (player.isStand() == false) {
        player.decide();
      }
      if (dealer.isStand() == false && !dealer.isBust()) {
        if (player.handValue >= 17) {
          hit(dealer);
        } else {
          dealer.decide();
        }
      }
      if (player.isStand() == false && !dealer.isBust()) {
        hit(player);
      }
      if (dealer.isStand() == false && !player.isBust()) {
        hit(dealer);
      }
      if (dealer.hasBlackJack() || (dealer.hasBlackJack() && player.hasBlackJack())) {
        break;
      }
      if (dealer.checkHandValue() > 21) {
        dealer.setBust(true);
      }
      if (player.checkHandValue() > 21) {
        player.setBust(true);
      }
      if (player.getHand().size() == 5 && player.checkHandValue() <= 21
          && dealer.checkHandValue() <= 21) {
        displayGameState();
        break;
      }

      displayGameState();

    }

    end();
  }

  private void dealCards() {
    Random random = new Random();
    int card1 = random.nextInt(Deck.SIZE);
    int card2 = card1;

    while (card2 == card1) {
      card2 = random.nextInt(Deck.SIZE);
    }

    int card3 = card1;

    while (card3 == card1 || card3 == card2) {
      card3 = random.nextInt(Deck.SIZE);
    }

    int card4 = card1;

    while (card4 == card1 || card4 == card2 || card4 == card3) {
      card4 = random.nextInt(Deck.SIZE);
    }

    deck.status[card1] = false;
    deck.status[card2] = false;
    deck.status[card1] = false;
    deck.status[card2] = false;

    dealer.acceptHand(deck.deck[card1], deck.deck[card2]);
    player.acceptHand(deck.deck[card3], deck.deck[card4]);
  }

  private void hit(Player player) {
    Random random = new Random();
    int card = random.nextInt(Deck.SIZE);
    boolean goodCard = false;
    while (goodCard == false) {
      if (deck.getStatus()[card] == false) {
        goodCard = false;
      }

      if (deck.getStatus()[card] == true) {
        goodCard = true;
        player.acceptHit(deck.getDeck()[card]);
      } else {
        card = random.nextInt(Deck.SIZE);
      }
    }
  }

  private void displayGameState() {
    System.out.println("\nCURRENT GAME STATE");
    System.out.println("------------------");

    System.out.println("Dealer's hand:");
    for (int i = 0; i < dealer.getHand().size(); i++) {
      if (i != 0) {
        System.out.print(dealer.getHand().get(i).toString());
      } else if (i == 0) {
        System.out.print("Face down card");
      }
      if (!(i == (dealer.getHand().size() - 1))) {
        System.out.print(" + ");
      }
    }
    System.out.println();

    System.out.println("\nYour hand:");
    for (int i = 0; i < player.getHand().size(); i++) {
      System.out.print(player.getHand().get(i).toString());
      if (!(i == (player.getHand().size() - 1))) {
        System.out.print(" + ");
      }
    }
    System.out.println();

  }

  private void end() {
    if (player.getHand().size() == 5 && player.isBust() == false) {
      System.out.println("You have gotten 5 cards without busting. You Win!");
      winner = "player";
    } else if (player.hasBlackJack() && dealer.hasBlackJack()) {
      System.out.println("Both players have Blackjack! Dealer Wins!");
      winner = "dealer";
    } else if (dealer.hasBlackJack() && !player.hasBlackJack()) {
      System.out.println("Dealer has Blackjack. Dealer Wins!");
      winner = "dealer";
    } else if (player.isBust()) {
      System.out.println("You are over 21. Dealer Wins!");
      winner = "dealer";
    } else if (dealer.isBust()) {
      System.out.println("Dealer is over 21. You Win!");
      winner = "player";
    } else if (dealer.handValue == player.handValue) {
      System.out.println("Both players have the same score. Dealer Wins!");
      winner = "dealer";
    } else if (dealer.handValue > player.handValue) {
      System.out.println("Dealer has a higher score than you. Dealer Wins!");
      winner = "dealer";
    } else if (player.handValue > dealer.handValue) {
      System.out.println("You have a higher score than Dealer. You Win!");
      winner = "player";
    }

  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public String getWinner() {
    return winner;
  }

  public void setWinner(String winner) {
    this.winner = winner;
  }

}
