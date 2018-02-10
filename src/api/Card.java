package api;

/*
 * Cards are represented using the Java Enum type. Please refer to
 * documentation online for more information. For this assignment,
 * two things are important:
 *
 *   1. Java Enum is a type. In this case, an instance of a card value
 *      should be declared using Card.Value (likewise for suit).
 *
 *   2. The Enum type defines a static method called 'values' that
 *      will allow you to go through all values of that type:
 *      Card.Value.values()
 *
 *   3. The number next to each constant (ACE(1), for example)
 *      represents that contants value. That value can be accessed
 *      using Card.getValue: If you have a variable x of type Card
 *      that represents an ACE, x.getValue().getValue() will return 1.
 */
public class Card implements Comparable<Card> {
    public enum Value {
        ACE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10);

        private int value;

        Value(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum Suit {
        CLUB(1),
        HEART(2),
        DIAMOND(3),
        SPADE(4);

        private int suit;

        Suit(int suit) {
            this.suit = suit;
        }

        public int getSuit() {
            return suit;
        }
    }

    private Value value;
    private Suit suit;

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int compareTo(Card card) {
        int vCompare = value.compareTo(card.getValue());

        return (vCompare == 0) ? suit.compareTo(card.getSuit()) : vCompare;
    }
}
