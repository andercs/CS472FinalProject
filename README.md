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
        - Ex. mvn install:install-file -Dfile=lib/aima-core.jar 
              -DgroupId=com.aima.custom -DartifactId=aima-core 
              -Dversion=0.11.1 -Dpackaging=jar
    3. Run Maven in the root directory to package the project and ensure it
        builds successfully.
        - Ex. mvn package

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