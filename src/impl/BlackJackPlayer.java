package impl;

import java.util.Random;

public class BlackJackPlayer extends AbstractBlackJackPlayer {

  private Random rng = new Random();

  public BlackJackPlayer(String playerName) {
    super(playerName);
  }

  public BlackJackPlayer(String playerName, int startingMoney) {
    super(playerName, startingMoney);
  }

  public int placeWager() {
    // This player likes to bet big! (between 25 to 75% of his money)
    double factor = rng.nextDouble() / 2 + 0.25;

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
    // This player is very cautious
    if (this.numAces() > 0) {
      // If we haven't lost yet and we have an ace it's impossible to lose
      return true;
    }
    if (this.getValue() > 11) {
      // We have no aces and we could lose on the next turn so we are cautious
      // and want to make sure we don't bust
      return false;
    }
    // We have 11 or less so we can hit without risk
    return true;
  }
}
