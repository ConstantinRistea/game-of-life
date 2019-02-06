package co.uk.gol;

import org.junit.Test;

import java.util.Vector;

import static co.uk.gol.Main.*;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class BoardTest {

  /**
   * Scenario 0: No interactions
   */
  @Test
  public void testEmptyBoard() {
    assertFalse("Empty board contains elements", new Board(new Vector<>()).isAlive());
  }

  /***
   *  Scenario 1: Underpopulation
   *       [
   *        [1,0]
   *        [0,0]
   *      ]
   *
   *  Expected outcome:
   *       [
   *        [0,0]
   *        [0,0]
   *      ]
   */
  @Test
  public void testUnderpopulation() {
    Board board = new Board(parseCells("[1,0] [0,0]"));
    printCells(board);
    assertTrue("Board is empty when it has an element", board.isAlive());
    Board evolvedBoard = board.evolve();
    assertFalse("Board is empty when evolving from underpopulation", evolvedBoard.isAlive());
    printCells(evolvedBoard);
  }

  /***
   *  Scenario 2: Overcrowding
   *       [
   *        [1 1 1]
   *        [1 1 1]
   *        [1 1 1]
   *      ]
   *
   *  Expected outcome:
   *       [
   *        [1 0 1]
   *        [0 0 0]
   *        [1 0 1]
   *      ]
   *
   *       [
   *        [0 0 0]
   *        [0 0 0]
   *        [0 0 0]
   *      ]
   */
  @Test
  public void testOvercrowding() {
    testBoardEvolution("[1,1,1] [1,1,1] [1,1,1]", "[1,0,1] [0,0,0] [1,0,1]");
  }

  private void testBoardEvolution(String input, String output) {
    Board board = new Board(parseCells(input));
    assertEquals("Evolution is not as expected", output, buildBits(board.evolve()));
  }

  /***
   *  Scenario 3: Survival
   *       [
   *        [1 0 0]
   *        [0 1 0]
   *        [0 0 1]
   *      ]
   *
   *  Expected outcome:
   *       [
   *        [0 0 0]
   *        [0 1 0]
   *        [0 0 0]
   *      ]
   *
   *
   */
  @Test
  public void testSurvival() {
    testBoardEvolution("[1,0,0] [0,1,0] [0,0,1]", "[0,0,0] [0,1,0] [0,0,0]");


  }


  /***
   *  Scenario 4: Creation of Life
   *       [
   *        [0 1 0]
   *        [1 1 0]
   *        [0 0 0]
   *      ]
   *
   *  Expected outcome:
   *       [
   *        [1 1 0]
   *        [1 1 0]
   *        [0 0 0]
   *      ]
   *
   *
   */
  @Test
  public void testCreationOfLife() {
    testBoardEvolution("[0,1,0] [1,1,0] [0,0,0]","[1,1,0] [1,1,0] [0,0,0]");
  }

  /***
   *  Scenario 5: Grid with no live cells
   *       [
   *        [0 0 0]
   *        [0 0 0]
   *        [0 0 0]
   *      ]
   *
   *  Expected outcome:
   *       [
   *        [0 0 0]
   *        [0 0 0]
   *        [0 0 0]
   *      ]
   *
   *
   */
  @Test
  public void testGridWithNoLifeCells() {
    testBoardEvolution("[0,0,0] [0,0,0] [0,0,0]","[0,0,0] [0,0,0] [0,0,0]");

  }

  /***
   *  Scenario 6: Expected game outcome for seeded grid*
   *       [
   *        [0 0 0]
   *        [1 1 1]
   *        [0 0 0]
   *      ]
   *
   *  Expected outcome:
   *       [
   *        [0 1 0]
   *        [0 1 0]
   *        [0 1 0]
   *      ]
   *
   *       [
   *        [0 0 0]
   *        [1 1 1]
   *        [0 0 0]
   *      ]
   */
  @Test
  public void testExpectedGameOutcomeForSeededGrid() {
    String initial = "[0,0,0] [1,1,1] [0,0,0]";
    String evolved = "[0,1,0] [0,1,0] [0,1,0]";
    testBoardEvolution(initial, evolved);
    testBoardEvolution(evolved, initial);

  }



}
