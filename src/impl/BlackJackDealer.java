package impl;

import api.Dealer;
import api.Card;
import api.Player;
import java.util.Vector;
import java.util.Collections;

/* TODO: Override the hit function here to the "normal" dealer rules */
public class BlackJackDealer extends BlackJackPlayer implements Dealer {

  private Vector<Card> deck;

  public BlackJackDealer(int numberOfDecks) {
    // Instantiate name and money of the dealer
    super("Dealer", 1000 * 1000 * 1000);

    // Add all the cards to our main deck
    for (int i = 0; i < numberOfDecks; i++) {
      for (int suit = 1; suit <= 4; suit++) {
        for (int value = 1; value <= 13; value++) {
          this.deck.add(new Card(Card.Value.values()[value - 1], Card.Suit.values()[suit - 1]));
        }
      }
    }
    // And we then shuffle the deck
    this.shuffle();
  }

  private Card drawCard() {
    Card cardDrawn = this.deck.lastElement();
    this.deck.removeElementAt(this.deck.size() - 1);
    return cardDrawn;
  }

  public void dealCard(Player player) {
    player.receive(this.drawCard());
  }

  public void collectCards(Player player) {
    this.deck.addAll(player.relinquishCards().getCards());
  }

  public void shuffle() {
    Collections.shuffle(this.deck);
  }
}
