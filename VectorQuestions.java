
import java.util.Random;

public class VectorQuestions {
    
    private int correctAnswer;
    private String operator;
    private int x;
    private int y;

    public VectorQuestions() {
      correctAnswer = 0;
      operator = "";
      x = 0;
      y = 0;
    }
  
    private void generateQuestion() {
        Random rand = new Random();

        // Generate random integral values for x and y
        x = rand.nextInt(20) + 10;
        y = rand.nextInt(9) + 1;

        // Generate a random operator
        String[] operators = {"+", "-", "*", "/"};
        operator = operators[rand.nextInt(operators.length)];

        // Store the correct answer in the check button's client property
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
    }
   
    public int getVectCorrectAns() {
        return correctAnswer;
    }
    
    public String getQuestion() {
        generateQuestion();
        return "What is " + x + " " + operator + " " + y + "?";
    }
}