/**
 * Names: Ishan Garg, Krish Patel, Pranav Mahabal
 * Course: MCV4U0-1
 * Date: January 17, 2023
 * Teacher: Ms. Iulia Gugoiu
 */


import bsh.Interpreter;
import java.util.Random;

public class DiffQuestions {
    
    private double correctAnswer;
    private String function;
    private String displayFunction;
    private int x;
    private int y;
    private String question;
    private Random rand;
    private Interpreter solver;
    private String[] functions;
    private String[] xExps;
    private String[] displayExps;

    public DiffQuestions() {
        correctAnswer = 0.0;
        function = "";
        functions = new String[]{
            "Math.sin(x)", 
            "Math.cos(x)", 
            "Math.tan(x)", 
            "Math.log(x)", 
            "Math.log10(x)", 
            "Math.sqrt(x)", 
            "Math.cosh(x)", 
            "Math.sinh(x)", 
            "Math.tanh(x)"
        };
        xExps = new String[]{
            "Math.pow(x,2)", 
            "Math.pow(x,3)", 
            "Math.sqrt(x)", 
            "y*x"
        };
        displayExps = new String[]{
            "x^2", 
            "x^3", 
            "sqrt(x)", 
            "y*x"
        };
        x = 0;
        y = 0;
        question = "";
        rand = new Random();
        solver = new Interpreter();
    }
  
    private void generateQuestion1() {
        x = rand.nextInt(20) + 1;
        int i = rand.nextInt(6) + 0;  
        function = "";
        function = function.concat(functions[i]);
        i = rand.nextInt(6) + 0;        
        function = function.concat("+" + functions[i]);
               
        correctAnswer = differentiate(function, x);
                
        function = function.replaceAll("log10", "temp").replaceAll("log", "ln").replaceAll("temp", "log10").replaceAll("Math.", "");
                
        question = "What is the slope of the tangent of y = " + function + " at x = " + x + "?";
    }
    
    private void generateQuestion2() {
        x = rand.nextInt(20) + 1;
        int i = rand.nextInt(9) + 0;  
        function = "";
        function = function.concat(functions[i]);
        i = rand.nextInt(9) + 0;        
        function = function.concat("*" + functions[i]);
               
        correctAnswer = differentiate(function, x);
                
        function = function.replaceAll("log10", "temp").replaceAll("log", "ln").replaceAll("temp", "log10").replaceAll("Math.", "");
                
        question = "What is the slope of the tangent of y = " + function + " at x = " + x + "?";
    }

    private void generateQuestion3() {
        x = rand.nextInt(8) + 2;
        y = rand.nextInt(10) + 2;
        int i = rand.nextInt(4) + 0;
        
        function = "";
        function = function.concat("(" + xExps[i] + ")").replaceAll("y", String.valueOf(y));
        displayFunction = "";
        displayFunction = displayFunction.concat("(" + displayExps[i] + ")").replaceAll("y", String.valueOf(y));
        
        y = rand.nextInt(14) + 2;
        function = function.concat("/(x+y)").replaceAll("y", String.valueOf(y));
        displayFunction = displayFunction.concat("/(x+y)").replaceAll("y", String.valueOf(y));

        correctAnswer = differentiate(function, x);
                               
        question = "What is the slope of the tangent of y = " + displayFunction + " at x = " + x + "?";
    }

    private void generateQuestion4() {
        x = rand.nextInt(20) + 1;
        int i = rand.nextInt(5) + 0;  
        function = "";
        displayFunction = "";
        function = function.concat(functions[i]);
        displayFunction = displayFunction.concat(functions[i]);
        i = rand.nextInt(4) + 0;
        y = rand.nextInt(100) + 2;
        function = function.replaceFirst("x", xExps[i]).replaceAll("y", String.valueOf(y));
        displayFunction = displayFunction.replaceFirst("x", displayExps[i]).replaceAll("y", String.valueOf(y));
               
        correctAnswer = differentiate(function, x);
                
        displayFunction = displayFunction.replaceAll("log10", "temp").replaceAll("log", "ln").replaceAll("temp", "log10").replaceAll("Math.", "");
                
        question = "What is the normal of y = " + displayFunction + " at x = " + x + "?"
                + "\n\nNote: read the question carefully.";
    }

    private void generateQuestion5() {
        double g = Math.round((double)(rand.nextInt(200) + 0))/100.0;
        y = rand.nextInt(3) + 2;
        function = "";
        displayFunction = "";
        
        function = function.concat("Math.pow(" + xExps[3] + ",");
        displayFunction = displayFunction.concat("(" + displayExps[3] + ")^");
        function = function.replaceAll("y", String.valueOf(y));
        displayFunction = displayFunction.replaceAll("y", String.valueOf(y));
        
        y = rand.nextInt(3) + 2;
        function = function.concat(xExps[3] + ")");
        displayFunction = displayFunction.concat("(" + displayExps[3] + ")");
        function = function.replaceAll("y", String.valueOf(y));
        displayFunction = displayFunction.replaceAll("y", String.valueOf(y));
               
        correctAnswer = differentiate(function, g);
                               
        question = "What is the slope of the tangent of y = " + displayFunction + " at x = " + g + "?";
    }
    
    private double differentiate(String f, double x) {
        double ans = 0;
        double h = 0.0000000001;
        
        String exp = "((" + f.replaceAll("x", String.valueOf((double)x+h)) + ")-(" + f.replaceAll("x", String.valueOf(x)) + "))/" + h;

        try {
            solver.eval("result = " + exp);
            ans = Double.parseDouble(String.valueOf(solver.get("result")));
        } catch(Exception e) {}
        
        return ans;
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