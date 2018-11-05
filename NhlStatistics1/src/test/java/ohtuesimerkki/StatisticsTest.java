package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void playerCanBeFound() {
        Player p = stats.search("Yzerman");
        assertEquals("Yzerman", p.getName());
    }
    
    @Test
    public void wrongNameReturnsNull() {
        assertTrue(stats.search("Möttönen") == null);
    }
    
    @Test
    public void allTeamsPlayersAreFound() {
        List<Player> team = stats.team("EDM");
        assertEquals(3, team.size());
    }
    
    @Test
    public void topScorersAreSortedCorrectly() {
        List<Player> top = stats.topScorers(1);
        Player p = top.get(0);
        assertEquals("Gretzky", p.getName());
    }
}
