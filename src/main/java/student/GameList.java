package student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GameList implements IGameList {

    /**
     * Default key word to use to add or remove an entire filter from/to the list.
     */
    private static final String ADD_ALL = "all";
    /**
     * a set to store board games.
     */
    private Set<BoardGame> gameList;

    /**
     * Create a GameList object.
     */
    public GameList() {
        this.gameList = new HashSet<BoardGame>();
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
                                        .sorted((o1, o2) -> o1.compareToIgnoreCase(o2)).toList();
            return uniqueGameNames;
        }
    }

    @Override
    public void clear() {
        this.gameList.clear();
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
        
        if (str == null) {
            throw new IllegalArgumentException("Invalid range number!");
        }        

        Set<BoardGame> newList = new HashSet<>();        
        str = str.trim();
        // add range
        // "1-3"
        if (str.contains("-")) {
            List<BoardGame> filteredList = filtered.toList();
            String[] rangeNum = str.split("-");
            // turns num into index
            // index starts from 0
            int firstNum = Integer.parseInt(rangeNum[0]) - 1;
            int secondNum = Integer.parseInt(rangeNum[1]) - 1;
            // if secondNum > the count of filtered, then we add all the way to the last element
            // the index fo the last element is size - 1;
            int streamCount = filteredList.size() - 1;
            secondNum = (secondNum > streamCount) ? streamCount : secondNum;
            if (firstNum < 0 || secondNum < 0 || firstNum > secondNum) {
                throw new IllegalArgumentException("Invalid range number!");
            } else {
                for (int i = firstNum; i <= secondNum; i++) {
                    newList.add(filteredList.get(i));
                }
            }
        // add all     
        } else if (str.contains(ADD_ALL)) {
            List<BoardGame> filteredList = filtered.toList();
            newList = filteredList.stream().collect(Collectors.toSet());
        // add single name or single number             
        } else {
            // single number 
            if (str.matches("[0-9]+")) {
                List<BoardGame> filteredList = filtered.toList();
                int index = Integer.parseInt(str);
                newList.add(filteredList.get(index));
            // single name                
            } else {
                String name = str;
                newList = filtered.filter(game -> game.getName().contains(name)).collect(Collectors.toSet());
            }
        }
        // add to list
        Set<BoardGame> newGameList = new HashSet<>();
        newGameList.addAll(this.gameList);
        newGameList.addAll(newList);        
        this.gameList = newGameList;
    }

    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        
        // List is empty then do nothing
        if (this.gameList.isEmpty()) {
            return;
        }
        // cast gameList to list
        List<BoardGame> newGameList = new ArrayList<>(this.gameList);
        str = str.trim();
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
            if (firstNum < 0 || secondNum < 0 || firstNum > secondNum) {
                throw new IllegalArgumentException("Invalid range number!");
            } else {
                // +1 cuz the second parameter of sublist is exclusive
                newGameList.subList(firstNum, secondNum + 1).clear();
                // cast newGameList to set and be sorted in ascending alphabetical order
                this.gameList = newGameList.stream()
                                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                                .collect(Collectors.toSet());
            }
        // remove all 
        } else if (str.contains(ADD_ALL)) {
            this.clear();
        // remove single name or single number             
        } else {
            // single number 
            if (str.matches("[0-9]+")) {
                int index = Integer.parseInt(str);
                newGameList.remove(index);
                this.gameList = newGameList.stream()
                                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                                .collect(Collectors.toSet());
            // single name                                
            } else {
                String name = str;
                this.gameList.removeIf(game -> game.getName().contains(name));
            }
        }        
    }      
}
