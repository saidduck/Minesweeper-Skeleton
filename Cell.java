public abstract class Cell {
    // Abstract section
    public abstract String toString(); // Use ▲ for flagged cells
    abstract boolean tap();

    // Concrete section
    protected boolean tapped = false;
    protected boolean isFlagged = false;

    boolean hasBeenTapped() {
        // TODO
    }
    void flag() {
        // TODO
    }

    void unflag() {
        // TODO
    }

    int numNeighborMines() {
      return 0;
    }

    void incrementNumNeighbors() {
    }
}
