package co.uk.gol;

import java.util.Vector;

final class Main {
  private static final String[] pulsar = new String[]{
          "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]",
          "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]",
          "[0,0,0,0,1,1,1,0,0,0,1,1,1,0,0,0,0]",
          "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]",
          "[0,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0,0]",
          "[0,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0,0]",
          "[0,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0,0]",
          "[0,0,0,0,1,1,1,0,0,0,1,1,1,0,0,0,0]",
          "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]",
          "[0,0,0,0,1,1,1,0,0,0,1,1,1,0,0,0,0]",
          "[0,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0,0]",
          "[0,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0,0]",
          "[0,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0,0]",
          "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]",
          "[0,0,0,0,1,1,1,0,0,0,1,1,1,0,0,0,0]",
          "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]",
          "[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]",
  };

  public static void main(String[] args) {
    String[] pattern;
    if (args.length == 0) pattern = pulsar;
    else pattern = args;

    Board board = new Board(parseCells(pattern));
    printCells(board);
    while (board.isAlive()) {
      board = board.evolve();
      printCells(board);
      System.out.println(buildBits (board));
    }
  }

  static Vector<Vector<Cell>> parseCells(String args) {
    return parseCells(args.split("\\s"));
  }

  private static Vector<Vector<Cell>> parseCells(String[] args) {
    int rowLen = args.length;
    Vector<Vector<Cell>> rows = new Vector<>(rowLen, 1);
    for (int x = 0; x < rowLen; x++) {
      String[] arg = args[x].replaceAll("\\[|\\]", "").trim().split(",");
      int colLen = arg.length;
      Vector<Cell> columns = new Vector<>(colLen, 1);
      for (int y = 0; y < colLen; y++) {
        String s = arg[y];
        if (!s.isBlank()) {
          boolean isAlive = "1".equals(s.trim());
          columns.add(new Cell(new XYPosition(x, y), isAlive));
        }
      }
      rows.add(columns);
    }

    return rows;
  }

  static void printCells(Board board) {
    board.cells.forEach((row) -> {
      row.forEach((cell) -> System.out.print(cell.isAlive ? '\u25A0' : '\u25A1'));
      System.out.println();
    });
  }

  static String buildBits(Board board) {
    StringBuilder sb = new StringBuilder();
    board.cells.forEach((row) -> {
      sb.append("[");
      row.forEach((cell) -> sb.append(cell.isAlive ? "1," : "0,"));
      final int len = sb.length();
      sb.delete(len-1, len);
      sb.append("] ");
    });
    return sb.toString().trim();
  }

}

