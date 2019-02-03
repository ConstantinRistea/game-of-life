package co.uk.gol;

import org.junit.Test;

import java.util.Arrays;
import java.util.Vector;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class BoardTest {

  /**
   * Scenario 0: No interactions
   */
  @Test
  public void testEmptyBoard() {
    assertFalse("Empty board contains elements", new Board().isLive());
  }

  /***
   *  Scenario 1: Underpopulation
   *       [
   *        [1 0]
   *        [0 0]
   *      ]
   *
   *  Expected outcome:
   *       [
   *        [0 0]
   *        [0 0]
   *      ]
   */
  @Test
  public void testUnderpopulation() {
    Vector<Cell> a = new Vector<>(Arrays.asList(new Cell(new XYPosition(0, 1), true), new Cell(new XYPosition(1, 1), false)));
    Vector<Cell> b = new Vector<>(Arrays.asList(new Cell(new XYPosition(0, 0), false), new Cell(new XYPosition(1, 0), false)));
    Board board = new Board(new Vector<>(Arrays.asList(a, b)));
    assertTrue("Board is empty when it has an element", board.isLive());
    assertFalse("Board is empty when evolving from underpopulation", board.evolve().isLive());
  }

//  /***
//   *  Scenario 2: Overcrowding
//   *       [
//   *        [1 1 1]
//   *        [1 1 1]
//   *        [1 1 1]
//   *      ]
//   *
//   *  Expected outcome:
//   *       [
//   *        [1 0 1]
//   *        [0 0 0]
//   *        [1 0 1]
//   *      ]
//   *
//   *       [
//   *        [0 0 0]
//   *        [0 0 0]
//   *        [0 0 0]
//   *      ]
//   */
//  @Test
//  public void testOvercrowding() {
//    Vector<Cell> a = new Vector<>(Arrays.asList(new Cell(new XYPosition(0, 2), true), new Cell(new XYPosition(1, 1), true), new Cell(new XYPosition(1, 1), true))));
//    Vector<Cell> b = new Vector<>(Arrays.asList(new Cell(new XYPosition(0, 1), true), new Cell(new XYPosition(1, 0), true)));
//    Vector<Cell> b = new Vector<>(Arrays.asList(new Cell(new XYPosition(0, 0), true), new Cell(new XYPosition(1, 0), true)));
//    Board board = new Board(new Vector<>(Arrays.asList(a, b)));
//    assertTrue("Board is empty when it has an element", board.isLive());
//    assertFalse("Board is empty when evolving from underpopulation", board.evolve().isLive());
//  }
}