import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import student.BoardGame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import student.Planner;
import student.IPlanner;
import student.GameData;


/**
 * JUnit test for the Planner class.
 * 
 * Just a sample test to get you started, also using
 * setup to help out. 
 */
public class TestPlanner {

    /**
     * new set holding test items.
     */
    static Set<BoardGame> games;

    /**
     * Executed before all tests in the current test class.
     */
    @BeforeAll
    public static void setup() {
        games = new HashSet<>();
        games.add(new BoardGame("17 days", 6, 1, 8, 70, 70, 9.0, 600, 9.0, 2005));
        games.add(new BoardGame("Chess", 7, 2, 2, 10, 20, 10.0, 700, 10.0, 2006));
        games.add(new BoardGame("Go", 1, 2, 5, 30, 30, 8.0, 100, 7.5, 2000));
        games.add(new BoardGame("Go Fish", 2, 2, 10, 20, 120, 3.0, 200, 6.5, 2001));
        games.add(new BoardGame("golang", 4, 2, 7, 50, 55, 7.0, 400, 9.5, 2003));
        games.add(new BoardGame("GoRami", 3, 6, 6, 40, 42, 5.0, 300, 8.5, 2002));
        games.add(new BoardGame("Monopoly", 8, 6, 10, 20, 1000, 1.0, 800, 5.0, 2007));
        games.add(new BoardGame("Tucano", 5, 10, 20, 60, 90, 6.0, 500, 8.0, 2004));
    }

    /**
     * Test to make sure filterSingle() return the expected stream.
     */
    @Test
    public void testFilterSingleName() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("name ~= 17").toList();
        assertEquals(1, filtered.size());
        assertEquals("17 days", filtered.get(0).getName());
    }

    /**
     *  Test to make sure filterMulti() return the expected stream.
     */
    @Test
    public void testFilterMultiNameMinPlayers() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("name ~= Go, minPlayers >= 2, maxPlayers == 5").toList();
        assertEquals(1, filtered.size()); 
        assertEquals("Go", filtered.getFirst().getName()); 
    }    

    /**
     *  Test to make sure filterMulti() return the expected stream
     *  with an asc order if we did not specified the order.
     */    
    @Test
    public void testFilterMultiNameMinPlayersCheckAscOrder() {
        IPlanner planner = new Planner(games);
        Stream<BoardGame> filtered = planner.filter("name ~= o, minPlayers >= 1");
        List<String> filteredListName = filtered.map(game -> game.getName()).toList();
        List<String> expectedList = Arrays.asList("Go", "Go Fish", "golang", "GoRami", "Monopoly", "Tucano");
        assertEquals(6, filteredListName.size()); 
        assertEquals(expectedList, filteredListName);        
    }       
    
    /**
     *  Test to make sure filterMulti() return the expected stream with an asc order if we did not specified the order
     *  and the expected stream also was sorted on GameData.RATING.
     */      
    @Test
    public void testFilterMultiNameMinPlayersSortOnRatingCheckAscOrder() {
        IPlanner planner = new Planner(games);
        Stream<BoardGame> filtered = planner.filter("name ~= o, minPlayers >= 1", GameData.RATING);
        List<String> filteredListName = filtered.map(game -> game.getName()).toList();
        List<String> expectedList = Arrays.asList("Monopoly", "Go Fish", "Go", "Tucano", "GoRami", "golang");
        assertEquals(6, filteredListName.size()); 
        assertEquals(expectedList, filteredListName);        
    }   
    
    /**
     *  Test to make sure filterMulti() return the expected stream with an desc order if we did specified the order
     *  and the expected stream also was sorted on GameData.DIFFICULTY.
     */     
    @Test
    public void testFilterMultiDifficultySortOnDifficultyCheckDescOrder() {
        IPlanner planner = new Planner(games);
        Stream<BoardGame> filtered = planner.filter("difficulty != 3, year >= 2001", GameData.DIFFICULTY, false);
        List<String> filteredListName = filtered.map(game -> game.getName()).toList();
        List<String> expectedList = Arrays.asList("Chess", "17 days", "golang", "Tucano", "GoRami", "Monopoly");
        assertEquals(6, filteredListName.size()); 
        assertEquals(expectedList, filteredListName);        
    }    

    /**
     *  Test to make sure reset() reset games in Planner to the defalut games provided in collection.csv.
     */      
    @Test
    public void testReset() {
        IPlanner planner = new Planner(games);
        Stream<BoardGame> filtered = planner.filter("difficulty != 3, year >= 2001", GameData.DIFFICULTY, false);
        assertEquals(6, filtered.count());         
        planner.reset();
        Stream<BoardGame> clearFiltered = planner.filter("");
        assertEquals(8, clearFiltered.count()); 
    }     

}