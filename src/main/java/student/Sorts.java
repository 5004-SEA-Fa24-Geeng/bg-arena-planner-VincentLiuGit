package student;

import java.util.Comparator;


public final class Sorts {
    /**
     * Private constructor to prevent instantiation.
     */    
    private Sorts() { };

    /**
     * Use sortOn to decide which sort class to use, and asc to decide which order.
     * @param sortOn collection.csv column name
     * @param asc true = asc, false = desc
     * @return Comparator<BoardGame>
     */
    public static Comparator<BoardGame> getSortType(String sortOn, boolean asc) {
        switch (sortOn) {
            case "objectname":
                return asc ? new SortByName() : new SortByNameDesc();
            case "maxplayers":
                return asc ? new SortByMaxPlayers() : new SortByMaxPlayersDesc();
            case "minplayers":
                return asc ? new SortByMinPlayers() : new SortByMinPlayersDesc();
            case "minplaytime":
                return asc ? new SortByMinPlaytime() : new SortByMinPlaytimeDesc();
            case "maxplaytime":
                return asc ? new SortByMaxPlaytime() : new SortByMaxPlaytimeDesc();
            case "rank":
                return asc ? new SortByRank() : new SortByRankDesc();
            case "average":
                return asc ? new SortByRating() : new SortByRatingDesc();
            case "avgweight":
                return asc ? new SortByDifficulty() : new SortByDifficultyDesc();
            case "yearpublished":
                return asc ? new SortByYear() : new SortByYearDesc();
            default:
                return ((o1, o2) -> 0);
        }        

    }

    /**
     * SortByName asc.
     */
    public static class SortByName implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }
    /**
     * SortByName desc.
     */
    public static class SortByNameDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o2.getName().compareToIgnoreCase(o1.getName());
        }
    }    
    /**
     * SortByMaxPlayers asc.
     */
    public static class SortByMaxPlayers implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o1.getMaxPlayers() - o2.getMaxPlayers();
        }
    }
    /**
     * SortByMaxPlayers desc.
     */
    public static class SortByMaxPlayersDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o2.getMaxPlayers() - o1.getMaxPlayers();
        }
    }    
    /**
     * SortByMinPlayers asc.
     */
    public static class SortByMinPlayers implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o1.getMinPlayers() - o2.getMinPlayers();
        }
    }   
    /**
     * SortByMinPlayers desc.
     */
    public static class SortByMinPlayersDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o2.getMinPlayers() - o1.getMinPlayers();
        }
    }      
    /**
     * SortByMaxPlaytime asc.
     */    
    public static class SortByMaxPlaytime implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o1.getMaxPlayTime() - o2.getMaxPlayTime();
        }
    }
    /**
     * SortByMaxPlaytime desc.
     */  
    public static class SortByMaxPlaytimeDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o2.getMaxPlayTime() - o1.getMaxPlayTime();
        }
    }    
    /**
     * SortByMinPlaytimeDesc asc.
     */  
    public static class SortByMinPlaytime implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o1.getMinPlayTime() - o2.getMinPlayTime();
        }
    }  
    /**
     * SortByMinPlaytimeDesc desc.
     */  
    public static class SortByMinPlaytimeDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o2.getMinPlayTime() - o1.getMinPlayTime();
        }
    }      
    /**
     * SortByRankDesc asc.
     */  
    public static class SortByRank implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o1.getRank() - o2.getRank();
        }
    }
    /**
     * SortByRankDesc desc.
     */  
    public static class SortByRankDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o2.getRank() - o1.getRank();
        }
    }    
    /**
     * SortByRating asc.
     */  
    public static class SortByRating implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return Double.compare(o1.getRating(), o2.getRating());
        }
    }  
    /**
     * SortByRating desc.
     */  
    public static class SortByRatingDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return Double.compare(o2.getRating(), o1.getRating());
        }
    }     
    /**
     * SortByDifficulty asc.
     */      
    public static class SortByDifficulty implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return Double.compare(o1.getDifficulty(), o2.getDifficulty());
        }
    }
    /**
     * SortByDifficulty desc.
     */   
    public static class SortByDifficultyDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return Double.compare(o2.getDifficulty(), o1.getDifficulty());
        }
    }    
    /**
     * SortByYear asc.
     */  
    public static class SortByYear implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o1.getYearPublished() - o2.getYearPublished();
        }
    }      
    /**
     * SortByYearDesc desc.
     */  
    public static class SortByYearDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o2.getYearPublished() - o1.getYearPublished();
        }
    }        

    
}