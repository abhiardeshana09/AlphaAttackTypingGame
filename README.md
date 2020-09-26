# AlphaAttackTypingGame

This is a program that implements a typing game in Java using the Swing GUI toolkit. The objective of the game is to "catch" letters as they fall down from the top of the screen by typing in the corresponding key on the keyboard. If the player allows a letter to reach the bottom of the screen, the game ends.

![alphaattack](https://i.imgur.com/Nvz2PpB.jpg)

The game has three unique difficulties, and the user is asked to select a difficulty upon startup. The higher the difficulty, the faster the letters move down the screen. At the hardest difficulty, the letters begin moving diagonally rather than straight down.

The program uses object-oriented design and contains a total of six classes. The letters move using vector based movement, which is implemented through the Vector class. In addition, since the hardest difficulty involves letters moving in both the x and y direction, the program implements a physics engine through the PhysicsEngine class that contains algorithms to detect collisions, apply the appropriate pushback, and determine the reflection angle after the collision.

The list of possible letters to be used in the program is stored in a text file. The program reads the text file and stores each letter as an element of an array, and the program randomly selects an element from this array when a letter is generated. A try catch block ensures that if an error occurs when reading the file, a default set of values (the 26 letters of the alphabet) is used instead.

The program uses double buffering to ensure smooth motion as the letters move. The Game class uses a KeyListener to obtain user input.

If the user allows a letter to reach the bottom of the screen, the game ends and the program displays the user's score. The user is asked if they would like to play again. If not, the program ends.
