package student;

import java.util.Comparator;

public class Sorts {
    private Sorts() {};

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

    public static class SortByName implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }

    public static class SortByNameDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o2.getName().compareToIgnoreCase(o1.getName());
        }
    }    

    public static class SortByMaxPlayers implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o1.getMaxPlayers() - o2.getMaxPlayers();
        }
    }

    public static class SortByMaxPlayersDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o2.getMaxPlayers() - o1.getMaxPlayers();
        }
    }    

    public static class SortByMinPlayers implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o1.getMinPlayers() - o2.getMinPlayers();
        }
    }   

    public static class SortByMinPlayersDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o2.getMinPlayers() - o1.getMinPlayers();
        }
    }      
    
    public static class SortByMaxPlaytime implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o1.getMaxPlayTime() - o2.getMaxPlayTime();
        }
    }

    public static class SortByMaxPlaytimeDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o2.getMaxPlayTime() - o1.getMaxPlayTime();
        }
    }    

    public static class SortByMinPlaytime implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o1.getMinPlayTime() - o2.getMinPlayTime();
        }
    }  

    public static class SortByMinPlaytimeDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o2.getMinPlayTime() - o1.getMinPlayTime();
        }
    }      

    public static class SortByRank implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o1.getRank() - o2.getRank();
        }
    }

    public static class SortByRankDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o2.getRank() - o1.getRank();
        }
    }    

    public static class SortByRating implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return Double.compare(o1.getRating(), o2.getRating());
        }
    }  

    public static class SortByRatingDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return Double.compare(o2.getRating(), o1.getRating());
        }
    }     
    
    public static class SortByDifficulty implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return Double.compare(o1.getDifficulty(), o2.getDifficulty());
        }
    }

    public static class SortByDifficultyDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return Double.compare(o2.getDifficulty(), o1.getDifficulty());
        }
    }    

    public static class SortByYear implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o1.getYearPublished() - o2.getYearPublished();
        }
    }      

    public static class SortByYearDesc implements Comparator<BoardGame> {
        @Override
        public int compare(BoardGame o1, BoardGame o2) {
            return o2.getYearPublished() - o1.getYearPublished();
        }
    }        

    
}