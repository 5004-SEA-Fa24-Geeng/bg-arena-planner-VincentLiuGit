package student;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


public class Planner implements IPlanner {
    
    private static final String DEFAULT_COLLECTION = "/collection.csv";
    private static final String DEFAULT_SORT_ON_NAME = "objectname";
    Set<BoardGame> games;

    public Planner(Set<BoardGame> games) {
        this.games = games;
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
        this.games = GamesLoader.loadGamesFile(DEFAULT_COLLECTION);
    }

    private boolean checkFilterNum(String filter) {
        // only one filter
        // "name == Go"        
        if (filter.split(",").length == 1) {
            return true;
        // 2+ filters
        // "name ~= Go, minPlayers >= 2, maxPlayers == 5"
        } else {
            return false;            
        }
    }

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

    public static void main(String[] args) { // used for local quick tests
        Set<BoardGame> games = new HashSet<>();
        games.add(new BoardGame("17 days", 6, 1, 8, 70, 70, 9.0, 600, 9.0, 2005));
        games.add(new BoardGame("Chess", 7, 2, 2, 10, 20, 10.0, 700, 10.0, 2006));
        games.add(new BoardGame("Go", 1, 2, 5, 30, 30, 8.0, 100, 7.5, 2000));
        games.add(new BoardGame("Go Fish", 2, 2, 10, 20, 120, 3.0, 200, 6.5, 2001));
        games.add(new BoardGame("golang", 4, 2, 7, 50, 55, 7.0, 400, 9.5, 2003));
        games.add(new BoardGame("GoRami", 3, 6, 6, 40, 42, 5.0, 300, 8.5, 2002));
        games.add(new BoardGame("Monopoly", 8, 6, 10, 20, 1000, 1.0, 800, 5.0, 2007));
        games.add(new BoardGame("Tucano", 5, 10, 20, 60, 90, 6.0, 500, 8.0, 2004));

        IPlanner planner = new Planner(games);
        Stream<BoardGame> filtered = planner.filter("name ~= o, minPlayers >= 1");
        List<String> filteredListName = filtered.map(game -> game.getName()).toList();

    }    


}
