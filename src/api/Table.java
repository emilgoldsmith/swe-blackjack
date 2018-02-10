package api;

import java.util.Map;
import java.util.List;
import java.util.Collection;

/*
 * The table class represents a place in which card games happen. You
 * should specialise it specifically for Black Jack.
 *
 * Concrete implementations should have two constructors:
 *
 *  1. Takes two integers, where the first represents the number of
 *     players and the second represents the number of decks.
 *
 *  2. Takes a collection of players and an integer representing the
 *     number of decks.
 */
public abstract class Table {
    /*
     * The dealer
     */
    protected Dealer dealer;

    /*
     * Keeps track of wagers made during a round
     */
    protected Map<Player, Integer> wagers;

    /*
     * Get the players list of players that are at this table. This
     * list should not include the dealer. The data structure that is
     * returned should be read-only.
     */
    public abstract Collection<Player> getPlayers();

    /*
     * A game is over when there are no players, or no players with
     * money to bet
     */
    public abstract boolean isGameOver();

    /*
     * A string representation of the table
     */
    public abstract String toString();

    /*
     * Collect cards from all players at the table. Note that in Black
     * Jack, that also means the dealer themself!
     */
    protected abstract void collectCards();

    /*
     * Deal cards to all players at the table. Note that in Black
     * Jack, that also means the dealer themself!
     */
    protected abstract void dealTable();

    /*
     * Collect bets from all players at the table
     */
    protected abstract void collectBets();

    /*
     * Give each player a turn. Note that in Black Jack, the dealer
     * should havea turn as well!
     */
    protected abstract void playerTurns();

    /*
     * Evaluate each players hand with respect the rules of the game,
     * and to the dealer. If a player has a winning hand, they should
     * be paid based on their respective information in the wager
     * table.
     */
    protected abstract void playerEvaluations();

    /*
     * A method that brings together actions of a round
     */
    public void round() {
        collectBets();
        dealTable();
        playerTurns();
        playerEvaluations();
        collectCards();
    }
}
