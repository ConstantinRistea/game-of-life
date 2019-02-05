package co.uk.gol;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class XYPositionTest {

  private XYPosition origin = new XYPosition(0, 0);
  private XYPosition one = new XYPosition(1, 1);

  @Test
  public void testNeighbours() {
    assertArrayEquals("Neighbours are not identical", origin.getNeighbours().toArray(), new XYPosition[]{
            new XYPosition(1, 0),
            one,
            new XYPosition(0, 1)
    });

    assertArrayEquals("Neighbours are not identical", one.getNeighbours().toArray(), new XYPosition[]{
            new XYPosition(1, 0),
            new XYPosition(2, 1),
            origin,
            new XYPosition(2, 2),
            new XYPosition(0, 1),
            new XYPosition(1, 2),
            new XYPosition(0, 2),
            new XYPosition(2, 0)
    });
  }
}
