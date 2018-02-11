package impl;

import strategies.AggressiveHittingStrategy;
import strategies.RiskyBettingStrategy;

public class AnotherBlackJackPlayer extends BlackJackPlayer {

  // This is an aggressive player
  @Override
  protected void setDefaultStrategies() {
    hittingStrategy = new AggressiveHittingStrategy();
    bettingStrategy = new RiskyBettingStrategy();
  }

  public AnotherBlackJackPlayer(String playerName) {
    super(playerName);
  }

  public AnotherBlackJackPlayer(String playerName, int startingMoney) {
    super(playerName, startingMoney);
  }
}