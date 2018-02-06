package api;

import java.util.List;
import java.lang.Comparable;

/*
 * A hand is a collection of cards.
 */
public interface Hand extends Comparable<Hand> {
    /*
     * Adds a card to the hand
     */
    public void addCard(Card card);

    /*
     * Returns a collection of cards that is read-only.
     */
    public List<Card> getCards();

    /*
     * Determination of valid and winning hands, respectively. Your
     * implementation should answer that question with respect to the
     * rules of Black Jack, but irrespective of what other players at
     * the table have (including the dealer).
     */
    public boolean isValid();
    public boolean isWinner();

    /*
     * The value of the hand, as an integer. This value should be
     * based on the "value" of the cards in this hand.
     */
    public int valueOf();
}
