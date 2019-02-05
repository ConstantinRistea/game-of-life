package co.uk.gol;

import java.util.Set;
import java.util.stream.Stream;

import static java.lang.Integer.max;
import static java.util.stream.Collectors.toSet;

final class XYPosition {
  // because these are final, we don't need getters
  final int x;
  final int y;

  /**
   * We're enforcing only positive x, y values as these represent indices in our Vectors
   *
   * @param x the row
   * @param y the column
   */
  XYPosition(int x, int y) {
    this.x = max(x, 0);
    this.y = max(y, 0);
  }

  /**
   * @return all your neighbours, for edge cases, e.g. (0,0), exclude yourself
   */
  Set<XYPosition> getNeighbours() {
    return Stream.of(
            new XYPosition(x - 1, y - 1), //upper left
            new XYPosition(x, y - 1), //upper
            new XYPosition(x + 1, y - 1), // upper right
            new XYPosition(x - 1, y), // middle left
            new XYPosition(x + 1, y), // middle right
            new XYPosition(x - 1, y + 1), //lower left
            new XYPosition(x, y + 1), //lower
            new XYPosition(x + 1, y + 1) //lower right
    ).filter((xy) -> xy.x != this.x || xy.y != this.y).collect(toSet());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    XYPosition that = (XYPosition) o;
    return x == that.x && y == that.y;
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(x, y);
  }
}
