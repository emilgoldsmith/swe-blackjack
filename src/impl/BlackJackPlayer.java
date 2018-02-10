package impl;

import api.Hand;
import api.Card;
import api.Player;
import strategies.HittingStrategy;
import strategies.BettingStrategy;
import strategies.CautiousBettingStrategy;
import strategies.CautiousHittingStrategy;

public class BlackJackPlayer implements Player {

  protected Hand hand = new BlackJackHand();
  protected String name;
  protected int money;
  protected BettingStrategy bettingStrategy;
  protected HittingStrategy hittingStrategy;

  protected void setDefaultStrategies() {
    this.bettingStrategy = new CautiousBettingStrategy();
    this.hittingStrategy = new CautiousHittingStrategy();
  }

  public BlackJackPlayer(String playerName) {
    this.name = playerName;
    // This will constitute the default starting money
    this.money = 1000;
    this.setDefaultStrategies();
  }

  public BlackJackPlayer(String playerName, int startingMoney) {
    this.name = playerName;
    this.money = startingMoney;
    this.setDefaultStrategies();
  }

  public void setBettingStrategy(BettingStrategy strat) {
    this.bettingStrategy = strat;
  }

  public void setHittingStrategy(HittingStrategy strat) {
    this.hittingStrategy = strat;
  }

  public int compareTo(Player other) {
    return this.name.compareTo(other.getName());
  }

  public Hand getHand() {
    return this.hand;
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

  public int placeWager() {
    int bid = this.bettingStrategy.bet(this.money);
    this.money -= bid;
    return bid;
  }

  public boolean requestCard() {
    return this.hittingStrategy.shouldHit(this.hand, this.numAces(), this.getValue());
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

