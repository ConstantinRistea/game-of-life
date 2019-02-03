package co.uk.gol;

import java.util.List;

/**
 * Represents a cell, it's position in space and whether it's alive
 */
class Cell {

  Cell(XYPosition position, Boolean live) {
    this.position = position;
    this.live = live;
  }

  private XYPosition position;
  private Boolean live;

  boolean isAlive() {
    return live;
  }

  XYPosition getPosition() {
    return position;
  }

  /**
   * @return the same Cell but Dead or Alive depending on the rules
   */
  Cell applyRules(List<Cell> neighbours) {
    int aliveNeighbours = 0;
    for (Cell c : neighbours) {
      if (c.isAlive()) aliveNeighbours++;
    }
    Cell dead = new Cell(position, false);
    Cell alive = new Cell(position, true);

    // if current cell is dead and has exactly 3 live neighbours it becomes alive
    if (!this.isAlive() && aliveNeighbours == 3) return alive;
    else if (this.isAlive() && aliveNeighbours < 2) return dead;
    else if (this.isAlive() && (aliveNeighbours == 2 || aliveNeighbours == 3)) return alive;
    else if (this.isAlive() && aliveNeighbours > 3) return dead;
    else return dead;
  }
}
