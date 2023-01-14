
import bsh.Interpreter;
import java.util.Random;

public class IntegrationQuestions {
    
    private double correctAnswer;
    private String expression;
    private String function;
    private int x;
    private int y;
    private String question;
    private Random rand;
    private Interpreter solver;
    private String[] functions;

    public IntegrationQuestions() {
        correctAnswer = 0.0;
        expression = "";
        function = "";
        functions = new String[]{
            "Math.log(x)", 
            "Math.log10(x)", 
            "Math.pow(x,2)", 
            "Math.pow(x,3)", 
            "Math.sqrt(x)",
            "Math.pow(x, 9) - Math.pow(x, 5) + Math.pow(x, 3) - x", 
            "Math.pow(Math.E, x) - Math.pow(Math.E, -x)", 
            "Math.sin(x)", 
            "(Math.pow(x,2)*Math.sin(x))/(1+Math.pow(x,6))",
            "x/(Math.sqrt(x+1))", 
            "Math.pow(Math.cos(x),2)", 
            "Math.pow(Math.sin(x),2)*Math.cos(x)", 
            "x/(Math.sqrt(Math.pow(x,2)+1))"
        };
        x = 0;
        y = 0;
        question = "";
        rand = new Random();
        solver = new Interpreter();
    }
  
    private void generateQuestion1() {
        x = rand.nextInt(11) + 0;
        y = rand.nextInt(10) + 11;
        int i = rand.nextInt(5) + 0;
        function = functions[i];
        
        expression = integrate(function, x, y);
        
        try {
            solver.eval("result = " + expression);
            correctAnswer = Double.parseDouble(String.valueOf(solver.get("result")));
        } catch(Exception e) {}
        
        function = function.replaceAll("log10", "temp").replaceAll("log", "ln").replaceAll("temp", "log10").replaceAll("Math.", "");
        question = "Find the definite integral of y = " + function + " over the interval [" + x + ", " + y + "]."
                + "\n\nThe generated answer is approximate, so there is a 5% tolerance.";
    }
    
    private void generateQuestion2() {
        x = rand.nextInt(20) + 1;
        int i = rand.nextInt(4) + 5;
        function = functions[i];
        
        correctAnswer = 0.0;
        
        function = function.replaceAll("log10", "temp").replaceAll("log", "ln").replaceAll("temp", "log10").replaceAll("Math.E", "e").replaceAll("Math.", "");
        question = "Find the definite integral of y = " + function + " over the interval [-" + x + ", " + x + "]."
                + "\n\nThe generated answer is approximate, so there is a 5% tolerance.";
    }

    private void generateQuestion3() {
        int a = rand.nextInt(5) + 1;
        int b = rand.nextInt(5) + 6;
        int c = rand.nextInt(5) + 11;
        
        function = "Math.pow(x, 3) - " + (a+b+c) + "*Math.pow(x, 2) + " + ((a*b)+(a*c)+(b*c)) + "*x - " + (a*b*c);
                
        expression = "Math.abs(" + integrate(function, a, b) + ") + Math.abs(" + integrate(function, b, c) + ")";
        
        try {
            solver.eval("result = " + expression);
            correctAnswer = Double.parseDouble(String.valueOf(solver.get("result")));
        } catch(Exception e) {}
        
                function = function.replaceAll("log10", "temp").replaceAll("log", "ln").replaceAll("temp", "log10").replaceAll("Math.", "").replaceAll("x", "t");
        question = "Find the total distance traveled by a particle moving at a velocity defined by v(t) = " + function + " over the interval [-" + a + ", " + c + "]."
                + "\n\nBe careful when reading the question, the negative signs (if present) may be hard to see.";
    }

    private void generateQuestion4() {
        int a = rand.nextInt(5) + 1;
        int b = rand.nextInt(5) + 6;
        int i = rand.nextInt(3) + 9;
        function = functions[i];
        
        expression = integrate(function, a, b);
        
        try {
            solver.eval("result = " + expression);
            correctAnswer = Double.parseDouble(String.valueOf(solver.get("result")));
        } catch(Exception e) {}
        
        function = function.replaceAll("Math.", "");
        question = "Find the definite integral of y = " + function + " over the interval [" + a + ", " + b + "]."
                + "\n\nThe generated answer is approximate, so there is a 5% tolerance.";
    }

    private void generateQuestion5() {
        String upperBound = "Math.pow(x, 2)";
        String lowerBound = "x";
        int x = rand.nextInt(10) + 1;
        int i = rand.nextInt(5) + 0;
        function = functions[i];
        
        expression = "2*x*" + function.replaceAll("x", upperBound) + "-" + function;
        expression = expression.replaceAll("x", String.valueOf(x));
        
        try {
            solver.eval("result = " + expression);
            correctAnswer = Double.parseDouble(String.valueOf(solver.get("result")));
        } catch(Exception e) {}
        
        function = function.replaceAll("Math.", "");
        question = "Find F'(" + x + ") of F(t) = " + function.replaceAll("x", "t") + " over the interval [" + lowerBound + ", " + upperBound.replaceAll("Math.", "") + "]."
                + "\n\nThe generated answer is approximate, so there is a 5% tolerance.";
    }
    
    private String integrate(String f, int a, int b) {
        return "((" + b + "-" + (a) + ")/8.0)*(" + f.replaceAll("x", String.valueOf(a)) + "+(3.0*" + 
                f.replaceAll("x", String.valueOf((2.0*a+b)/3.0)) + ")+(3.0*" + 
                f.replaceAll("x", String.valueOf((a+2.0*b)/3.0)) + ")+" + f.replaceAll("x", String.valueOf(b)) + ")";
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