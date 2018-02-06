package test.unit;

import java.util.List;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import api.Hand;
import api.Card;
import impl.BlackJackHand;

public class HandTest {
    private Hand hand;

    @Before
    public void setup() {
        hand = new BlackJackHand();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCardsReadOnly() {
        List<Card> cards = hand.getCards();
        cards.add(new Card(Card.Value.ACE, Card.Suit.CLUB));
    }

    @Test
    public void testAddCardIncreasesSet() {
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.CLUB));

        assertEquals(hand.getCards().size(), 1);
    }

    @Test
    public void testHandContainsAddition() {
        Card card = new Card(Card.Value.ACE, Card.Suit.CLUB);
        hand.addCard(card);

        assertTrue(hand.getCards().contains(card));
    }

    @Test
    public void testAbleToIdentifyBust() {
        hand.addCard(new Card(Card.Value.KING, Card.Suit.CLUB));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.HEART));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.SPADE));

        assertFalse(hand.isValid());
    }

    @Test
    public void testAbleToIdentifyWinner() {
        hand.addCard(new Card(Card.Value.KING, Card.Suit.CLUB));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.HEART));
        hand.addCard(new Card(Card.Value.ACE, Card.Suit.SPADE));

        assertTrue(hand.isWinner());
    }

    @Test
    public void testKingsAreTwenty() {
        hand.addCard(new Card(Card.Value.KING, Card.Suit.CLUB));
        hand.addCard(new Card(Card.Value.KING, Card.Suit.HEART));

        assertEquals(hand.valueOf(), 20);
    }
}
