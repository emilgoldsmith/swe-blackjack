package strategies;

import api.Hand;

public class AggressiveHittingStrategy extends HittingStrategy {
  public shouldHit(Hand hand, int numAces, int value) {
    if (value < 21) {
      return true;
    }
    return false;
  }
}