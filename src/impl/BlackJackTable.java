package impl;

import api.Table;
import api.Player;
import api.Card;
import java.util.HashMap;
import java.util.Random;
import java.util.Collections;
import java.lang.System;
import java.util.Collection;
import java.lang.StringBuilder;
import java.util.Set;

public class BlackJackTable extends Table {

  public BlackJackTable(int numberOfPlayers, int numberOfDecks) {
    this.dealer = new BlackJackDealer(numberOfDecks);
    this.wagers = new HashMap<Player, Integer>(numberOfPlayers);
    // Initialize the players
    Random rng = new Random();
    for (int i = 0; i < numberOfPlayers; i++) {
      // Starting money is random number between 200 and 1000
      int startingMoney = rng.nextInt(801) + 200;
      if (rng.nextBoolean()) {
        this.wagers.put(new BlackJackPlayer("Player " + Integer.toString(i + 1)), startingMoney);
      } else {
        this.wagers.put(new AnotherBlackJackPlayer("Player " + Integer.toString(i + 1)), startingMoney);
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

  public String toString() {
    StringBuilder stringRepresentation = new StringBuilder();

    stringRepresentation.append("Welcome to the status of the most awesome BlackJack table!\n\n");

    Collection<Player> players = this.getPlayers();
    for (Player singlePlayer : players) {
      stringRepresentation.append(singlePlayer.getName() + ":\n");
      stringRepresentation.append("Money left: ");
      stringRepresentation.append(singlePlayer.getMoney());
      stringRepresentation.append("\n");
      stringRepresentation.append("Current wager: ");
      stringRepresentation.append(this.wagers.get(singlePlayer));
      stringRepresentation.append("\n");
      Set<Card> cards = singlePlayer.getHand().getCards();
      if (cards.size() == 0) {
        stringRepresentation.append("Player currently has no cards\n");
      } else {
        stringRepresentation.append("Current cards:");
        for (Card singleCard : cards) {
          stringRepresentation.append(
            " " + singleCard.getValue().name().toLowerCase() +
            " of " + singleCard.getSuit().name().toLowerCase() + "s"
          );
        }
        stringRepresentation.append("\n");
      }
    }
    stringRepresentation.append("Dealer:\n");
    Set<Card> cards = this.dealer.getHand().getCards();
    if (cards.size() == 0) {
      stringRepresentation.append("Dealer currently has no cards\n");
    } else {
      stringRepresentation.append("Current cards:");
      for (Card singleCard : cards) {
        stringRepresentation.append(
          " " + singleCard.getValue().name().toLowerCase() +
          " of " + singleCard.getSuit().name().toLowerCase() + "s"
        );
      }
      stringRepresentation.append("\n");
    }
    return stringRepresentation.toString();
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
    // Remember to always shuffle before dealing
    this.dealer.shuffle();
    Collection<Player> players = this.getPlayers();
    // Deal 2 cards to each player
    for (int i = 0; i < 2; i++) {
      for (Player singlePlayer : players) {
        if (this.wagers.get(singlePlayer) > 0) {
          // Only deal them in if they made a bet
          this.dealer.dealCard(singlePlayer);
        }
      }
      this.dealer.dealCard((Player)this.dealer);
    }
  }

  protected void collectBets() {
    Collection<Player> players = this.getPlayers();
    for (Player singlePlayer : players) {
      if (singlePlayer.getMoney() > 0) {
        // Only ask them for a wager if they have something to bet
        this.wagers.put(singlePlayer, singlePlayer.placeWager());
      }
    }
    // Dealer doesn't bet
  }

  protected void playerTurns() {
    Collection<Player> players = this.getPlayers();
    for (Player singlePlayer : players) {
      while (
          this.wagers.get(singlePlayer) > 0 &&
          singlePlayer.getHand().isValid() &&
          !singlePlayer.getHand().isWinner() &&
          singlePlayer.requestCard()
      ) {
        this.dealer.dealCard(singlePlayer);
      }
    }
    // Dealer always plays last
    Player playerDealer = (Player)this.dealer;
    while (
        playerDealer.getHand().isValid() &&
        !playerDealer.getHand().isWinner() &&
        playerDealer.requestCard()
    ) {
      this.dealer.dealCard(playerDealer);
    }
  }

  protected void playerEvaluations() {
    int dealerScore = this.dealer.getHand().valueOf();
    Collection<Player> players = this.getPlayers();
    int winningFactor = 2;
    for (Player singlePlayer : players) {
      // Ignore them if they didn't bet anything
      if (this.wagers.get(singlePlayer) == 0) continue;

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
