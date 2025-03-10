package student;

import java.nio.file.OpenOption;

import student.BoardGame;
import student.GameData;
import student.Operations;

public class Filter {

    /**
     * Private constructor to prevent instantiation.
     */
    private Filter() {};

    /**
     * filter board games using game data, operations, and the value user send in.
     * use in the filter() of Stream.
     * @param game board game
     * @param column game data name, ex: name, max players etc
     * @param op operations, ex: equals, greater then etc
     * @param value the value used to compare with the value of a board game
     * @return boolean, true if the game fits the filter, else false
     */
    public static boolean filter(BoardGame game, GameData column, Operations op, String value) {

        switch (column) {
            case NAME:
                //filter NAME
                return filterString(game.getName(), op, value);
            case MAX_PLAYERS:
                //filter MAX_PLAYERS
                return filterNum(game.getMaxPlayers(), op, value);      
            case MIN_PLAYERS:
                //filter MIN_PLAYERS
                return filterNum(game.getMinPlayers(), op, value);    
            case MAX_TIME:
                //filter MAX_TIME
                return filterNum(game.getMaxPlayTime(), op, value);    
            case MIN_TIME:
                //filter MIN_TIME
                return filterNum(game.getMinPlayTime(), op, value);    
            case RANK:
                //filter RANK
                return filterNum(game.getRank(), op, value); 
            case RATING:
                //filter RATING
                return filterDouble(game.getRating(), op, value); 
            case DIFFICULTY:
                //filter DIFFICULTY
                return filterDouble(game.getDifficulty(), op, value); 
            case YEAR:
                //filter YEAR
                return filterNum(game.getYearPublished(), op, value); 
            case ID:
                //filter ID
                return filterNum(game.getId(), op, value);                 
            default:
                return false;
        }
    }

    /**
     * Compares strings.
     * @param gameData the name of the board game 
     * @param op operations, ex: equals, greater then etc
     * @param value the value used to compare with the value of a board game
     * @return boolean, true if the game fits the filter, else false
     */
    public static boolean filterString(String gameData, Operations op, String value) {

        switch (op) {
            case EQUALS:
                return gameData.equals(value);
            case NOT_EQUALS:
                return !(gameData.equals(value));
            case GREATER_THAN:
                return false;
            case LESS_THAN:
                return false;
            case GREATER_THAN_EQUALS:
                return false;
            case LESS_THAN_EQUALS:
                return false;
            case CONTAINS:
                return gameData.contains(value);                                                                                                
            default:
                return false;
        }
    }

    /**
     * Compares integers.
     * @param gameData fields except name, difficulty and averageRating 
     * @param op operations, ex: equals, greater then etc
     * @param value the value used to compare with the value of a board game
     * @return boolean, true if the game fits the filter, else false
     */
    public static boolean filterNum(int gameData, Operations op, String value) {
        int valueInt = Integer.parseInt(value);

        switch (op) {
            case EQUALS:
                return (gameData == valueInt);
            case NOT_EQUALS:
                return (gameData != valueInt);
            case GREATER_THAN:
                return (gameData > valueInt);
            case LESS_THAN:
                return (gameData < valueInt);
            case GREATER_THAN_EQUALS:
                return (gameData >= valueInt);
            case LESS_THAN_EQUALS:
                return (gameData <= valueInt);
            case CONTAINS:
                return false;                                                                                              
            default:
                return false;
        }
    }    

    /**
     * Compares double.
     * @param gameData fields like difficulty and averageRating 
     * @param op operations, ex: equals, greater then etc
     * @param value the value used to compare with the value of a board game
     * @return boolean, true if the game fits the filter, else false
     */
    public static boolean filterDouble(double gameData, Operations op, String value) {
        double valueDouble = Double.parseDouble(value);

        switch (op) {
            case EQUALS:
                return (gameData == valueDouble);
            case NOT_EQUALS:
                return (gameData != valueDouble);
            case GREATER_THAN:
                return (gameData > valueDouble);
            case LESS_THAN:
                return (gameData < valueDouble);
            case GREATER_THAN_EQUALS:
                return (gameData >= valueDouble);
            case LESS_THAN_EQUALS:
                return (gameData <= valueDouble);
            case CONTAINS:
                return false;                                                                                              
            default:
                return false;
        }
    }        
}
