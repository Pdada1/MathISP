
import bsh.Interpreter;
import java.util.Random;

public class MysteryQuestions {
    
    private double correctAnswer;
    private String expression;
    private String function;
    private int x;
    private int y;
    private String question;
    private Random rand;
    private Interpreter solver;
    private String[] functions;
    private String[] xExps;

    public MysteryQuestions() {
        correctAnswer = 0.0;
        expression = "";
        function = "";
        functions = new String[]{
            "z*Math.pow(x,2) + g*x*y", 
            "Math.pow(x,2) + z*Math.pow(y,2) - g", 
            "z*y+Math.sqrt(Math.pow(x,2)+Math.pow(y,2))",
            "Math.pow(x,2)-z*x*y+Math.pow(y,2)-g*y", 
            "Math.sin(x*y)-Math.pow(x,2)*y", 
        };
        x = 0;
        y = 0;
        question = "";
        rand = new Random();
        solver = new Interpreter();
    }
  
    private void generateQuestion1() {
        
    }
    
    private void generateQuestion2() {
        double x = rand.nextInt(3) + 0;
        double y = rand.nextInt(3) + 0;
        double approxX = rand.nextInt(2) + x + 1;
        int z = rand.nextInt(5) + 1;
        int g = rand.nextInt(5) + 1;
        double h = Math.round(((approxX-x)/(rand.nextInt(100) + 1))*100.0)/100.0;
        while((approxX-x)%h != 0 || (approxX-x > 1.0 && h < 1.0) || (approxX-x > 1.0 && h > 1.0)) {
            h = Math.round(((approxX-x)/(rand.nextInt(100) + 1))*100.0)/100.0;
        }
        
        int j = rand.nextInt(5) + 0;
        function = functions[j].replaceAll("z", String.valueOf(z)).replaceAll("g", String.valueOf(g));
                
        correctAnswer = 0;
        double tempY = y;
        double dx = 0;
        for(double i = 0; i<(approxX-x); i+=h) {
            try {
                solver.eval("result = " + function.replaceAll("x", String.valueOf(x+i)).replaceAll("y", String.valueOf(tempY)));
                dx = Double.parseDouble(String.valueOf(solver.get("result")));
            } catch(Exception e) {}
            correctAnswer = tempY + h*(dx);
            tempY = correctAnswer;
        }

        function = function.replaceAll("Math.", "");
        question = "Suppose y = y(x) is the solution to the differential equation dy/dx = " + function + " with y(" + x + ") = " + y + ". "
                + "Use Euler's method to approximate y(" + approxX + ") using h = " + h + " as the increment.";
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