package student;

import java.util.Set;
import java.util.stream.Stream;


public class Planner implements IPlanner {
    
    /**
     * If no sort is given, use objectname as a default.
     */
    private static final String DEFAULT_SORT_ON_NAME = "objectname";
    /**
     * A set of BoardGame that user sent in.
     */
    private Set<BoardGame> games;
    /**
     * A copy set of BoardGame that user sent in.
     */
    private Set<BoardGame> copiedGames;

    /**
     * Create a Planner object.
     * @param games a set of games that user send in
     */
    public Planner(Set<BoardGame> games) {
        this.games = games;
        this.copiedGames = games;
    }

    @Override
    public Stream<BoardGame> filter(String filter) {
        // true: single filter
        // false: multi filter
        Stream<BoardGame> filteredStream = (checkFilterNum(filter)) 
                            ? filterSingle(filter, this.games.stream()) : filterMulti(filter, this.games.stream());
        filteredStream = filteredStream.sorted(Sorts.getSortType(DEFAULT_SORT_ON_NAME, true));
        return filteredStream;
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {
        Stream<BoardGame> filteredStream = (checkFilterNum(filter)) 
                            ? filterSingle(filter, this.games.stream()) : filterMulti(filter, this.games.stream());
        filteredStream = filteredStream.sorted(Sorts.getSortType(sortOn.getColumnName(), true));
        return filteredStream;              
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
        Stream<BoardGame> filteredStream = (checkFilterNum(filter)) 
                            ? filterSingle(filter, this.games.stream()) : filterMulti(filter, this.games.stream());
        filteredStream = filteredStream.sorted(Sorts.getSortType(sortOn.getColumnName(), ascending));
        return filteredStream;   
    }

    @Override
    public void reset() {
        this.games = this.copiedGames;
    }

    /**
     * true if the string only contains one filter, else false.
     * @param filter
     * @return bolean
     */
    private boolean checkFilterNum(String filter) {
        // only one filter
        // "name == Go"        
        if (filter.split(",").length == 1) {
            return true;
        }
        // 2+ filters
        // "name ~= Go, minPlayers >= 2, maxPlayers == 5"
        return false;           

    }

    /**
     * processes string with one filter.
     * @param filter string contains one filter
     * @param filteredGames a stream of games that user send in
     * @return
     */
    private Stream<BoardGame> filterSingle(String filter, Stream<BoardGame> filteredGames) {
        Operations operator = Operations.getOperatorFromStr(filter);
        if (operator == null) {
            return filteredGames;
        }
        // remove spaces
        filter = filter.replaceAll(" ", "");

        String[] parts = filter.split(operator.getOperator());
        if (parts.length != 2) {
            return filteredGames;
        }
        GameData column;
        try {
            column = GameData.fromString(parts[0]);
        } catch (IllegalArgumentException e) {
            return filteredGames;
        }

        String value;
        try {
            value = parts[1].trim();
        } catch (IllegalArgumentException e) {
            return filteredGames;
        }
        
        filteredGames = filteredGames.filter(game -> Filter.filter(game, column, operator, value));
        
        return filteredGames;

    }

    /**
     * processes string with 2+ filter.
     * @param filter string contains 2+ filter
     * @param filteredGames a stream of games that user send in
     * @return Stream<BoardGame> a stream of games that is filtered
     */
    private Stream<BoardGame> filterMulti(String filter, Stream<BoardGame> filteredGames) {
        // remove spaces
        filter = filter.replaceAll(" ", ""); 
        // remove commas       
        String[] parts = filter.split(",");
        // for every part in the filter
        // part breaks into smallPart to get the column, operator, value we need
        for (String part : parts) {
            Operations operator = Operations.getOperatorFromStr(part);
            String[] smallParts = part.split(operator.getOperator());
            if (smallParts.length != 2) {
                return filteredGames;
            }
            GameData column;
            try {
                column = GameData.fromString(smallParts[0]);
            } catch (IllegalArgumentException e) {
                return filteredGames;
            }
            String value;
            try {
                value = smallParts[1].trim();
            } catch (IllegalArgumentException e) {
                return filteredGames;
            }
            filteredGames = filteredGames.filter(game -> Filter.filter(game, column, operator, value));
        }
        return filteredGames;
    }    
}
