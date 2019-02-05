package co.uk.gol;

import java.util.Optional;
import java.util.Vector;

import static java.util.stream.Collectors.toCollection;

/**
 * Class that represents the board and allows us to emulate the state machine, evolving between the states
 */
final class Board {
  // because cells are final, we don't need a getter
  final Vector<Vector<Cell>> cells;

  /**
   * @param cells the isAlive cells the board starts with
   */
  Board(Vector<Vector<Cell>> cells) {
    this.cells = cells;
  }

  /**
   * @return a new Board with evolutions applied
   */
  Board evolve() {
    // if the board is completely empty or all cells are dead don't do anything
    if (!isAlive()) return this;
    else return new Board(
            cells.parallelStream().map((row) ->
                    row.parallelStream().map((cell) ->
                            cell.applyRules(
                                    cell.position.getNeighbours().parallelStream().map(this::getCellAt)
                            )
                    ).collect(toCollection(Vector::new))
            ).collect(toCollection(Vector::new))
    );
  }

  /**
   * @return true if it contains at least a cell that is alive <br>
   * false otherwise
   */
  Boolean isAlive() {
    for (Vector<Cell> row : cells)
      for (Cell cell : row)
        if (cell.isAlive) return true;
    return false;
  }

  /**
   * @return the cell at the XY position, otherwise return a new dead cell
   */
  private Cell getCellAt(XYPosition pos) {
    Vector<Cell> rows = null;
    if (pos.x < cells.size()) rows = cells.get(pos.x);

    return Optional.ofNullable(rows)
            .flatMap((row) -> {
              Cell cell = null;
              if (pos.y < row.size()) cell = row.get(pos.y);

              return Optional.ofNullable(cell);
            })
            .orElseGet(() -> new Cell(pos, false));
  }
}
