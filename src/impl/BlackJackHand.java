package impl;

import java.util.List;
import java.util.Vector;
import java.util.Collections;
import api.Card;
import api.Hand;

public class BlackJackHand implements Hand {

  private List<Card> cards = new Vector<Card>();

  public void addCard(Card cardToAdd) {
    this.cards.add(cardToAdd);
  }

  public List<Card> getCards() {
    return Collections.unmodifiableList(this.cards);
  }

  /**
   * I thought of what would make sense semantically and implemented the algorithm
   * as described below in the comments based on that
   */
  public int compareTo(Hand other) {
    // The hands are equally bad if they both busted
    if (!this.isValid() && !other.isValid()) {
      return 0;
    }
    // We were the only ones that busted so we are less
    if (!this.isValid()) {
      return -1;
    }
    // They were the only ones that busted so they are less
    if (!other.isValid()) {
      return 1;
    }
    // Both hands are valid so we now check who has the highest value
    int valueComp = new Integer(this.valueOf()).compareTo(new Integer(other.valueOf()));
    // If one is higher than the other we return the ordering of the numbers
    if (valueComp != 0) {
      return valueComp;
    }
    // We have equal values, if we both have 21 we call it equal
    if (this.isWinner()) {
      return 0;
    }
    // Else as a tiebreak we say the one who has a higher amount of aces has a stronger hand
    BlackJackHand extendedOther = (BlackJackHand)other;
    return new Integer(this.numAces()).compareTo(new Integer(extendedOther.numAces()));
  }

  public boolean isValid() {
    return this.valueOf() <= 21;
  }

  public boolean isWinner() {
    return this.valueOf() == 21;
  }

  public int valueOf() {
    int total = 0;
    int aces = 0;
    for (Card singleCard : this.cards) {
      int value = singleCard.getValue().getValue();
      if (value == 1) {
        aces++;
        total += 11;
      } else {
        total += value;
      }
    }
    for (; aces > 0 && total > 21; aces--, total -= 10);
    return total;
  }

  public int numAces() {
    int count = 0;
    for (Card singleCard : cards) {
      if (singleCard.getValue().getValue() == 1) {
        count++;
      }
    }
    return count;
  }
}
