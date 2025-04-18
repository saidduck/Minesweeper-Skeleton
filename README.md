# Minesweeper

This is a simple implementation of the Minesweeper game in Java.

## How to Play

1.  Clone the repository.
2.  Compile the Java files.
    ```bash
    javac Main.java Board.java Cell.java EmptyCell.java MineCell.java NumberCell.java
    ```
3.  Run the `Main` class.
    ```bash
    java Main
    ```
4.  The game will start, displaying the board.
5.  Enter commands in the format `<action> <row> <column>`.
    *   `<action>` can be `FLAG`, `UNFLAG`, or `TAP`.
    *   `<row>` and `<column>` should be letters from A to J.

    For example, to tap the cell at row A and column B, enter:
    ```
    TAP A B
    ```

## Implemented Features

*   **Board Generation:** Generates a 10x10 Minesweeper board with 10 mines.
*   **Basic Actions:** Supports `FLAG`, `UNFLAG`, and `TAP` actions.
*   **Game Logic:** Detects game over when a mine is tapped and congratulates the user upon solving the board.
*   **Neighboring Empty Cell reveal:** Reveals all neighboring empty cells upon tapping an empty cell.

## Project Structure

*   `Main.java`: Contains the main method and game loop.
*   `Board.java`: Represents the Minesweeper board and its logic.
*   `Cell.java`: Abstract class for a cell on the board.
*   `EmptyCell.java`: Represents an empty cell.
*   `MineCell.java`: Represents a cell containing a mine.
*   `NumberCell.java`: Represents a cell containing a number indicating neighboring mines.

## Dependencies

*   [JUnit](https://junit.org/junit4/)
*   [json-simple](https://github.com/fangyidong/json-simple)
*   [hamcrest-core](https://mvnrepository.com/artifact/org.hamcrest/hamcrest-core)

These dependencies are managed by Maven, as specified in the [pom.xml](pom.xml) file.

## TODO

*   Implement more robust error handling.
*   Add difficulty selection.
*   Improve the user interface.