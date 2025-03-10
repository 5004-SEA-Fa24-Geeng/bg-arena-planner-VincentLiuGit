import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import student.BoardGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import student.Planner;
import student.IPlanner;


/**
 * JUnit test for the Filter class.
 * 
 * Just a sample test to get you started, also using
 * setup to help out. 
 */
public class TestFilter {

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
     * Test to make sure filter() handle the contain operartion properly and return the expected stream.
     */    
    @Test
    public void testFilterContainsName() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("name ~= 17 days").toList();
        assertEquals(1, filtered.size());
        assertEquals("17 days", filtered.get(0).getName());
    }
    /**
     * Test to make sure filter() handle the equql operartion properly and return the expected stream.
     */   
    @Test
    public void testFilterEqualName() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("name == Go").toList();
        assertEquals(1, filtered.size());
        assertEquals("Go", filtered.get(0).getName());
    }    
    /**
     * Test to make sure filter() handle the not equal operartion properly and return the expected stream.
     */   
    @Test
    public void testFilterNotEqualName() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("name != Go").toList();
        assertEquals(7, filtered.size());
    }    
    /**
     * Test to make sure filter() handle the greater equal then operartion properly and return the expected stream.
     */       
    @Test
    public void testFilterOtherOpsName() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("name >= Go").toList();
        assertEquals(6, filtered.size());
    }     
    /**
     * Test to make sure filter() handle the less then operartion properly and return the expected stream.
     */    
    @Test
    public void testFilterLessThanMinPlaytime() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("minPlayTime < 70").toList();
        assertEquals(7, filtered.size());
    }  
    /**
     * Test to make sure filter() handle the less equal then operartion properly and return the expected stream.
     */    
    @Test
    public void testFilterLessThanEqualYear() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("year <= 2005").toList();
        assertEquals(6, filtered.size());
    }  

}