package student;

import java.nio.file.OpenOption;

import student.BoardGame;
import student.GameData;
import student.Operations;

public class Filter {
    private Filter() {};

    public static boolean filter(BoardGame game, GameData column, Operations op, String value) {

        switch (column) {
            case NAME:
                //filter the name
                return filterString(game.getName(), op, value);
                break;
            case MAX_PLAYERS:
                //filter the name
                break;                
        
            default:
                return false;
        }
    }

    public static boolean filterString(String gameData, Operations operations, String value) {

        switch (op) {
            case EQUALS:
                return gameData.equals(value);
                break;
            default:
                return false;
        }
    }

    public static boolean filterNum(int gameData, Operations operations, String value) {
        int valueInt = Integer.parseInt(value);

        switch (op) {
            case EQUALS:
                break;
            default:
                return false;
        }
    }    
}
