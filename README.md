# SeaBattle-game

An application to simulate a game of sea battle.

- Name: Admin
- Password: myDream1@$$

# Basic rules of the game:
1. The game board consists of a 16x16 matrix.
2. Columns are signed with letters of the Latin alphabet A-P.
3. Rows are signed with numbers 1-16.
4. Ships are of 6 types: 6 cells - 1 piece, 5 cells - 2 pieces, 4 cells - 3 pieces, 3 cells - 4 pieces, 2 cells - 5 pieces, 1 cell - 6 pieces.
5. Game 1 (with opponent-bot algorithm).  Ships are arranged automatically according to the rules of classical "naval combat" (ships can not cross, stand closer than 1 cell to each other horizontally, vertically, diagonally).
6. Two fields are drawn at the start: the player's field, where his "ships" are located, and the opponent's field, which is empty.
7. Each player makes his move by specifying coordinates from the matrix of the opponent's "playing field". The playing field responds with "wounded", "killed" (if the ship is hit completely), "past".
8. In case of receiving a "bye" the turn passes to the opponent, otherwise he makes another turn.
9. Each move is marked on the field, both yours and the opponent's (on the opponent's field). The mark "past" is a point, the mark "wounded" or "killed" is a cross.
10. The task is to hit all the opponent's "ships" until your own "ships" are hit.

# The following requirements for the game were taken into account during development:
- at the start the game should offer you four choices:
  1 - INFO.
  2 - Sing in."
  3 - Registration.
  4 - Play GAME.
  5 - EXIT.

- After that, if you select any of the options in the menu (except for the exit and registration option), you will be prompted to log in to the game by entering your unique name and then your password. If your name is not in the database, then the registration algorithm will be activated in which you will be offered to enter your name, age and password step by step. After successful registration it is necessary to enter the program under your name. If you try to log in and enter your password incorrectly three times, your account will be blocked for 24 hours. After the expiration of this period and a second attempt to log in, the account will be unlocked automatically.
- data are stored in a text file in JSON format

# The following requirements to the game bot (single-player game) were taken into account during development:
- each turn must be aimed at a specific action: completing an attack, searching for a ship.
- The "search for a ship" should be performed in accordance with the situation, for example, if the largest ship is not hit, but there are no places on the opponent's field where such a ship could fit - the strike should be in such an area. In the case of the very start of the game, random or known strategies can be used (Perelman's note).
- "end of attack" implies that if "wounded" is received, the ship must be finished before going to "search for ship".


# The following technology requirements are considered in the development:
- Java 8+ application
- console implementation (without graphics libraries)
- documentation (brief description of commands for the game)
- code base published on github in a public repository



