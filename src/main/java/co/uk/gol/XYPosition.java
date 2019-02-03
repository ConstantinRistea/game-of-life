package co.uk.gol;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.max;

class XYPosition {

  private int x;

  private int y;

  /**
   * We're enforcing only positive x, y values as these represent indices in our Vectors
   * @param x the row
   * @param y the column
   */
  XYPosition(int x, int y) {
    this.x = max(x, 0);
    this.y = max(y, 0);
  }

  Set<XYPosition> getNeighbours() {
    Set<XYPosition> allPossibleNeighbors = new HashSet<>(Arrays.asList(
            new XYPosition(x - 1, y - 1), //upper left
            new XYPosition(x, y - 1), //upper
            new XYPosition(x + 1, y - 1), // upper right
            new XYPosition(x - 1, y), // middle left
            new XYPosition(x + 1, y), // middle right
            new XYPosition(x - 1, y + 1), //lower left
            new XYPosition(x, y + 1), //lower
            new XYPosition(x + 1, y + 1) //lower right
    ));

    // you can't be your own neighbour
    allPossibleNeighbors.removeIf((xy) -> xy.x == this.x && xy.y == this.y);
    return allPossibleNeighbors;
  }

  int getX() {
    return x;
  }

  void setX(int x) {
    this.x = x;
  }

  int getY() {
    return y;
  }

  void setY(int y) {
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    XYPosition that = (XYPosition) o;
    return x == that.x &&
            y == that.y;
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(x, y);
  }
}
