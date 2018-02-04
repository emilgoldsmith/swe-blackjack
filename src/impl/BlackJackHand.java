package impl;

import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import api.Card;
import api.Hand;

public class BlackJackHand implements Hand {

  private HashSet<Card> cards;

  /** Delete this if not needed
  public BlackJackHand(BlackJackHand other) {
    Set<Card> otherCards = other.getCards();
    for (Card singleCard : otherCards) {
      this.addCard(singleCard);
    }
  }
  */

  public void addCard(Card cardToAdd) {
    this.cards.add(cardToAdd);
  }

  public Set<Card> getCards() {
    return Collections.unmodifiableSet(this.cards);
  }

  public int compareTo(Hand other) {
    // This will make Hands of equal value equal, but I can't think of a consistent
    // way to accurately compare two Hands since Sets aren't ordered.
    return new Integer(this.valueOf()).compareTo(new Integer(other.valueOf()));
  }

  // TODO: Implement this
  public boolean isValid() {
    return true;
  }

  // TODO: Find out what he means with this
  public boolean isWinner() {
    return false;
  }

  public int valueOf() {
    // TODO: Remember to somehow handle ace being 1 and 11
    int total = 0;
    for (Card singleCard : this.cards) {
      total += singleCard.getValue().getValue();
    }
    return total;
  }
}
