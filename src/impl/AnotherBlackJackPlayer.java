package impl;

public class AnotherBlackJackPlayer extends AbstractBlackJackPlayer {
  public AnotherBlackJackPlayer(String playerName) {
    super(playerName);
  }

  public AnotherBlackJackPlayer(String playerName, int startingMoney) {
    super(playerName, startingMoney);
  }

  public int placeWager() {
    // TODO: Make this better
    // And remember that it should also make the player lose the money
    return Math.min(this.money / 2, 100);
  }

  public boolean requestCard() {
    // TODO: Make this better
    return true;
  }
}
