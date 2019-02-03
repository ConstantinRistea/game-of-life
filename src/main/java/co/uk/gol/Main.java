package co.uk.gol;

import java.util.Vector;

class Main {

    /**
     * Example input: [1, 0] [0, 0]
     */
    public static void main(String[] args) {
        Board board = new Board(parseCells(args));
        do {
            //TODO: print board in a nice way
            System.out.println(board);
            board.evolve();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        } while (board.isLive());
    }

    static Vector<Vector<Cell>> parseCells(String[] args) {
        int rowLen = args.length;
        Vector<Vector<Cell>> rows = new Vector<>(rowLen);
        for (int x = 0; x < rowLen; x++) {
            String[] arg = args[x].replaceAll("\\[|\\]", "").split(",");
            int colLen = arg.length;
            Vector<Cell> columns = new Vector<>(colLen);
            for (int y = 0; y < colLen; y++) {
                boolean isAlive = Integer.parseInt(arg[y]) != 0;
                columns.add(new Cell(new XYPosition(x, y), isAlive));
            }
            rows.add(columns);
            return rows;

        }
    }
