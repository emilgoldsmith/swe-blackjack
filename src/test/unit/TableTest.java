package test.unit;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import api.Table;
import api.Player;
import impl.BlackJackTable;
import impl.BlackJackPlayer;
import impl.AnotherBlackJackPlayer;

public class TableTest {
    @Test
    public void testNoMoneyGameOver() {
        Table table = new BlackJackTable(2, 1);
        assertFalse(table.isGameOver());
    }

    @Test
    public void testNoPlayersGameOver() {
        Table table = new BlackJackTable(0, 1);
        assertTrue(table.isGameOver());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testPlayerListReadOnly() {
        Table table = new BlackJackTable(10, 1);
        Collection<Player> players = table.getPlayers();
        players.add(new BlackJackPlayer("Player 1", 0));
    }

    @Test
    public void testPlayerConstructorExists() {
        Collection<Player> players = new ArrayList<Player>();
        Table table = new BlackJackTable(players, 1);
        assertNotNull(table);
    }
}
