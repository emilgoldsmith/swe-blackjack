package impl;

import java.util.Random;

public class AnotherBlackJackPlayer extends AbstractBlackJackPlayer {

  private Random rng = new Random();

  public AnotherBlackJackPlayer(String playerName) {
    super(playerName);
  }

  public AnotherBlackJackPlayer(String playerName, int startingMoney) {
    super(playerName, startingMoney);
  }

  public int placeWager() {
    // This player is a bit more cautious
    double factor = rng.nextDouble() / 8 + 0.80;

    int bid = (int)(factor * this.money);
    if (bid == 0 && this.money > 0) {
      // If we have something to bid we should
      bid = 1;
    }
    // Actually place the wager
    this.money -= bid;
    return bid;
  }

  public boolean requestCard() {
    // This player is very aggresive, goes for blackjack or bust!
    if (this.getValue() < 21) {
      return true;
    }
    return false;
  }
}
