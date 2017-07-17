package com.casino.blackjack.util;

/**
 * A Scoreboard for keeping and displaying the score of the player in the Blackjack game. Scoreboard
 * single class which is responsible to create an object while making sure that only single object
 * gets created. This class provides a way to access its only object which can be accessed directly
 * without need to instantiate the object of the class.
 *
 */
public class Scoreboard {

  /**
   * Number of rounds that player and dealer play.
   */
  private int round;

  /**
   * Number of rounds that player win.
   */
  private int win;

  /**
   * Number of rounds that player lose.
   */
  private int lose;

  /**
   * Restrict instantiation of the class from other classes.
   */
  private Scoreboard() {};

  private static class ScoreboardHolder {
    /**
     * create an object of Scoreboard *
     */
    private static final Scoreboard INSTANCE = new Scoreboard();
  }

  /**
   * Return the single instance of available scoreboard.
   * 
   * @return instance of available scoreboard.
   */
  public static Scoreboard getInstance() {
    return ScoreboardHolder.INSTANCE;
  }

  /**
   * Return round from scoreboard.
   * 
   * @return round from scoreboard.
   */
  public int getRound() {
    return round;
  }

  /**
   * Set the round on scoreboard
   * 
   * @param round
   */
  public void setRound(int round) {
    this.round = round;
  }

  /**
   * Return total win of the player
   * 
   * @return total win of the player
   */
  public int getWin() {
    return win;
  }

  /**
   * Set total win of the player
   * 
   * @param win the total win of the player
   */
  public void setWin(int win) {
    this.win = win;
  }

  /**
   * Return the total lose of the player
   * 
   * @return the total lose of the player
   */
  public int getLose() {
    return lose;
  }

  /**
   * Set the total lose of the player
   * 
   * @param lose the total lose of the player
   */
  public void setLose(int lose) {
    this.lose = lose;
  }

  /**
   * Returns the winning percentage of the player.
   * 
   * @return winning percentage of the player.
   */
  public double getWinPercent() {
    return (((double) win) / round) * 100;
  }

  /**
   * Display the record of player on the console. It includes the Rounds played, total win, total
   * lost and winning percentage.
   */
  public void display() {
    System.out.print(
        String.format("Your Record: [Rounds Played: %s  Win: %s  Lost: %s  Winning Percentage: %s",
            round, win, lose, getWinPercent()) + "%]");
  }

}
