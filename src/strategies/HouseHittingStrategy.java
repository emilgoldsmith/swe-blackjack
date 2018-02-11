package strategies;

import api.Hand;

public class HouseHittingStrategy implements HittingStrategy {

  /**
   * The dealer at most casinoes has a fixed strategy as follows:
   * Hit until total reaches 17 or above (though also hit on soft 17, which
   * is a 17 with an ace)
   */
  public boolean shouldHit(Hand hand, int numAces, int value) {
    if (value > 17) {
      return false;
    }
    if (value == 17) {
      if (numAces > 0) {
        return true;
      }
      return false;
    }
    return true;
  }
}