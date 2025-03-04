# Board Game Arena Planner Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram 

Place your class diagrams below. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)

### Provided Code

Provide a class diagram for the provided code as you read through it.  For the classes you are adding, you will create them as a separate diagram, so for now, you can just point towards the interfaces for the provided code diagram.

```mermaid
---
title: bg arena planner UML
---
classDiagram
    direction LR
    BGArenaPlanner --> Planner : uses
    BGArenaPlanner --> GameList : uses
    BGArenaPlanner --> ConsoleApp : uses
    ConsoleApp --> IGameList : has a
    ConsoleApp --> IPlanner : has a
    ConsoleApp --> ConsoleText : has a
    IGameList <|-- GameList : implements
    IPlanner <|-- Planner : implements

    class BGArenaPlanner {
        - BGArenaPlanner()
        + main(String[] args) : void
    }
    class BoardGame {
        - name : String
        - id : int
        - minPlayers : int
        - maxPlayers : int
        - maxPlayTime : int
        - minPlayTime : int
        - difficulty : double
        - rank : int
        - averageRating : double
        - yearPublished : int
        + BoardGame(String name, int id, int minPlayers, int maxPlayers, int minPlayTime, int maxPlayTime, double difficulty, int rank, double averageRating, int yearPublished)
        + getName() : String
        + getId() : String
        + getMinPlayers() : int
        + getMaxPlayers() : int
        + getMaxPlayTime() : int
        + getMinPlayTime() : int
        + getDifficulty() : double
        + getRank() : int
        + getYearPublished() : int
        + toStringWithInfo(GameData col) : String
        + toString() : String 
        + equals(Object obj) : boolean
        + hashCode() : int
        + main(String[] args) : void
    }
    class ConsoleApp {
        - IN : Scanner
        - DEFAULT_FILENAME : String
        - RND : Random
        - current : Scanner
        - gameList : IGameList
        - planner : IPlanner
        + ConsoleApp(IGameList gameList, IPlanner planner)
        + start() : void
        + randomNumber() : String
        + processHelp() : void
        + processFilter() : void
        + printFilterStream(Stream<BoardGame> games, GameData sortON) : void
        + processListCommands() : void
        + nextCommand() : ConsoleText
        + remainder() : String
        + getInput(String format, Object... args) : String
        + printOutput(String format, Object... output) : void
    }
    class ConsoleText {
        <<enum>>
        WELCOME
        HELP
        INVALID
        GOODBYE
        PROMPT
        NO_FILTER
        NO_GAMES_LIST
        FILTERED_CLEAR
        LIST_HELP
        FILTER_HELP
        INVALID_LIST
        EASTER_EGG
        CMD_EASTER_EGG
        CMD_EXIT
        CMD_HELP
        CMD_QUESTION
        CMD_FILTER
        CMD_LIST
        CMD_SHOW
        CMD_ADD
        CMD_REMOVE
        CMD_CLEAR
        CMD_SAVE
        CMD_OPTION_ALL
        CMD_SORT_OPTION
        CMD_SORT_OPTION_DIRECTION_ASC
        CMD_SORT_OPTION_DIRECTION_DESC
        - CTEXT : Properties
        + toString() : String
        + fromString(String str) : ConsoleText
    }
    class GameData {
        <<enum>>
        + NAME("objectname")
        + ID("objectid")
        + RATING("average")
        + DIFFICULTY("avgweight")
        + RANK("rank")
        + MIN_PLAYERS("minplayers")
        + MAX_PLAYERS("maxplayers")
        + MIN_TIME("minplaytime")
        + MAX_TIME("maxplaytime")
        + YEAR("yearpublished")
        - columnName : String
        + GameData(String columnName)
        + getColumnName() : String
        + fromColumnName(String columnName) : GameData
        + fromString(String name) : GameData
    }
    class GameList {
        + GameList()
        + getGameNames() : List<String>
        + clear() : void 
        + count() : int
        + saveGame(String filename) : void
        + addToList(String str, Stream<BoardGame> filtered) : void
        + removeFromList(String str) : void
    }
    class GamesLoader {
        - DELIMITER : String
        - GamesLoader()
        + loadGamesFile(String filename) : Set<BoardGame>
        - toBoardGame(String line, Map<GameData, Integer> columnMap) : BoardGame
        - processHeader(String header) : Map<GameData, Integer>
    }    
    class IGameList {
        <<interface>>
        + ADD_ALL : String
        + getGameNames() : List<String>
        + clear() : void
        + count() : int
        + saveGame(String filename) : void
        + addToList(String str, Stream<BoardGame> filtered) : void
        + removeFromList(String str) : void
    }
    class IPlanner {
        <<interface>>
        + filter(String filter) : Stream<BoardGame>
        + filter(String filter, GameData sortOn) : Stream<BoardGame>
        + filter(String filter, GameData sortOn, boolean ascending) : Stream<BoardGame>
        + reset() : void
    }    
    class Operations {
        <<enum>>
        + EQUALS("==")
        + NOT_EQUALS("!=")
        + GREATER_THAN(">")
        + LESS_THAN("<")
        + GREATER_THAN_EQUALS(">=")
        + LESS_THAN_EQUALS("<=")
        + CONTAINS("~=")
        + Operations(String operator)
        + getOperator() : String
        + fromOperator(String operator) : Operations
        + getOperatorFromStr(String str) : Operations
    }
    class Planner {
        + Planner(Set<BoardGame> games)
        + filter(String filter) : Stream<BoardGame>
        + filter(String filter, GameData sortOn) : Stream<BoardGame> 
        + filter(String filter, GameData sortOn, boolean ascending) : Stream<BoardGame>
        + reset() : void
    }    

```

