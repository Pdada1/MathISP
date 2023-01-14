
import bsh.Interpreter;
import java.util.Random;

public class ApplyIntQuestions {
    
    private double correctAnswer;
    private String expression;
    private String function;
    private int x;
    private int y;
    private String question;
    private Random rand;
    private Interpreter solver;

    public ApplyIntQuestions() {
        correctAnswer = 0.0;
        expression = "";
        function = "";
        x = 0;
        y = 0;
        question = "";
        rand = new Random();
        solver = new Interpreter();
    }
  
    private void generateQuestion1() {
        x = rand.nextInt(10) + 1;
        y = rand.nextInt(10) + 1;
        int a = rand.nextInt(5) + 1;
        int b = rand.nextInt(10) + 5;
        function = x + "*x + " + y;
        
        expression = "((" + x + "/2.0)*Math.pow(" + b + ", 2) +" + y + "*" + b + ")"
                + "-((" + x + "/2.0)*Math.pow(" + a + ", 2) + " + y + "*" + a + ")";
        
        try {
            solver.eval("result = " + expression);
            correctAnswer = Double.parseDouble(String.valueOf(solver.get("result")));
        } catch(Exception e) {}
        
        function = function.replaceAll("x", "t").replaceAll("Math.pow", "");
        question = "Find the downward displacement of Harry on a drop tower over the interval  [" + a + ", " + b + "] if his velocity is defined by v(t) = " + function + "."
                + "\n\nThe generated answer is approximate, so there is a 5% tolerance.";
    }
    
    private void generateQuestion2() {
        String function2 = "";
        int a = rand.nextInt(10) + 1;
        int b = rand.nextInt(10) + 1;
        function = String.valueOf(a) + "*Math.pow(x,2)";
        function2 = String.valueOf(b) + "*x";
        
        expression = integrate(function2, 0, b/a) + "-" + integrate(function, 0, b/a);
        
        try {
            solver.eval("result = " + expression);
            correctAnswer = Double.parseDouble(String.valueOf(solver.get("result")));
        } catch(Exception e) {}
        
        function = function.replaceAll("Math.", "");
        function2 = function2.replaceAll("Math.", "");
        
        question = "Find the area of the region enclosed by f(x) = " + function + " and g(x) = " + function2 + "."
                + "\n\nThe generated answer is approximate, so there is a 5% tolerance.";
    }

    private void generateQuestion3() {
        int a = rand.nextInt(20) + 1;
        int b = rand.nextInt(20) + 1;
        int c = rand.nextInt(20) + 1;
        
        correctAnswer = (double) (1.0/6.0)*a*b*c;
        
        question = "Find the volume of the pyramid delimited by the coordinate planes and the plane x/" + a + " + y/" + b + " + z/" + c + " = 1.";
    }

    private void generateQuestion4() {
        x = rand.nextInt(3) + 1;
        y = rand.nextInt(6) + 4;
        int g = rand.nextInt(10)+1;
        int c = rand.nextInt(10)+1;
        function = "g*Math.pow(x,2)+c";
        function = function.replaceAll("g", String.valueOf(g)).replaceAll("c", String.valueOf(c));
        
        expression = "Math.PI*" + integrate("Math.pow(" + function + ", 2) - Math.pow(" + c + ", 2)", x, y);
        
        try {
            solver.eval("result = " + expression);
            correctAnswer = Double.parseDouble(String.valueOf(solver.get("result")));
        } catch(Exception e) {}
        
        function = function.replaceAll("Math.", "");
        question = "Find the volume of the region bounded by the function y = " + function + " and the line y = " + c + " over the interval [" + x + ", " + y + "] when rotated about the x-axis."
                + "\n\nThe generated answer is approximate, so there is a 5% tolerance.\nPlease do not enter in terms of pi.";
    }

    private void generateQuestion5() {
        int g = rand.nextInt(5)+1;
        int c = rand.nextInt(5)+1;
        function = "g*x*Math.pow(x-c, 2)";
        function = function.replaceAll("g", String.valueOf(g)).replaceAll("c", String.valueOf(c));
        
        expression = "2*Math.PI*" + integrate("x*" + function, 0, c);
        
        try {
            solver.eval("result = " + expression);
            correctAnswer = 0.9*Double.parseDouble(String.valueOf(solver.get("result")));
        } catch(Exception e) {}
        
        function = function.replaceAll("Math.", "");
        question = "Find the volume of the region bounded by the function y = " + function + " and the x-axis over the interval [" + 0 + ", " + c + "] when rotated about the y-axis."
                + "\n\nThe generated answer is approximate, so there is a 5% tolerance.\nPlease do not enter in terms of pi.";
    }
    
    private String integrate(String f, int a, int b) {
        return "((" + b + "-" + a + ")/8.0)*(" + f.replaceAll("x", String.valueOf(a)) + "+(3.0*" + 
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