package co.uk.gol;

import java.util.stream.Stream;

/**
 * Represents a cell, it's position in space and whether it's alive
 */
final class Cell {
  // because these are final, we don't need getters
  final XYPosition position;
  final Boolean isAlive;

  Cell(XYPosition position, Boolean isAlive) {
    this.position = position;
    this.isAlive = isAlive;
  }

  /**
   * @return the same Cell but Dead or Alive depending on the rules
   */
  Cell applyRules(Stream<Cell> neighbours) {
    long aliveNeighbours = neighbours.filter((c) -> c.isAlive).count();

    if (isAlive)
      if (aliveNeighbours == 2 || aliveNeighbours == 3) return this; //stay alive
      else return flip(); //die
    else if (aliveNeighbours == 3) return flip(); // become alive
    else return this; // stay dead
  }

  private Cell flip() {
    return new Cell(position, !isAlive);
  }
}
