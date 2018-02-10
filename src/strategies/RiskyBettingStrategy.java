package strategies;

import java.util.Random;

public class RiskyBettingStrategy extends BettingStrategy {

  private Random rng = new Random();

  public int bet() {
    double factor = rng.nextDouble() / 2 + 0.25;

    int bid = (int) (factor * this.money);
    if (bid == 0 && this.money > 0) {
      // If we have something to bid we should
      bid = 1;
    }
    // Actually place the wager
    this.money -= bid;
    return bid;
  }
}