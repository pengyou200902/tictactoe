Name: You Peng
Email: youpeng@bu.edu
BU ID: U82384393

Execution:

    javac Main.java && java Main
    OR
    Run the main function in Main.java in the IDE such as IntelliJ IDEA.

Class Descriptions:

    interface BoardInterface: Set the common functions for a game board.

    interface GameInterface: Set the common logics for a game.

    interface PlayerInterface: Set the abilities for players such as move().

    interface Winnable: A winnable Game should have a checkWin interface.

    class BaseBoard: Store/Share the limits for board size.

    class BaseGame: For future use for scalability. Currently, it's empty.

    class Board: The board of the Tic Tac Toe and Orders and Chaos, min(3x3) max(15x15).

    class GameHelper: The class for choosing a game and launching a game, etc.

    class Main: The main entrance.

    class Mark: Stores info about a mark/piece.

    class OACPlayer: Player for Orders and Chaos. Different from TTTPlayer when creating.It implements PlayerInterface.

    class OrdersAndChaos: Stores the game logic and implements GameInterface.

    class TicTacToe: The game class which contains some logics and rules of the game such as checkWin.

    class TTTPlayer: Player for Tic Tac Toe.

Extra Info:

    0. Use a Scalable interface to indicate a board can have different sizes. And use a Winnable interface to indicate
    that a game has a winner so we must implement checkWin().

    1. In class TTTPlayer and OACPlayer, I implemented a private constructor. To instantiate XXXPlayer[] objects
    for both games, we should call a static function named createPlayers(). This was designed to avoid creating a wrong
    number of players by mistake.

    2. The XXXPlayer can set their names(empty and same names are not allowed). They can try to input again if they
    enter invalid names at the beginning.

    3. If XXXPlayer set their pieces onto invalid positions, the program will ask them to try again.

    4. When one game ends, users can choose whether to start a new game.