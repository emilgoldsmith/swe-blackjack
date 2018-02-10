package strategies;

import api.Hand;

public class CautiousHittingStrategy extends HittingStrategy {
  public boolean shouldHit(Hand hand, int numAces, int value) {
    if (numAces > 0) {
      // If we haven't lost yet and we have an ace it's impossible to lose
      return true;
    }
    if (value > 11) {
      // We have no aces and we could lose on the next turn so we are cautious
      // and want to make sure we don't bust
      return false;
    }
    // We have 11 or less so we can hit without risk
    return true;
  }
}