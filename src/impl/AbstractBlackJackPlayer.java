package impl;

import api.Hand;
import api.Card;
import api.Player;

/**
 * This intermediary class is created so as to modularize the constructors, other shared methods,
 * and make sure it is easy to change things such as the default amount of money
 * started with in both BlackJackPlayer and AnotherBlackJackPlayer
 * Since this class implements Player but the whole interface is not implemented here
 * any children of this class will have to implement placeWager and requestCard themselves.
 */
public abstract class AbstractBlackJackPlayer implements Player {

  protected Hand hand = new BlackJackHand();
  protected String name;
  protected int money;

  public AbstractBlackJackPlayer(String playerName) {
    this.name = playerName;
    // This will constitute the default starting money
    this.money = 1000;
  }

  public AbstractBlackJackPlayer(String playerName, int startingMoney) {
    this.name = playerName;
    this.money = startingMoney;
  }

  public int compareTo(Player other) {
    return this.name.compareTo(other.getName());
  }

  public Hand getHand() {
    return hand;
  }

  public void receive(Card card) {
    this.hand.addCard(card);
  }

  public Hand relinquishCards() {
    // This passing around by reference works because we reassign hand to a new pointer
    Hand oldHand = hand;
    this.hand = new BlackJackHand();
    return oldHand;
  }

  public void payOut(int winnings) {
    this.money += winnings;
  }

  public int getMoney() {
    // We put our checks that money never becomes negative in the places where logic
    // that alters the amount of money left is written
    return this.money;
  }

  public String getName() {
    return this.name;
  }

  public int numberOfCards() {
    return this.hand.getCards().size();
  }

  // These two are my own methods
  protected int getValue() {
    return this.getHand().valueOf();
  }

  protected int numAces() {
    BlackJackHand extendedHand = (BlackJackHand)hand;
    return extendedHand.numAces();
  }
}

