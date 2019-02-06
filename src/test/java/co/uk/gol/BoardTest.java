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
    Board board = new Board(parseCells( "[1,1,1] [1,1,1] [1,1,1]"));
    Board evo1 = board.evolve();
    Board evo2 = evo1.evolve();
    assertEquals("Evolution is not as expected", "[1,0,1] [0,0,0] [1,0,1]", buildBits(evo1));
    assertEquals("Evolution is not as expected", "[0,0,0] [0,0,0] [0,0,0]", buildBits(evo2));

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
    Board board = new Board(parseCells("[1,0,0] [0,1,0] [0,0,1]"));
    assertEquals("Evolution is not as expected", "[0,0,0] [0,1,0] [0,0,0]", buildBits(board.evolve()));

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
   *       [
   *        [1 1 0]
   *        [1 1 0]
   *        [0 0 0]
   *      ]
   */
  @Test
  public void testCreationOfLife() {
    Board board = new Board(parseCells("[0,1,0] [1,1,0] [0,0,0]"));
    Board evo1 = board.evolve();
    Board evo2 = evo1.evolve();
    printCells(board);
    printCells(evo1);
    printCells(evo2);
    assertTrue ("Board is empty when it has an element", evo1.isAlive());
    assertTrue("Board is empty when evolving from underpopulation", evo2.isAlive());
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
    Board board = new Board(parseCells("[0,0,0] [0,0,0] [0,0,0]"));
    Board evo1 = board.evolve();
    Board evo2 = evo1.evolve();
    printCells(board);
    printCells(evo1);
    printCells(evo2);
    assertEquals("Board is empty when it has an element", false, evo1.isAlive());
    assertTrue("Board is empty when evolving from underpopulation", !evo2.isAlive());
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
    Board board = new Board(parseCells("[0,0,0] [1,1,1] [0,0,0]"));
    Board evo1 = board.evolve();
    Board evo2 = evo1.evolve();
    printCells(board);
    printCells(evo1);
    printCells(evo2);
    assertTrue ("Board is empty when it has an element", evo1.isAlive());
    assertTrue("Board is empty when evolving from underpopulation", evo2.isAlive());
  }



}
