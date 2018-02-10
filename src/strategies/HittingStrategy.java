package strategies;

import api.Hand;

public interface HittingStrategy {
    public boolean shouldHit(Hand hand, int numAces, int value);
}