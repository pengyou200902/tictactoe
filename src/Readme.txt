Name: You Peng
Email: youpeng@bu.edu
BU ID: U82384393

Execution:
    javac Main.java && java Main
    OR
    Run the main function in Main.java in the IDE such as IntelliJ IDEA.

Class Descriptions:
    class Board: The board of the Tic Tac Toe game with fixed size 3x3 (classical Tic Tac Toe).

    class Player: The player class which has a name and a chess type.

    class TicTacToe: The game class which contains some logics and rules of the game such as checkWin.

    class Main: The class that can run a Tic Tac Toe game.

Extra Info:
    1. In class Player, I implemented a private constructor. To instantiate player objects for Tic Tac Toe game,
    we should call a static function named createPlayers(). This was designed to avoid creating a wrong number of
    players by mistake.

    2. The players can set their names(empty and same names are not allowed). They can try to input again if they
    enter invalid names at the beginning.

    3. If players set their pieces onto invalid positions, the program will ask them to try again.

    4. When one game ends, users can choose whether to start a new game.