### Your Plans/Design

Create a class diagram for the classes you plan to create. This is your initial design, and it is okay if it changes. Your starting points are the interfaces. 

```mermaid
---
title: bg arena planner UML
---
classDiagram
    direction LR
    IGameList <|-- GameList : implements
    IPlanner <|-- Planner : implements
    Planner --> BoardGame : uses
    GameList --> BoardGame : uses
    GameList --> GameData : uses  
    Planner --> Filter : uses
    Planner --> Sorting : uses    
    Sorting --> GameData : uses  
    Filter --> Operations : uses   
    Filter --> BoardGame : has a           

    class IGameList {
        <<interface>>
        + ADD_ALL : String
        + getGameNames() : List<String>
        + clear() : void
        + count() : int
        + saveGame(String filename) : void
        + addToList(String str, Stream<BoardGame> filtered) : void
        + removeFromList(String str) : void
    }
    class IPlanner {
        <<interface>>
        + filter(String filter) : Stream<BoardGame>
        + filter(String filter, GameData sortOn) : Stream<BoardGame>
        + filter(String filter, GameData sortOn, boolean ascending) : Stream<BoardGame>
        + reset() : void
    }    
    class Planner {
        + Planner(Set<BoardGame> games)
        + filter(String filter) : Stream<BoardGame>
        + filter(String filter, GameData sortOn) : Stream<BoardGame> 
        + filter(String filter, GameData sortOn, boolean ascending) : Stream<BoardGame>
        + reset() : void
    }        
    class GameList {
        + GameList()
        + getGameNames() : List<String>
        + clear() : void 
        + count() : int
        + saveGame(String filename) : void
        + addToList(String str, Stream<BoardGame> filtered) : void
        + removeFromList(String str) : void
    }
    class BoardGame {
        - name : String
        - id : int
        - minPlayers : int
        - maxPlayers : int
        - maxPlayTime : int
        - minPlayTime : int
        - difficulty : double
        - rank : int
        - averageRating : double
        - yearPublished : int
        + BoardGame(String name, int id, int minPlayers, int maxPlayers, int minPlayTime, int maxPlayTime, double difficulty, int rank, double averageRating, int yearPublished)
        + getName() : String
        + getId() : String
        + getMinPlayers() : int
        + getMaxPlayers() : int
        + getMaxPlayTime() : int
        + getMinPlayTime() : int
        + getDifficulty() : double
        + getRank() : int
        + getYearPublished() : int
        + toStringWithInfo(GameData col) : String
        + toString() : String 
        + equals(Object obj) : boolean
        + hashCode() : int
        + main(String[] args) : void
    }    
    class GameData {
        <<enum>>
        + NAME("objectname")
        + ID("objectid")
        + RATING("average")
        + DIFFICULTY("avgweight")
        + RANK("rank")
        + MIN_PLAYERS("minplayers")
        + MAX_PLAYERS("maxplayers")
        + MIN_TIME("minplaytime")
        + MAX_TIME("maxplaytime")
        + YEAR("yearpublished")
        - columnName : String
        + GameData(String columnName)
        + getColumnName() : String
        + fromColumnName(String columnName) : GameData
        + fromString(String name) : GameData
    }
    class Operations {
        <<enum>>
        + EQUALS("==")
        + NOT_EQUALS("!=")
        + GREATER_THAN(">")
        + LESS_THAN("<")
        + GREATER_THAN_EQUALS(">=")
        + LESS_THAN_EQUALS("<=")
        + CONTAINS("~=")
        + Operations(String operator)
        + getOperator() : String
        + fromOperator(String operator) : Operations
        + getOperatorFromStr(String str) : Operations
    }    
    class Filter {
        - operation : Operations
        - boardGame : BoardGame
        + Filter(Operations operation, BoardGame boardGame)
        + nameFilter() : BoardGame
        + maxPlayersFilter() : BoardGame
        + minPlayersFilter() : BoardGame
        + minPlaytimeFilter() : BoardGame
        + maxPlaytimeFilter() : BoardGame
        + rankFilter() : BoardGame
        + ratingFilter() : BoardGame
        + difficultyFilter() : BoardGame
        + yearFilter() : BoardGame  
    }        
    class Sorting {
        - sortOn : GameData
        - filteredBoardGames : Stream<BoardGame>
        - sorting : boolean
        + Sorting(GameData sortOn, Stream<BoardGame> filteredBoardGames)        
        + Sorting(GameData sortOn, Stream<BoardGame> filteredBoardGames, boolean sorting)
        + nameFilter() : Stream<BoardGame>
        + maxPlayersSortOn() : Stream<BoardGame>
        + minPlayersSortOn() : Stream<BoardGame>
        + minPlaytimeSortOn() : Stream<BoardGame>
        + maxPlaytimeSortOn() : Stream<BoardGame>
        + rankSortOn() : Stream<BoardGame>
        + ratingSortOn() : Stream<BoardGame>
        + difficultySortOn() : Stream<BoardGame>
        + yearSortOn() : Stream<BoardGame>  
        + idSortOn() : Stream<BoardGame>          
    }
```


## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test getters and setters in BoardGame
2. Test different filter scenarios using Filter
3. Test all the methods in GameList
4. Test all the toString and fromString methods in Operations, GameData and ConsoleText
5. Test different sorting scenarios using Sorting
6. Test scenarios when filter methods in Planner receive different inputs
7. Test scenarios when start() method in ConsoleApp receive different CMD


## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

For the final design, you just need to do a single diagram that includes both the original classes and the classes you added. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.





## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning to information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 
