public class MineCell extends Cell {
  // TODO override abstract methods
  public String toString(){
    if (tapped) {
      return "*";
    }else if (isFlagged){
      return "▲";
    }else{
      return "x";
    }
  };
  boolean tap(){
    this.tapped = true;
    return false;
  };
}
