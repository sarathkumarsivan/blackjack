[![Build Status](https://travis-ci.org/sarathkumarsivan/blackjack.svg?branch=master)](https://travis-ci.org/sarathkumarsivan/blackjack)
## Blackjack
**Blackjack**, also known as **twenty-one**, is the most widely played casino banking game in the world. Blackjack is a comparing card game between a player and dealer, meaning players compete against the dealer but not against other players. It is played with one or more decks of 52 cards. The objective of the game is to beat the dealer in one of the following ways:

* Get 21 points on the player's first two cards (called a "blackjack" or "natural"), without a dealer blackjack;
* Reach a final score higher than the dealer without exceeding 21; or
* Let the dealer draw additional cards until their hand exceeds 21.

The player or players are dealt a two-card hand and add together the value of their cards. Face cards (**kings**, **queens**, and **jacks**) are counted as ten points. A player and the dealer can count an ace as 1 point or 11 points. All other cards are counted as the numeric value shown on the card. After receiving their first two cards, players have the option of getting a "hit", or taking an additional card. In a given round, the player or the dealer wins by having a score of 21 or by having the higher score that is less than 21. Scoring higher than 21 (called "busting" or "going bust") results in a loss. A player may win by having any final score equal to or less than 21 if the dealer busts. If a player holds an **Ace** valued as 11, the hand is called "soft", meaning that the player cannot go bust by taking an additional card; the value of the **Ace** will become 1 to prevent the hand from exceeding 21. Otherwise, the hand is "hard".

The dealer must hit until the cards total 17 or more points. (At many tables the dealer also hits on a "soft" 17, i.e. a hand containing an ace and one or more other cards totaling six.) Players win by not busting and having a total higher than the dealer's. The dealer loses by busting or having a lesser hand than the player who has not busted. If the player and dealer have the same total, this is called a "push", and the player typically does not win or lose money on that hand. If all available players bust, the hand ends automatically without the dealer having to play his or her hand.

This project is a command line implementation of basic **Blackjack** game using Java programming language. This game is implemented with the following rules:

* Dealer must hit on soft 17.
* Single Deck. The deck is shuffled every 6 rounds.
* Player is not allowed to split cards.
* Keeps track of win percentage of the player.

## Building Blackjack

Blackjack game is built using Apache Maven. To build Blackjack game, run:

```javascript
mvn clean compile assembly:single
```

## Blackjack Command Line Interface

The easiest way to start using Blackjack game is through running the jar blackjack-0.0.1-RELEASE.jar

```javascript
java -jar target/blackjack-0.0.1-RELEASE.jar
```

## How to play Blackjack?
```ruby
D:\workspace\blackjack>java -jar target/blackjack-0.0.1-RELEASE.jar

You have $1000.00 left.
How much do you want to bet?: 500

CURRENT GAME STATE
------------------
Dealer's hand:
Face down card + 4 of Diamonds

Your hand:
3 of Diamonds + King of Spades
Your hand is worth 13
Do you want to hit or stand? (hit/stand): hit

Dealer has decided to hit.

CURRENT GAME STATE
------------------
Dealer's hand:
Face down card + 4 of Diamonds + King of Spades

Your hand:
3 of Diamonds + King of Spades + 6 of Diamonds
Your hand is worth 19
Do you want to hit or stand? (hit/stand): stand

CURRENT GAME STATE
------------------
Dealer's hand:
Face down card + 4 of Diamonds + King of Spades + 7 of Diamonds + 2 of Clubs

Your hand:
3 of Diamonds + King of Spades + 6 of Diamonds
Dealer is over 21. You Win!

You have $1500.00 left. Do you want to keep playing? (yes or no): yes
You have $1500.00 left.
How much do you want to bet?: 1000

CURRENT GAME STATE
------------------
Dealer's hand:
Face down card + 7 of Diamonds

Your hand:
2 of Hearts + 6 of Diamonds
Your hand is worth 8
Do you want to hit or stand? (hit/stand): hit

Dealer has decided to hit.

CURRENT GAME STATE
------------------
Dealer's hand:
Face down card + 7 of Diamonds + 5 of Diamonds

Your hand:
2 of Hearts + 6 of Diamonds + 8 of Clubs
Dealer is over 21. You Win!

You have $2500.00 left. Do you want to keep playing? (yes or no): yes
You have $2500.00 left.
How much do you want to bet?: 2500

CURRENT GAME STATE
------------------
Dealer's hand:
Face down card + 4 of Clubs

Your hand:
3 of Hearts + 8 of Spades
Your hand is worth 11
Do you want to hit or stand? (hit/stand): hit

Dealer has decided to hit.

CURRENT GAME STATE
------------------
Dealer's hand:
Face down card + 4 of Clubs + 7 of Hearts

Your hand:
3 of Hearts + 8 of Spades + King of Spades
Your hand is worth 21
Do you want to hit or stand? (hit/stand): stand

CURRENT GAME STATE
------------------
Dealer's hand:
Face down card + 4 of Clubs + 7 of Hearts + 6 of Diamonds + Ace of Spades

Your hand:
3 of Hearts + 8 of Spades + King of Spades
Dealer is over 21. You Win!

You have $5000.00 left. Do you want to keep playing? (yes or no): no
Your Record: [Rounds Played: 3  Win: 3  Lost: 0  Winning Percentage: 100.0%]
```

## Reference

* [Blackjack](https://en.wikipedia.org/wiki/Blackjack)
* [Blackjack Rules](https://www.blackjackinfo.com/blackjack-rules/)

