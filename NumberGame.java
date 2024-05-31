import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGame extends JFrame {
    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JLabel attemptsLabel;
    private int generatedNumber;
    private int attempts;
    private int maxAttempts = 5;

    public NumberGame() {
        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        generateRandomNumber();

        JLabel titleLabel = new JLabel("Guess the Number (1-100)");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout());
        guessField = new JTextField(10);
        centerPanel.add(guessField);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessListener());
        centerPanel.add(guessButton);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        resultLabel = new JLabel("");
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        bottomPanel.add(resultLabel);

        attemptsLabel = new JLabel("Remaining attempts: " + (maxAttempts - attempts));
        attemptsLabel.setHorizontalAlignment(JLabel.CENTER);
        bottomPanel.add(attemptsLabel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void generateRandomNumber() {
        Random rand = new Random();
        generatedNumber = rand.nextInt(100) + 1;
        attempts = 0;
    }

    private class GuessListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if (guess == generatedNumber) {
                resultLabel.setText("Congratulations! You guessed it right.");
                attemptsLabel.setText("Remaining attempts: " + (maxAttempts - attempts));
                guessButton.setEnabled(false);
            } else if (guess < generatedNumber) {
                resultLabel.setText("Too low! Try again.");
                attemptsLabel.setText("Remaining attempts: " + (maxAttempts - attempts));
            } else {
                resultLabel.setText("Too high! Try again.");
                attemptsLabel.setText("Remaining attempts: " + (maxAttempts - attempts));
            }

            if (attempts >= maxAttempts) {
                resultLabel.setText("Sorry, you've used all your attempts. The number was: " + generatedNumber);
                guessButton.setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                NumberGame game = new NumberGame();
                game.setVisible(true);
            }
        });
    }
}
