
import java.util.Random;

public class LHopitalQuestions {
    
    private int correctAnswer;
    private String operator;
    private int x;
    private int y;
    private String question;
    private Random rand;

    public LHopitalQuestions() {
      correctAnswer = 0;
      operator = "";
      x = 0;
      y = 0;
      question = "";
      rand = new Random();
    }
  
    private void generateQuestion1() {
        x = rand.nextInt(20) + 10;
        y = rand.nextInt(9) + 1;

        String[] operators = {"+", "-", "*", "/"};
        operator = operators[rand.nextInt(operators.length)];

        switch (operator) {
            case "+" -> correctAnswer = x + y;
            case "-" -> correctAnswer = x - y;
            case "*" -> correctAnswer = x * y;
            case "/" -> correctAnswer = x / y;
        }
        
        question = "What is " + x + " " + operator + " " + y + "?";
    }
    
    private void generateQuestion2() {
        x = rand.nextInt(20) + 10;
        y = rand.nextInt(9) + 1;

        String[] operators = {"+", "-", "*", "/"};
        operator = operators[rand.nextInt(operators.length)];

        switch (operator) {
            case "+" -> correctAnswer = x + y;
            case "-" -> correctAnswer = x - y;
            case "*" -> correctAnswer = x * y;
            case "/" -> correctAnswer = x / y;
        }
        
        question = "What is " + x + " " + operator + " " + y + "?";
    }

    private void generateQuestion3() {
        x = rand.nextInt(20) + 10;
        y = rand.nextInt(9) + 1;

        String[] operators = {"+", "-", "*", "/"};
        operator = operators[rand.nextInt(operators.length)];

        switch (operator) {
            case "+" -> correctAnswer = x + y;
            case "-" -> correctAnswer = x - y;
            case "*" -> correctAnswer = x * y;
            case "/" -> correctAnswer = x / y;
        }
        
        question = "What is " + x + " " + operator + " " + y + "?";
    }

    private void generateQuestion4() {
        x = rand.nextInt(20) + 10;
        y = rand.nextInt(9) + 1;

        String[] operators = {"+", "-", "*", "/"};
        operator = operators[rand.nextInt(operators.length)];

        switch (operator) {
            case "+" -> correctAnswer = x + y;
            case "-" -> correctAnswer = x - y;
            case "*" -> correctAnswer = x * y;
            case "/" -> correctAnswer = x / y;
        }
        
        question = "What is " + x + " " + operator + " " + y + "?";
    }

    private void generateQuestion5() {
        x = rand.nextInt(20) + 10;
        y = rand.nextInt(9) + 1;

        String[] operators = {"+", "-", "*", "/"};
        operator = operators[rand.nextInt(operators.length)];

        switch (operator) {
            case "+" -> correctAnswer = x + y;
            case "-" -> correctAnswer = x - y;
            case "*" -> correctAnswer = x * y;
            case "/" -> correctAnswer = x / y;
        }
        
        question = "What is " + x + " " + operator + " " + y + "?";
    }
   
    public int getCorrectAns() {
        return correctAnswer;
    }
    
    public String getQuestion(int num) {
        switch(num) {
            case 100 -> generateQuestion1();
            case 200 -> generateQuestion2();
            case 300 -> generateQuestion3();
            case 400 -> generateQuestion4();
            case 500 -> generateQuestion5();
        }
        
        return question;
    }
}