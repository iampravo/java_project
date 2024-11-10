# CronExpressionParser

#### Deliveroo Coding Assignment

### Description
- This project parse the Cron Expression and return the detailed description of it.

### Pre Requisites

- Install Java 1.8 on the host machine, validate the installation by running ```java -version```
- Install Gradle on the host machine, validate the installation by running ```gradle -v```
- Either fork and check out this git repository using [CronExpressionParser Clone](https://github.com/iampravo/CronExpressionParser.git)
- Or download the [CronExpressionParser-Zip](https://github.com/iampravo/CronExpressionParser/archive/refs/heads/main.zip) and unzip it on the host machine.

### Exceution Steps
- ```gradle clean```
- ```gradle build```
- ```gradle run```
    - <details>
       <summary>Output</summary>
         <pre>
            $ gradle run --args='*/15 0 1,15 * 1-5 /usr/bin/find'
            > Task :run
            2022-07-20 19:16:34 INFO  AbstractExpressionParser:19 - Started parsing expression : */15
            2022-07-20 19:16:34 INFO  AbstractExpressionParser:23 - Completed parsing token parsing expression : */15 Parsed value : minute->0 15 30 45
            2022-07-20 19:16:34 INFO  AbstractExpressionParser:19 - Started parsing expression : 0
            2022-07-20 19:16:34 INFO  AbstractExpressionParser:23 - Completed parsing token parsing expression : 0 Parsed value : hour->0
            2022-07-20 19:16:34 INFO  AbstractExpressionParser:19 - Started parsing expression : 1,15
            2022-07-20 19:16:34 INFO  AbstractExpressionParser:23 - Completed parsing token parsing expression : 1,15 Parsed value : day of month->1 15
            2022-07-20 19:16:34 INFO  AbstractExpressionParser:19 - Started parsing expression : *
            2022-07-20 19:16:34 INFO  AbstractExpressionParser:23 - Completed parsing token parsing expression : * Parsed value : month->1 2 3 4 5 6 7 8 9 10 11 12
            2022-07-20 19:16:34 INFO  AbstractExpressionParser:19 - Started parsing expression : 1-5
            2022-07-20 19:16:34 INFO  AbstractExpressionParser:23 - Completed parsing token parsing expression : 1-5 Parsed value : day of week->1 2 3 4 5
            2022-07-20 19:16:34 INFO  AbstractExpressionParser:19 - Started parsing expression : /usr/bin/find
            2022-07-20 19:16:34 INFO  AbstractExpressionParser:23 - Completed parsing token parsing expression : /usr/bin/find Parsed value : command->/usr/bin/find
            minute->0 15 30 45
            hour->0
            day of month->1 15
            month->1 2 3 4 5 6 7 8 9 10 11 12
            day of week->1 2 3 4 5
            command->/usr/bin/find
         </pre>
     </details>

### Notes
- In this project, The main class [CronExpressionParser](https://github.com/iampravo/CronExpressionParser/blob/main/src/main/java/CronExpressionParser.java) initiates the parsing of Requested Cron Expression.
- The Cron Expression parsing used [Chain of Responsibility Design](https://refactoring.guru/design-patterns/chain-of-responsibility)
- In this implementation,each field are independently validated, parsed, output-generated in sequence.
- Further, at the end of the parsing, the resultant output is sent back to console.
- Furthermore, the gradle.build has following dependencies : 
  - *log4j*  for Logging the invocation of public methods.
  - *commons-lang3* for String Utilities
  - *mockito* for mocking the Parsers
  - *junit* for JUnit Testing

    
