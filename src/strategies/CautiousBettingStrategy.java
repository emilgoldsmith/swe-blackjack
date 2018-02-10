package strategies;

import java.util.Random;

public class CautiousBettingStrategy extends BettingStrategy {

  private Random rng = new Random();

  public int bet() {
    double factor = rng.nextDouble() / 8 + 0.80;

    int bid = (int)(factor * moneyLeft);
    if (bid == 0 && moneyLeft > 0) {
      // If we have something to bid we should
      bid = 1;
    }
    return bid;
  }
}