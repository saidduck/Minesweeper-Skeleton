public class NumberCell extends Cell {
    private int numNeighborMines;

    public NumberCell() {
        numNeighborMines = 1;
    }
    
    public String toString(){
        if (tapped) {
          return String.valueOf(this.numNeighborMines);
        }else if (isFlagged){
          return "▲";
        }else{
          return "x";
        }
      };
      boolean tap(){
        this.tapped = true;
        return true;
      };

    int numNeighborMines(){
        return numNeighborMines;
    }
    // TODO override abstract methods and numNeighborMines

    @Override
    void incrementNumNeighbors() {
        this.numNeighborMines++;
    }
}
