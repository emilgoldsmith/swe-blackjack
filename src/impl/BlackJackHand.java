package impl;

import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import api.Card;
import api.Hand;

public class BlackJackHand implements Hand {

  private HashSet<Card> cards = new HashSet<Card>();

  public void addCard(Card cardToAdd) {
    this.cards.add(cardToAdd);
  }

  public Set<Card> getCards() {
    return Collections.unmodifiableSet(this.cards);
  }

  // TODO: Do this properly if we end up not using a set
  public int compareTo(Hand other) {
    // This will make Hands of equal value equal, but I can't think of a consistent
    // way to accurately compare two Hands since Sets aren't ordered.
    return new Integer(this.valueOf()).compareTo(new Integer(other.valueOf()));
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
}
