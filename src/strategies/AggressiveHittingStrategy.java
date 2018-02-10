package strategies;

import api.Hand;

public class AggressiveHittingStrategy implements HittingStrategy {
  public boolean shouldHit(Hand hand, int numAces, int value) {
    if (value < 21) {
      return true;
    }
    return false;
  }
}