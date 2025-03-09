package student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import student.Sorts.SortByName;

public class GameList implements IGameList {

    private String ADD_ALL = "all";
    private List<BoardGame> gameList;
    
    public GameList() {
        this.gameList = new ArrayList<BoardGame>();
    }

    @Override
    public List<String> getGameNames() {
        if (this.gameList.isEmpty()) {
            List<String> emptyList = new ArrayList<String>();
            return emptyList;
        } else {
            List<String> gameNames = new ArrayList<String>();
            for (BoardGame game: gameList) {
                gameNames.add(game.getName());
            }
            List<String> uniqueGameNames = gameNames.stream().distinct()
                                        .sorted((o1,o2) -> o1.compareToIgnoreCase(o2)).toList();
            return uniqueGameNames;
        }
    }

    @Override
    public void clear() {
        this.gameList = new ArrayList<BoardGame>();
    }

    @Override
    public int count() {
        return this.gameList.size();
    }

    @Override
    public void saveGame(String filename) {
        List<String> lines = this.getGameNames();
        try {
            Files.write(Path.of(filename), lines);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public void addToList(String str, Stream<BoardGame> filtered) throws IllegalArgumentException {

        List<BoardGame> newList = new ArrayList<BoardGame>();
        List<BoardGame> filteredList = filtered.toList();
        // get the second part after the str is splited using " add "
        // and right trim space
        // "list add 7 wonders " -> "list", "7 wonders " -> "7 wonders"
        str = str.split(" add ")[1].replaceAll("\\s+$", "");
        // add range
        // "1-3"
        if (str.contains("-")) {
            String[] rangeNum = str.split("-");
            // turns num into index
            // index starts from 0
            int firstNum = Integer.parseInt(rangeNum[0]) - 1;
            int secondNum = Integer.parseInt(rangeNum[1]) - 1;
            // if secondNum > the count of filtered, then we add all the way to the last element
            // the index fo the last element is size - 1;
            int streamCount = filteredList.size() - 1;
            secondNum = (secondNum > streamCount) ? streamCount : secondNum;
            if (firstNum < 0 || secondNum < 0) {
                throw new IllegalArgumentException("Invalid range number!");
            } else {
                for (int i = firstNum; i <= secondNum; i++) {
                    newList.add(filteredList.get(i));
                }
            }
        } 
        // add all 
        else if (str.contains(ADD_ALL)) {
            newList = filteredList;
        }
        // add single name or single number 
        else {
            // single number 
            if (str.matches("[0-9]+")) {
                int index = Integer.parseInt(str);
                newList.add(filteredList.get(index));
            } 
            // single name
            else {
                String name = str;
                newList = filtered.filter(game -> game.getName().contains(name)).toList();
            }
        }
        // add to list
        List<BoardGame> newGameList = new ArrayList<>();
        newGameList.addAll(this.gameList);
        newGameList.addAll(newList);        
        this.gameList = newGameList;
    }

    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        
        // List is empty then do nothing
        if (this.gameList.isEmpty()) {
            return ;
        }

        // get the second part after the str is splited using " add "
        // and right trim space
        // "list remove 7 wonders " -> "list", "7 wonders " -> "7 wonders"
        str = str.split(" remove ")[1].replaceAll("\\s+$", "");
        // add range
        // "1-3"
        if (str.contains("-")) {
            String[] rangeNum = str.split("-");
            // turns num into index
            // index starts from 0
            int firstNum = Integer.parseInt(rangeNum[0]) - 1;
            int secondNum = Integer.parseInt(rangeNum[1]) - 1;  
            // if secondNum > the size of this.gameList, then we remove all the way to the last element
            // the index fo the last element is size - 1;
            int listCount = this.gameList.size() - 1;            
            secondNum = (secondNum > listCount) ? listCount : secondNum;                
            if (firstNum < 0 || secondNum < 0) {
                throw new IllegalArgumentException("Invalid range number!");
            } else {
                // +1 cuz the second parameter of sublist is exclusive
                this.gameList.subList(firstNum, secondNum + 1).clear();
            }
        } 
        // remove all 
        else if (str.contains(ADD_ALL)) {
            this.clear();
        }
        // remove single name or single number 
        else {
            // single number 
            if (str.matches("[0-9]+")) {
                int index = Integer.parseInt(str);
                this.gameList.remove(index);
            } 
            // single name
            else {
                String name = str;
                this.gameList.removeIf(game -> game.getName().contains(name));
            }
        }        
    }

    public static void main(String[] args) { // used for local quick tests
        Set<BoardGame> games = new HashSet<>();
        IGameList gameList = new GameList();
        games.add(new BoardGame("17 days", 6, 1, 8, 70, 70, 9.0, 600, 9.0, 2005));
        games.add(new BoardGame("Go Fish", 2, 2, 10, 20, 120, 3.0, 200, 6.5, 2001));
        games.add(new BoardGame("golang", 4, 2, 7, 50, 55, 7.0, 400, 9.5, 2003));
        games.add(new BoardGame("GoRami", 3, 6, 6, 40, 42, 5.0, 300, 8.5, 2002));


        IPlanner planner = new Planner(games);
        Stream<BoardGame> filtered = planner.filter("");
        gameList.addToList("list add all ", filtered);
        
        for (String game : gameList.getGameNames()) {
            System.out.println(game);
        }
    }    
}
