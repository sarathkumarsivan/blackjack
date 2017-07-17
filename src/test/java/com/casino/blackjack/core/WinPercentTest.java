package com.casino.blackjack.core;

import com.casino.blackjack.util.Scoreboard;

import junit.framework.TestCase;

/**
 * Test case to check the winning percentage calculation.
 *
 */
public class WinPercentTest extends TestCase {

  public void test1WinPercent() {
    Scoreboard.getInstance().setRound(4);
    Scoreboard.getInstance().setWin(2);
    Scoreboard.getInstance().setLose(2);
    assertEquals(50d, Scoreboard.getInstance().getWinPercent());
  }

  public void test2WinPercent() {
    Scoreboard.getInstance().setRound(4);
    Scoreboard.getInstance().setWin(0);
    Scoreboard.getInstance().setLose(4);
    assertEquals(0d, Scoreboard.getInstance().getWinPercent());
  }

  public void test3WinPercent() {
    Scoreboard.getInstance().setRound(4);
    Scoreboard.getInstance().setWin(4);
    Scoreboard.getInstance().setLose(0);
    assertEquals(100d, Scoreboard.getInstance().getWinPercent());
  }

}
