# Game of Life

## Problem description
The Game of Life is set in an infinite two-dimensional grid inhabited by “cells”. Every cell interacts with up to eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent.
From an initial seed grid the game "evolves" one iteration at a time. An iteration applies rules to the grid to determine its next state. These scenarios are:

### Scenario 0: No interactions
	Given a game of life
	When there are no live cells
	Then on the next step there are still no live cells
### Scenario 1: Underpopulation
	Given a game of life
	When a live cell has fewer than two neighbours
	Then this cell dies
### Scenario 2: Overcrowding
	Given a game of life
	When a live cell has more than 	three neighbours
	Then this cell dies
### Scenario 3: Survival
	Given a game of life
	When a live cell has two or three neighbours
	Then this cell stays alive
### Scenario 4: Creation of Life
	Given a game of life
	When an empty position has exactly three neighbouring cells
	Then a cell is created in this position
When applied these scenarios result in the following:
### Scenario 5: Grid with no live cells
	Given a game of life with the initial state containing no live cells
	When the game evolves one turn
	Then the next state also contains no live cells

## How to run
You need openjdk-11 and Maven

All the screnarios described in the problem description are represented in a test implementation.
To run the tests execute `mvn test` from the console.

To play with it open the project in any IDE and pass parameters to the main method.
If you prefer to run it from the console. First: `mvn compile` and then:
 - on Linux/MacOS: `mvn exec:java -Dexec.mainClass="co.uk.gol.Main" -Dexec.args="[[1, 0],[0, 0]]"`
 - on Windows: `mvn exec:java -D"exec.mainClass"="co.uk.gol.Main" -D"exec.args"="[[1, 0],[0, 0]]"`