package test.system;

import api.Table;
import impl.BlackJackTable;

/*
 * This is a "system" test that does two things:
 *
 *   1. Plays the game until all players are out of money. Unless
 *      you're a Black Jack wizard, that condition should be true at
 *      some point.
 *   2. Provides a template for how the game is programmatically setup
 *      and managed. Use this, in conjunction with api.Table::round,
 *      to get an understanding of how the various components
 *      interact.
 */
public class BlackJackGame {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Please provide the number of players");
            System.exit(1);
        }

        int numberOfPlayers = Integer.parseInt(args[0]);
        int decks = (args.length > 1) ? Integer.parseInt(args[1]) : 1;

        Table table = new BlackJackTable(numberOfPlayers, decks);

        for (int i = 0; !table.isGameOver(); i++) {
            System.out.println("Round " + i);
            table.round();
            System.out.println(table);
        }
    }
}
