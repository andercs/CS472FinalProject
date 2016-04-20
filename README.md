# CS472FinalProject

Course: CS 472 - Artificial Intelligence (Spring 2016)
Assignment: Final Project (CSPs)

-----------
Description
-----------
The following project is designed to explore the usage of search algorithms
applied to constraint satisfaction problems (CSPs). In particular, we examine
Sudoku as a CSP and apply traditional Backtracking Search along with a number
of heuristics to examine their performance. Finally, we examine a MinConflicts
solution, and examine why its application to Sudoku has a very low chance of
success.

------
Source
------
    All code written for this project can be found within the src directory,
    under the root directory.

    The main program can be found within the App.java file.

------------
Dependencies
------------
    Included in the lib directory you will find the following dependencies:
        aima-core.jar (https://github.com/aimacode/aima-java)

---------------------
Instructions to build
---------------------
    1. Navigate to the root of the project directory.
    2. Use the build tool, Apache Maven, in the root directory to first
        install the jar file provided in lib.
        - Ex. mvn install:install-file -Dfile=lib/aima-core-0.11.1.jar 
              -DgroupId=com.aima.custom -DartifactId=aima-core 
              -Dversion=0.11.1 -Dpackaging=jar
    3. Run Maven in the root directory to package the project and ensure it
        builds successfully.
        - Ex. mvn package

-------------------
Instructions to run
-------------------
    Option 1
        1. Navigate to the root of the project directory.
        2. Use the build tool, Apache Maven, in the root directory to run the
           program.
            - Ex: mvn exec:java 
                  -Dexec.mainClass="com.wvu.ai.csp.project.SudokuSolver.App"

    Option 2
        1. Follow the instructions to build the project as specified above.
        2. Navigate to the "/target" directory, under the root
           directory.
        3. Use "java -jar SudokuSolver.jar"

-------
License
-------
    Read LICENSE in the root directory of this project.

-------------
Special Notes
-------------
    For the purposes of this project, the aima-core project was modified
    slightly to allow for certain behaviors to be achieved.

    This project was modified and re-compiled in accordance with both the
    MIT License and the instructions specified at, respectively:
        https://github.com/aimacode/aima-java/blob/AIMA3e/LICENCE.md
        https://github.com/aimacode/aima-java/wiki/AIMA3e-Workspace-Setup

--------------------
Example Sudoku Input
--------------------
    Easy Puzzle: 0 2 0 1 7 8 0 3 0 0 4 0 3 0 2 0 9 0 1 0 0 0 0 0 0 0 6 0 0 8 6
    0 3 5 0 0 3 0 0 0 0 0 0 0 4 0 0 6 7 0 9 2 0 0 9 0 0 0 0 0 0 0 2 0 8 0 9 0 
    1 0 6 0 0 1 0 4 3 6 0 5 0

    Hard Puzzle: 0 1 0 0 0 3 5 0 0 0 0 0 0 0 0 1 0 0 0 0 4 0 8 0 0 0 0 5 4 0 6
    0 0 0 0 0 0 0 2 0 0 0 0 9 0 6 7 0 0 2 0 0 0 3 0 2 0 0 0 0 0 0 0 0 0 0 5 0
    0 0 0 8 0 0 0 4 7 6 0 2 9

    World's Hardest Puzzle: 8 0 0 0 0 0 0 0 0 0 0 3 6 0 0 0 0 0 0 7 0 0 9 0 2 0
    0 0 5 0 0 0 7 0 0 0 0 0 0 0 4 5 7 0 0 0 0 0 1 0 0 0 3 0 0 0 1 0 0 0 0 6 8 0
    0 8 5 0 0 0 1 0 0 9 0 0 0 0 4 0 0
