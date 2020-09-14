import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * Alpha Attack Typing Game
 * Author: Abhi Ardeshana
 * Description: This is the program's central class that controls the game window, game rules and business logic
 */

class Game extends JFrame implements Runnable, KeyListener {

    // Array of strings to store the list of possible characters to be used in the game
    private String[] gmCharacterList;
    // Array of LetterSprite objects that will be used in the game
    private LetterSprite[] gmLetters;
    // Random number generator to be used when selecting a new character
    private Random gmRandom = new Random();
    // Attributes for the game's difficulty, frame rate, and score
    private int gmDifficulty;
    private int gmFrameRate = 60;
    private int gmScore = 0;

    // Constructor
    Game() {
        // Sets the JFrame's title, close operation and size
        setTitle("Alpha Attack");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 800);
        // Invokes methods to read the file, get the user's desired difficulty, and initialize the letter objects
        readFile();
        getUserDifficulty();
        initializeLetters();
        // Adds the key listener, makes the JFrame visible, and starts the game loop
        addKeyListener(this);
        setVisible(true);
        new Thread(this).start();
    }

    // This method reads the text file containing the list of possible characters to be used in the game
    private void readFile() {
        try {
            // Creates a scanner, array list, and loads the file's lines into the array list
            Scanner fileInput = new Scanner(new File("CharacterList.txt"));
            java.util.List<String> fileLines = new ArrayList<>();
            while (fileInput.hasNextLine()) {
                fileLines.add(fileInput.nextLine());
            }
            // Sets the game's character list to the array list
            gmCharacterList = fileLines.toArray(new String[0]);
        }
        catch (Exception e) {
            // If an error occurs while reading the file, this set of default values is used in the program instead
            gmCharacterList = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                    "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        }
    }

    // This method asks the user for their desired difficulty level
    private void getUserDifficulty() {
        // Creates a list of possible options for the game's difficulty
        Object[] options = {"Easy", "Normal", "Hard"};
        // Creates an option dialog prompting the user for their desired difficulty
        gmDifficulty = JOptionPane.showOptionDialog(this,
                "Welcome to Alpha Attack! Please select a difficulty.",
                "Welcome!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        // Ends the program if the user closed the dialog box without choosing a difficulty
        if (gmDifficulty == -1) {
            System.exit(0);
        }
    }

    // This method initializes the LetterSprite objects in the gmLetters array
    private void initializeLetters() {
        // Creates the gmLetters array based on the difficulty chosen by the user
        // Creates 8 objects for the Easy difficulty, 10 for the Normal difficulty, and 12 for the Hard difficulty
        gmLetters = new LetterSprite[gmDifficulty * 2 + 8];
        // Instantiates all of the LetterSprite objects
        for (int i = 0; i < gmLetters.length; i++) {
            gmLetters[i] = new LetterSprite(getInsets());
            // Chooses a random position, character, speed and direction for the letter based on the difficulty
            gmLetters[i].positionLetter(getWidth(), getHeight(), gmDifficulty,
                    gmCharacterList[gmRandom.nextInt(gmCharacterList.length)]);
        }
    }

    // This method maintains the game loop
    public void run() {
        while (true) {
            // Moves each LetterSprite, checks for and handles collisions, and checks if the game has ended
            for (int i = 0; i < gmLetters.length; i++) {
                gmLetters[i].moveLetter();
                gmLetters[i].checkLetterCollision(getWidth());
                checkGameOver(gmLetters[i].getLetterY());
            }
            // Repaints the JFrame
            repaint();
            // Sleeps the thread based on the game's frame rate
            try {
                Thread.sleep(1000 / gmFrameRate);
            }
            catch (InterruptedException e) {}
        }
    }

    // This method draws the score and letters onto the screen
    public void paint(Graphics g) {
        // Creates a back buffer and obtains the back buffer's graphics object
        Image backBuffer = createImage(getWidth(), getHeight());
        Graphics backBufferGraphics = backBuffer.getGraphics();
        // Sets the back buffer's font
        backBufferGraphics.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        // Sets the back buffer's color to white and clears the back buffer
        backBufferGraphics.setColor(Color.WHITE);
        backBufferGraphics.fillRect(0, 0, getWidth(), getHeight());
        // Sets the back buffer's color to black and draws the score onto the back buffer
        backBufferGraphics.setColor(Color.BLACK);
        backBufferGraphics.drawString("Score: " + gmScore, 10, 60);
        // Draws all of the letters onto the back buffer
        for (int i = 0; i < gmLetters.length; i++) {
            gmLetters[i].drawLetter(backBufferGraphics);
        }
        // Displays the back buffer on the JFrame using the JFrame's graphics object
        g.drawImage(backBuffer, 0, 0, this);
    }

    // This method runs whenever the user types a key
    public void keyTyped(KeyEvent e) {
        for (int i = 0; i < gmLetters.length; i++) {
            // This "if" statement runs if the key pressed by the user matches a letter currently visible on the screen
            if (gmLetters[i].getLetterCharacter().equalsIgnoreCase(Character.toString(e.getKeyChar())) &&
                    gmLetters[i].getLetterY() > getInsets().top) {
                // Resets the captured letter by choosing a new position, character, direction and speed
                gmLetters[i].positionLetter(getWidth(), getHeight(), gmDifficulty,
                        gmCharacterList[gmRandom.nextInt(gmCharacterList.length)]);
                // Increments the score if the user successfully caught a letter
                gmScore++;
            }
        }
    }

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    // This method is called to check if the game is over and handles the user's input if it is
    private void checkGameOver(double y) {
        // Checks if the game is over by checking if the letter has reached the bottom of the screen
        if (y > getHeight() - getInsets().bottom) {
            // If the game ends, a confirm dialog is created asking the user if they would like to play again
            int userChoice = JOptionPane.showConfirmDialog(this,
                    "Ouch! Your final score was " + gmScore + ". Play again?",
                    "You Lost!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            // If the "Yes" option is selected, the game restarts by resetting the letters and the score
            if (userChoice == 0) {
                initializeLetters();
                gmScore = 0;
            }
            // Exits the program if the "No" option is selected or if the dialog is closed
            else {
                System.exit(0);
            }
        }
    }
}