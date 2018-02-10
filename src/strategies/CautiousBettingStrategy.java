package strategies;

import java.util.Random;

public class CautiousBettingStrategy extends BettingStrategy {

  private Random rng = new Random();

  public int bet() {
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
}