import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
    private Cell[][] cells;
    private static int HEIGHT = 10;
    private static int WIDTH = 10;
    private static int NUM_MINES = 10;
    public static Map<String, Integer> LETTER_TO_NUM_MAP = Map.of(
      // TODO
        "A", 0,
        "B", 1,
        "C", 2,
        "D", 3,
        "E", 4,
        "F", 5,
        "G", 6,
        "H", 7,
        "I", 8,
        "J", 9
    );

    public Board() {
        this.cells = new Cell[HEIGHT][WIDTH];
        plantMinesAndNumbers();
        plantEmptyCells();
    }

    public boolean isSolved() {
        int numTapped = 0;
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                if (cells[row][column].hasBeenTapped()) {
                    numTapped++;
                }
            }
        }
        return numTapped == HEIGHT * WIDTH - NUM_MINES;
    }

    public void flag(int row, int column) {
        cells[row][column].flag();
    }

    public void unflag(int row, int column) {
        cells[row][column].unflag();
    }

    private void plantMinesAndNumbers() {
        List<Integer> rawCoordinates = new ArrayList<>();
        for (int counter = 0; counter < HEIGHT * WIDTH; counter++) {
            rawCoordinates.add(counter);
        }
        Collections.shuffle(rawCoordinates);

        for (int mineIdx = 0; mineIdx < NUM_MINES; mineIdx++) {
            int rawCoordinate = rawCoordinates.get(mineIdx);
            int row = rawCoordinate / 10;
            int column = rawCoordinate % 10;
            cells[row][column] = new MineCell();
            plantNumbers(row, column);
        }
    }

    private void plantNumbers(int i, int j) {
        for (int row = i - 1; row <= i + 1; row++) {
            for (int col = j - 1; col <= j + 1; col++) {
                if (isWithinBounds(row, col)) {
                    if (cells[row][col] == null) {
                        cells[row][col] = new NumberCell();
                    } else {
                        cells[row][col].incrementNumNeighbors();
                    }
                }
            }
        }
    }

    private void plantEmptyCells() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                if (cells[row][column] == null) {
                    cells[row][column] = new EmptyCell();
                }
            }
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("  | A | B | C | D | E | F | G | H | I | J | \n");
        s.append("----------------------------------------------\n");
        String[] num_to_letter_map = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (int i = 0; i < HEIGHT; i++) {
            s.append(num_to_letter_map[i]);
            s.append(" | ");
            for (int j = 0; j < WIDTH; j++) {
                s.append(cells[i][j].toString());
                s.append(" | ");
            }
            s.append(num_to_letter_map[i]);
            s.append("\n----------------------------------------------\n");
        }
        s.append("  | A | B | C | D | E | F | G | H | I | J | \n");
        return s.toString();
    }

    public boolean tap(int row, int column) {
        boolean tapResult = cells[row][column].tap();
        if (tapResult && cells[row][column].numNeighborMines() == 0) {
            List<Cell> neighboringEmptyCells = gatherNeighboringEmptyCells(row, column);
            for (Cell cell : neighboringEmptyCells) {
                cell.tap();
            }
            return true;
        } else if (!tapResult) {
            return false;
        }
        return true;
    }

    private List<Cell> gatherNeighboringEmptyCells(int row, int column) {
        List<Cell> finalResult = new ArrayList<>();
        // TODO
        HashSet<Cell> seenCells = new HashSet<>();
        Queue<String> q = new LinkedBlockingQueue<>();
        q.add(row + "," + column);
        while (!q.isEmpty()) {
            String[] coordinates = q.poll().split(",");
            row = Integer.parseInt(coordinates[0]);
            column = Integer.parseInt(coordinates[1]);
            Cell popped = cells[row][column];
            // TODO
            if (!seenCells.contains(popped)) {
                    seenCells.add(popped);
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = column - 1; j <= column + 1; j++) {
                        if (isWithinBounds(i, j)) {
                            if (cells[i][j].numNeighborMines() == 0) {
                                q.add(i + "," + j);
                            } else {
                                finalResult.add(cells[i][j]);
                            }
                        }
                    }
                }
            }
            
        }
        return Stream.concat(finalResult.stream() , new ArrayList<>(seenCells).stream()).collect(Collectors.toList());
    }

    private boolean isWithinBounds(int row, int column) {
        return row >= 0 && row < 10 && column >= 0 && column < 10;
    }
}
