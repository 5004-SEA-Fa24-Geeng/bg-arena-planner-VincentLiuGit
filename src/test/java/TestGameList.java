
import org.junit.jupiter.api.BeforeEach;
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
import student.IGameList;
import student.GameList;


/**
 * JUnit test for the GameList class.
 * 
 * Just a sample test to get you started, also using
 * setup to help out. 
 */
public class TestGameList {

    /**
     * new set holding test items.
     */    
    static Set<BoardGame> games;
    /**
     * new GameList holding test items.
     */        
    static IGameList gameList;

    /**
     * Executed before each tests in the current test class.
     */
    @BeforeEach
    public void setup() {
        gameList = new GameList();
        games = new HashSet<>();
        games.add(new BoardGame("17 days", 6, 1, 8, 70, 70, 9.0, 600, 9.0, 2005));  
        games.add(new BoardGame("Go Fish", 2, 2, 10, 20, 120, 3.0, 200, 6.5, 2001));
        games.add(new BoardGame("golang", 4, 2, 7, 50, 55, 7.0, 400, 9.5, 2003));
        games.add(new BoardGame("GoRami", 3, 6, 6, 40, 42, 5.0, 300, 8.5, 2002));
       
    }
    /**
     * Test to make sure getGameNames() return the expected list.
     */
    @Test
    public void testGetGameNames() {
        IPlanner planner = new Planner(games);
        Stream<BoardGame> filtered = planner.filter("name ~= 17");
        gameList.addToList("all ", filtered);
        List<String> expectedList = Arrays.asList("17 days");
        assertEquals(1, gameList.count());
        assertEquals(expectedList, gameList.getGameNames());
    }
    /**
     * Test to make sure addToList() adds the first item in the filtered stream to the list.
     */
    @Test
    public void testAddToList1Num() {
        IPlanner planner = new Planner(games);
        Stream<BoardGame> filtered = planner.filter("");
        gameList.addToList(" 1 ", filtered);
        List<String> expectedList = Arrays.asList("Go Fish");
        assertEquals(1, gameList.count());
        assertEquals(expectedList, gameList.getGameNames());
    }    
    /**
     * Test to make sure addToList() adds the exact game to the list.
     */
    @Test
    public void testAddToListNumAndStringNum() {
        IPlanner planner = new Planner(games);
        Stream<BoardGame> filtered = planner.filter("");
        gameList.addToList(" 17 days ", filtered);
        List<String> expectedList = Arrays.asList("17 days");
        assertEquals(1, gameList.count());
        assertEquals(expectedList, gameList.getGameNames());
    }        
    /**
     * Test to make sure addToList() adds the 2nd to 5th games in the filtered stream to the list.
     */
    @Test
    public void testAddToListNumberGreaterThanFilterStreamCount() {
        IPlanner planner = new Planner(games);
        Stream<BoardGame> filtered = planner.filter("");
        gameList.addToList("2-5 ", filtered);
        List<String> expectedList = Arrays.asList("17 days", "Go Fish", "golang");
        assertEquals(3, gameList.count());
    }
    /**
     * Test to make sure addToList() adds the 2nd game in the filtered stream to the list.
     */
    @Test
    public void testAddToListNumberEvenFilterStreamCount() {
        IPlanner planner = new Planner(games);
        Stream<BoardGame> filtered = planner.filter("");
        gameList.addToList("2-2 ", filtered);
        List<String> expectedList = Arrays.asList("Go Fish");
        assertEquals(1, gameList.count());
        assertEquals(expectedList, gameList.getGameNames());
    }  
    /**
     * Test to make sure removeFromList() removes the 1st to 6th games on the list.
     */    
    @Test
    public void testRemoveFromListNumberGreaterThanList() {
        IPlanner planner = new Planner(games);
        Stream<BoardGame> filtered = planner.filter("");
        gameList.addToList("all ", filtered);
        gameList.removeFromList(" 1-6 ");
        assertEquals(0, gameList.count());
    }    
    /**
     * Test to make sure removeFromList() removes the 3rd game on the list.
     */        
    @Test
    public void testRemoveFromListNumberEvenFilterStreamCount() {
        IPlanner planner = new Planner(games);
        Stream<BoardGame> filtered = planner.filter("");
        gameList.addToList("2-6 ", filtered);
        gameList.removeFromList(" 3-3 ");
        assertEquals(2, gameList.count());
    }      
    /**
     * Test to make sure removeFromList() removes all of the games on the list.
     */   
    @Test
    public void testRemoveFromListRemoveAll() {
        IPlanner planner = new Planner(games);
        Stream<BoardGame> filtered = planner.filter("");
        gameList.addToList("2-6 ", filtered);
        gameList.removeFromList(" all ");
        assertEquals(0, gameList.count());
    }     

}