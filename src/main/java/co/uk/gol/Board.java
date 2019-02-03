package co.uk.gol;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class that represents the board and allows us to emulate the state machine, evolving between the states
 */
class Board {

  private Vector<Vector<Cell>> cells;

  /**
   * creates an empty board
   */
  Board() {
    this(new Vector<>());
  }

  /**
   * @param cells the live cells the board starts with
   */
  Board(Vector<Vector<Cell>> cells) {
    this.cells = cells;
  }

  /**
   * @return a new Board with evolutions applied
   */
  Board evolve() {
    // if the board is completely empty or all cells are dead don't do anything
    if (!isLive()) return this;
    else {
      List<List<Cell>> newCellsList =
        cells.stream().map(
          (row) -> row.stream().map(
            (cell) -> {
               List<Cell> neighbours =
                cell.getPosition().getNeighbours().parallelStream().map(this::getCellAt).collect(Collectors.toList());

               return cell.applyRules(neighbours);
            }
          ).collect(Collectors.toList())
        ).collect(Collectors.toList());

      Vector<Vector<Cell>> newCellVector = new Vector<>(newCellsList.size());
      for(List<Cell> ncl : newCellsList) {
        Vector<Cell> ncv = new Vector<>(ncl.size());
        ncv.addAll(ncl);
        newCellVector.add(ncv);
      }

      return new Board(newCellVector);
    }
  }

  /**
   * @return true if it contains cells that are live <br>
   * false otherwise
   */
  Boolean isLive() {
    if (cells.isEmpty()) return false;
    else {
      for (Vector<Cell> row : cells) {
        if (!row.isEmpty()) {
          for (Cell cell : row) {
            if (cell.isAlive()) return true;
          }
        }
      }
      return false;
    }
  }

  /**
   * @return the cell at the XY position, otherwise return a new dead cell
   */
  Cell getCellAt(XYPosition pos) {
    Vector<Cell> rows = null;
    if (pos.getX() < cells.size()) rows = cells.get(pos.getX());

    return Optional.ofNullable(rows)
            .flatMap((row) -> {
              Cell cell = null;
              if (pos.getY() < row.size()) cell = row.get(pos.getY());

              return Optional.ofNullable(cell);
            })
            .orElseGet(() -> new Cell(pos, false));
  }
}
