package impl;

public class BlackJackPlayer extends AbstractBlackJackPlayer {
  public BlackJackPlayer(String playerName) {
    super(playerName);
  }

  public BlackJackPlayer(String playerName, int startingMoney) {
    super(playerName, startingMoney);
  }

  public int placeWager() {
    // TODO: Make this better
    return Math.min(this.money / 2, 100);
  }

  public boolean requestCard() {
    // TODO: Make this better
    return true;
  }
}
