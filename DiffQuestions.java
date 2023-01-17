/**
 * Names: Ishan Garg, Krish Patel, Pranav Mahabal
 * Course: MCV4U0-1
 * Date: January 17, 2023
 * Teacher: Ms. Iulia Gugoiu
 */


import java.util.Random;

public class DiffQuestions {
    
    private double correctAnswer;
    private String displayFunction;
    private int x;
    private int y;
    private String question;
    private Random rand;
    private String[] displayFunctions;
    private String[] displayExps;
    private Function[] funcs;
    private Function[] xFuncs;
    private Function func;
    private double temp;

    public DiffQuestions() {
        correctAnswer = 0.0;
        displayFunctions = new String[]{
            "sin(x)", 
            "cos(x)", 
            "tan(x)", 
            "log(x)", 
            "log10(x)", 
            "sqrt(x)", 
            "x^(1/3)", 
            "x^3", 
            "2^x"
        };
        funcs = new Function[] {
            (x) -> Math.sin((double) x), 
            (x) -> Math.cos((double) x),
            (x) -> Math.tan((double) x), 
            (x) -> Math.log((double) x), 
            (x) -> Math.log10((double) x), 
            (x) -> Math.sqrt((double) x), 
            (x) -> Math.pow(x, 1.0/3.0), 
            (x) -> Math.pow(x, 3), 
            (x) -> Math.pow(2, x),
        };
        xFuncs = new Function[]{
            (x) -> Math.pow(x,2), 
            (x) -> Math.pow(x,3), 
            (x) -> Math.sqrt(x), 
            (x) -> y*x,
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
    }
    
    public interface Function {
        public double eval(double x);
    }
  
    private void generateQuestion1() {
        x = rand.nextInt(20) + 1;
        int i = rand.nextInt(6) + 0;  
        int j = rand.nextInt(6) + 0; 
        while(i == j) {
            j = rand.nextInt(6) + 0; 
        }
        
        displayFunction = displayFunctions[i] + "+" + displayFunctions[j];   
               
        correctAnswer = diff(funcs[i], (double)x) + diff(funcs[j], (double)x);
                
        displayFunction = displayFunction.replaceAll("log10", "temp").replaceAll("log", "ln").replaceAll("temp", "log10").replaceAll("Math.", "");
                        
        question = "What is the slope of the tangent of y = " + displayFunction + " at x = " + x + "?";
    }
    
    private void generateQuestion2() {
        x = rand.nextInt(20) + 1;
        int i = rand.nextInt(9) + 0;  
        int j = rand.nextInt(9) + 0;  
        while(i == j) {
            j = rand.nextInt(9) + 0; 
        }
        
        displayFunction = displayFunctions[i] + " * " + displayFunctions[j];
               
        correctAnswer = diff(funcs[i], (double)x)*funcs[j].eval((double)x) + diff(funcs[j], (double)x)*funcs[i].eval((double)x);
                
        displayFunction = displayFunction.replaceAll("log10", "temp").replaceAll("log", "ln").replaceAll("temp", "log10").replaceAll("Math.", "");
                
        question = "What is the slope of the tangent of y = " + displayFunction + " at x = " + x + "?";
    }

    private void generateQuestion3() {
        x = rand.nextInt(8) + 2;
        y = rand.nextInt(10) + 2;
        int z = rand.nextInt(14) + 2;
        int i = rand.nextInt(4) + 0;
        
        displayFunction = ("(" + displayExps[i] + ")/(x+z)").replaceAll("y", String.valueOf(y)).replaceAll("z", String.valueOf(z));
        
        correctAnswer = (diff(xFuncs[i], x)*(double)(x+z)-xFuncs[i].eval(x)*1.0)/Math.pow(x+z, 2);
                               
        question = "What is the slope of the tangent of y = " + displayFunction + " at x = " + x + "?";
    }

    private void generateQuestion4() {
        x = rand.nextInt(6) + 1;
        y = rand.nextInt(100) + 2;
        int i = rand.nextInt(5) + 0;  
        int j = rand.nextInt(4) + 0;
                
        displayFunction = displayFunctions[i].replaceFirst("x", displayExps[j]).replaceAll("y", String.valueOf(y));
               
        correctAnswer = -1.0/(diff(xFuncs[j], x)*diff(funcs[i], xFuncs[j].eval(x)));
                
        displayFunction = displayFunction.replaceAll("log10", "temp").replaceAll("log", "ln").replaceAll("temp", "log10").replaceAll("Math.", "");
                
        question = "What is the normal of y = " + displayFunction + " at x = " + x + "?\n\nNote: read the question carefully.";
    }

    private void generateQuestion5() {
        double g = 0.25*(double)(rand.nextInt(7) + 1);
        y = rand.nextInt(3) + 2;
        int z = rand.nextInt(3) + 2;
        
        displayFunction = "(" + y + "*x)^(" + z + "*x)";
        
        Function f = (x) -> Math.pow(y*x, z*x);
               
        correctAnswer = diff(f, g);
                               
        question = "What is the slope of the tangent of y = " + displayFunction + " at x = " + g + "?";
    }
    
//    private double differentiate(String f, double x) {
//        double ans = 0;
//        double h = 0.000000001;
//        
//        String exp = "((" + f.replaceAll("x", String.valueOf((double)x+h)) + ")-(" + f.replaceAll("x", String.valueOf(x)) + "))/" + h;
//
//        try {
//            solver.eval("result = " + exp);
//            ans = Double.parseDouble(String.valueOf(solver.get("result")));
//        } catch(Exception e) {}
//        
//        return ans;
//    }
    
    private double diff(Function f, double x) {
        double h = 0.000000001;               
        return (f.eval(x+h)-f.eval(x))/h;
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