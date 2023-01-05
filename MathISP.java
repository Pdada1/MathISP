import java.awt.*;
import javax.swing.*

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