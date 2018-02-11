package test.system;

import api.Table;
import impl.BlackJackTable;
import api.Player;
import impl.BlackJackPlayer;
import strategies.CautiousBettingStrategy;
import strategies.CautiousHittingStrategy;
import strategies.RiskyBettingStrategy;
import strategies.AggressiveHittingStrategy;

import java.util.Collection;
import java.util.Vector;

public class BlackJackStrategyGame {
    public static void main(String[] args) {
        int numberOfPlayers = 5;
        int decks = 10;

        Collection<Player> players = new Vector<Player>();
        for (int i = 0; i < numberOfPlayers; i++) {
          // We make this a BlackJackPlayer since it needs to be able to set strategies
          BlackJackPlayer curPlayer = new BlackJackPlayer("Player " + new Integer(i+1).toString());
          // Setup different combinations of strategies
          if (i % 2 == 0) {
            curPlayer.setBettingStrategy(new CautiousBettingStrategy()); 
          } else {
            curPlayer.setBettingStrategy(new RiskyBettingStrategy());
          }
          if ((i % 4) < 2) {
            curPlayer.setHittingStrategy(new CautiousHittingStrategy());
          } else {
            curPlayer.setHittingStrategy(new AggressiveHittingStrategy());
          }

          // Add newly created player to our players
          players.add(curPlayer);
        }

        Table table = new BlackJackTable(players, decks);

        for (int i = 0; !table.isGameOver(); i++) {
            System.out.println("Round " + i);
            table.round();
            System.out.println(table);
        }
    }
}
