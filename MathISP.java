import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MathISP extends JFrame implements ActionListener {
  private JLabel questionLabel;
  private JTextField answerField;
  private JButton checkButton;
  private JLabel resultLabel;

  public MathISP() {
    super("Integral Calculator");
    setLayout(new FlowLayout());

    // Initialize the components
    questionLabel = new JLabel();
    answerField = new JTextField(10);
    checkButton = new JButton("Check");
    resultLabel = new JLabel();

    // Add the components to the frame
    add(questionLabel);
    add(answerField);
    add(checkButton);
    add(resultLabel);

    // Set up the action listener for the check button
    checkButton.addActionListener(this);

    // Generate a new question
    generateQuestion();
  }
  private void generateQuestion() {
    Random rand = new Random();

    // Generate random integral values for x and y
    int x = rand.nextInt(10) + 1;
    int y = rand.nextInt(10) + 1;

    // Generate a random operator
    String[] operators = {"+", "-", "*", "/"};
    String operator = operators[rand.nextInt(operators.length)];

    // Set the question text
    questionLabel.setText(String.format("What is %d %s %d?", x, operator, y));

    // Store the correct answer in the check button's client property
    int correctAnswer;
    switch (operator) {
      case "+":
        correctAnswer = x + y;
        break;
      case "-":
        correctAnswer = x - y;
        break;
      case "*":
        correctAnswer = x * y;
        break;
      case "/":
        correctAnswer = x / y;
        break;
      default:
        throw new IllegalArgumentException("Invalid operator: " + operator);
    }
    checkButton.putClientProperty("correctAnswer", correctAnswer);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Get the user's answer
    int answer = Integer.parseInt(answerField.getText());

    // Get the correct answer from the check button's client property
    int correctAnswer = (int) checkButton.getClientProperty("correctAnswer");

    if (answer == correctAnswer) {
      resultLabel.setText("Correct!");
    } else {
      resultLabel.setText("Incorrect. The correct answer is " + correctAnswer);
    }
    answerField.setText("");
    generateQuestion();
  }
  

  public static void main(String[] args) {
    MathISP frame = new MathISP();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 150);
    frame.setBackground(Color.BLUE);
    frame.setVisible(true);
    
  }
}