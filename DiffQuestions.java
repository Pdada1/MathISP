
import bsh.Interpreter;
import java.util.Random;
import javax.script.*;

public class DiffQuestions {
    
    private double correctAnswer;
    private String expression;
    private String function;
    private int x;
    private int y;
    private String question;
    private Random rand;
    private Interpreter solver;
    private String[] functions;

    public DiffQuestions() {
        correctAnswer = 0.0;
        expression = "";
        function = "";
        functions = new String[]{"Math.sin(x)", "Math.cos(x)", "Math.tan(x)", "Math.log(x)", "Math.log10(x)"};
        x = 0;
        y = 0;
        question = "";
        rand = new Random();
        solver = new Interpreter();
    }
  
    private void generateQuestion1() {
        x = rand.nextInt(20) + 1;
        double h = 0.00001;
        int i = rand.nextInt(5);  
        function = "";
        function = function.concat(functions[i]);
        i = rand.nextInt(5);        
        function = function.concat("*" + functions[i]);
               
        expression = "(" + function.replaceAll("x", String.valueOf(x+h)) + "-" + function.replaceAll("x", String.valueOf(x)) + ")/" + h;

        try {
            solver.eval("result = " + expression);
            correctAnswer = Double.parseDouble(String.valueOf(solver.get("result")));
        } catch(Exception e) {}
                
        function = function.replaceAll("log10", "temp").replaceAll("log", "ln").replaceAll("temp", "log10").replaceAll("Math.", "");
        
        question = "What is the slope of the tangent of y = " + function + " at x = " + x + "?";
    }
    
    private void generateQuestion2() {
        
    }

    private void generateQuestion3() {
        
    }

    private void generateQuestion4() {
        
    }

    private void generateQuestion5() {
        
    }
   
    public double getCorrectAns() {
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