package impl;

import api.Table;
import api.Player;
import java.util.HashMap;
import java.util.Random;
import java.util.Collections;
import java.lang.System;
import java.util.Collection;

public class BlackJackTable extends Table {

  public BlackJackTable(int numberOfPlayers, int numberOfDecks) {
    this.dealer = new BlackJackDealer(numberOfDecks);
    this.wagers = new HashMap<Player, Integer>(numberOfPlayers);
    // Initialize the players
    Random rnd = new Random();
    for (int i = 0; i < numberOfPlayers; i++) {
      if (rnd.nextBoolean()) {
        this.wagers.put(new BlackJackPlayer("Player " + Integer.toString(i + 1)), 0);
      } else {
        this.wagers.put(new AnotherBlackJackPlayer("Player " + Integer.toString(i + 1)), 0);
      }
    }
  }

  public Collection<Player> getPlayers() {
    // Make it read only as per the specification
    return Collections.unmodifiableSet(this.wagers.keySet());
  }

  public boolean isGameOver() {
    Collection<Player> players = this.getPlayers();
    boolean hasPlayersLeft = players.size() > 0;
    boolean playersHaveMoneyLeft = false;
    for (Player singlePlayer : players) {
      playersHaveMoneyLeft = playersHaveMoneyLeft || singlePlayer.getMoney() > 0;
      if (playersHaveMoneyLeft) {
        break;
      }
    }
    return !hasPlayersLeft || !playersHaveMoneyLeft;
  }

  // TODO: Implement something real here
  public String toString() {
    return "Dummy String";
  }

  protected void collectCards() {
    Collection<Player> players = this.getPlayers();
    for (Player singlePlayer : players) {
      this.dealer.collectCards(singlePlayer);
    }
    // Remember to collect cards from dealer as well
    this.dealer.collectCards((Player)this.dealer);
  }

  protected void dealTable() {
    Collection<Player> players = this.getPlayers();
    // Deal 2 cards to each player
    for (int i = 0; i < 2; i++) {
      for (Player singlePlayer : players) {
        this.dealer.dealCard(singlePlayer);
      }
      this.dealer.dealCard((Player)this.dealer);
    }
  }

  protected void collectBets() {
    Collection<Player> players = this.getPlayers();
    for (Player singlePlayer : players) {
      this.wagers.put(singlePlayer, singlePlayer.placeWager());
    }
  }

  // TODO: Make sure you stop after someone has busted
  protected void playerTurns() {
    Collection<Player> players = this.getPlayers();
    for (Player singlePlayer : players) {
      while (singlePlayer.requestCard()) {
        this.dealer.dealCard(singlePlayer);
      }
    }
    // Dealer always plays last
    Player playerDealer = (Player)this.dealer;
    while (playerDealer.requestCard()) {
      this.dealer.dealCard(playerDealer);
    }
  }

  protected void playerEvaluations() {
    int dealerScore = this.dealer.getHand().valueOf();
    Collection<Player> players = this.getPlayers();
    int winningFactor = 2;
    for (Player singlePlayer : players) {
      int score = singlePlayer.getHand().valueOf();
      if (score > 21) {
        // You lose no matter what, even if dealer busts
      } else if (score == 21) {
        if (dealerScore != 21) {
          // You win
          singlePlayer.payOut(winningFactor * this.wagers.get(singlePlayer));
        } else {
          // You draw
          singlePlayer.payOut(this.wagers.get(singlePlayer));
        }
      } else {
        if (dealerScore > 21 || dealerScore < score) {
          // You win
          singlePlayer.payOut(winningFactor * this.wagers.get(singlePlayer));
        } else if (dealerScore == score) {
          // You draw
          singlePlayer.payOut(this.wagers.get(singlePlayer));
        } else {
          // You lose
        }
      }
      // Reset the wagers
      this.wagers.put(singlePlayer, 0);
    }
  }
}
