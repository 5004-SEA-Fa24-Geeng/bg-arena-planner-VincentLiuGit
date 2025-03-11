# Report

Submitted report to be manually graded. We encourage you to review the report as you read through the provided
code as it is meant to help you understand some of the concepts. 

## Technical Questions

1. What is the difference between == and .equals in java? Provide a code example of each, where they would return different results for an object. Include the code snippet using the hash marks (```) to create a code block.
   ```java
   // your code here
    class Dog {
        private String name;
        private int weight;
        private int height;
        
        public Dog(String name, int weight, int height) {
            this.name = name;
            this.weight = weight;
            this.height = height;
        }
        public String getName() {
            return this.name;
        }
        public int getWeight() {
            return this.weight;
        }
        public int getHeight() {
            return this.height;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Dog)) {
                return false;
            }
            Dog newObject = (Dog)o;
            return this.getName() == newObject.getName() && this.getWeight() == newObject.getWeight() && this.getHeight() == newObject.getHeight();
        }

        public static void main(String[] args) {
            Dog dog1 = new Dog("Bob", 20, 50);
            Dog dog2 = new Dog("Bob", 20, 50);
            System.out.println(dog1 == dog2); //false
            System.out.println(dog1.equals(dog2)); //true       
        }
    }   
   ```
   '==' compares memory address between two objects, while equals() compares the content of the objects.


2. Logical sorting can be difficult when talking about case. For example, should "apple" come before "Banana" or after? How would you sort a list of strings in a case-insensitive manner? 

    I'd cast all the stings in the list in to lower case, then sort the list.

3. In our version of the solution, we had the following code (snippet)
    ```java
    public static Operations getOperatorFromStr(String str) {
        if (str.contains(">=")) {
            return Operations.GREATER_THAN_EQUALS;
        } else if (str.contains("<=")) {
            return Operations.LESS_THAN_EQUALS;
        } else if (str.contains(">")) {
            return Operations.GREATER_THAN;
        } else if (str.contains("<")) {
            return Operations.LESS_THAN;
        } else if (str.contains("=="))...
    ```
    Why would the order in which we checked matter (if it does matter)? Provide examples either way proving your point. 

    The order matters. If we check ">" first, the method would return Operations.GREATER_THAN when it got inputs like strings containing ">=".

4. What is the difference between a List and a Set in Java? When would you use one over the other? 

    A List is a indexed collection that allows duplicate elements, while a Set is a non-indexed collection that does not allow duplicate elements. If I want to use a collection that contains duplicate elements and can be accessed by their positions, I would use a List. On the contrary, I would use a Set.

5. In [GamesLoader.java](src/main/java/student/GamesLoader.java), we use a Map to help figure out the columns. What is a map? Why would we use a Map here? 

    The map contains key-value pairs, where we access elements in the map using key values, and the map does not allow duplicate keys. We use a Map to make sure that the column names are unique and we need the actual index of these columns.

6. [GameData.java](src/main/java/student/GameData.java) is actually an `enum` with special properties we added to help with column name mappings. What is an `enum` in Java? Why would we use it for this application?

    'enum' represents a group of named finite constants. It's used when we know all the possible values.
    The reason why we use it in this application is that we know all the column names in the collection.csv and all the operations we'll encounter.

7. Rewrite the following as an if else statement inside the empty code block.
    ```java
    switch (ct) {
                case CMD_QUESTION: // same as help
                case CMD_HELP:
                    processHelp();
                    break;
                case INVALID:
                default:
                    CONSOLE.printf("%s%n", ConsoleText.INVALID);
            }
    ``` 

    ```java
    // your code here, don't forget the class name that is dropped in the switch block..
    if (ct.equals(ConsoleText.CMD_QUESTION) || ct.equals(ConsoleText.CMD_HELP)) {
        processHelp();
    } else {
        CONSOLE.printf("%s%n", ConsoleText.INVALID);
    }
    ```

## Deeper Thinking

ConsoleApp.java uses a .properties file that contains all the strings
that are displayed to the client. This is a common pattern in software development
as it can help localize the application for different languages. You can see this
talked about here on [Java Localization – Formatting Messages](https://www.baeldung.com/java-localization-messages-formatting).

Take time to look through the console.properties file, and change some of the messages to
another language (probably the welcome message is easier). It could even be a made up language and for this - and only this - alright to use a translator. See how the main program changes, but there are still limitations in 
the current layout. 

Post a copy of the run with the updated languages below this. Use three back ticks (```) to create a code block. 

```text
// your consoles output here

*******???????????.*******

A tool to help people plan which games they
want to play on Board Game Arena.
```

Now, thinking about localization - we have the question of why does it matter? The obvious
one is more about market share, but there may be other reasons.  I encourage
you to take time researching localization and the importance of having programs
flexible enough to be localized to different languages and cultures. Maybe pull up data on the
various spoken languages around the world? What about areas with internet access - do they match? Just some ideas to get you started. Another question you are welcome to talk about - what are the dangers of trying to localize your program and doing it wrong? Can you find any examples of that? Business marketing classes love to point out an example of a car name in Mexico that meant something very different in Spanish than it did in English - however [Snopes has shown that is a false tale](https://www.snopes.com/fact-check/chevrolet-nova-name-spanish/).  As a developer, what are some things you can do to reduce 'hick ups' when expanding your program to other languages?


As a reminder, deeper thinking questions are meant to require some research and to be answered in a paragraph for with references. The goal is to open up some of the discussion topics in CS, so you are better informed going into industry. 

Localization matters a lot. When a brand is adapting to a local culture, it's easier to reach new customers and gain brand recogntion. Moreover, implementing loclaization helps the brand communicate more efficiently wtih it's target audience.[^1] However, there are some disadvantages doing so, such as high costs. Translating all of the content into a whole new language and even using local terms in the products might cost a lot and take time, since the brand needs to hire local residents who is familiar with both language to do the job and there might not be many of them, espescially the cheap ones. Once you finish the job, that's not all. You need to verify your localization again with other people who aren't involve in the translation job. You don't want to be like one of these bad examples.[^2] Based on these circumstances, I would say localization is a high risk high profit investment. As a developer, there are some methods to aovid problems when implementing localization, such as use separate resource files, give strings room to grow and shrink, always use UTF-8 etc.[^3]


[^1]: https://greatcontent.com/globalization-vs-localization/
[^2]: https://www.polilingua.com/blog/post/cultural-blunders-worst-cases-of-localization.htm
[^3]: https://phrase.com/blog/posts/10-common-mistakes-in-software-localization/