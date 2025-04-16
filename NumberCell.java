public class NumberCell extends Cell {
    private int numNeighborMines;

    public NumberCell() {
        numNeighborMines = 1;
    }

    // TODO override abstract methods and numNeighborMines

    @Override
    void incrementNumNeighbors() {
        this.numNeighborMines++;
    }
}
