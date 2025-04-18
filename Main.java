import java.util.Map;
import java.util.Scanner;

public class Main {
    enum Action {
        // TODO
        FLAG,
        UNFLAG,
        TAP
    }

    public static void main(String[] args) {
        Board b = new Board();
        System.out.println(b);

        while (true) {
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            if (userInput.isEmpty()) {
                continue;
            }

            String[] cmds = userInput.split(" ");
            if (cmds.length != 3) {
                System.out.println("Input should be of the format `<action> <row> <coordinate>` where <action> is one of " +
                        "`FLAG`, `UNFLAG`, or `TAP`");
                continue;
            }

          // DON'T WORRY THAT THIS SECTION OF THE SKELETON CODE DOES NOT MATCH IN WALKTHROUGH #2.
          // I made some changes to the skeleton after that video was recorded
          Integer row = Board.LETTER_TO_NUM_MAP.get(cmds[1]);              // TODO

          if (row == null) {
            System.out.println("Row should be between letters A through J but was " + cmds[1]);
            continue;
          }

          Integer column = Board.LETTER_TO_NUM_MAP.get(cmds[2]);              // TODO

          if (column == null) {
            System.out.println("Column should be between letters A through J but was " + cmds[2]);
            continue;
          }

          boolean isSafeCell = true;
          switch (Action.valueOf(cmds[0])) {
            // TODO
                  case TAP:
                    isSafeCell = b.tap(row, column);
                  break;
                  case FLAG:
                    b.flag(row, column);
                  break;
                  case UNFLAG:
                    b.unflag(row, column);
                  break;
                  
          }    

          System.out.println(b);

          if (!isSafeCell) {
              System.out.println("GAME OVER!");
              break;
          }

          if (b.isSolved()) {
              System.out.println("Congratulations! The board has been solved!");
              break;
          }
        }
    }
}
