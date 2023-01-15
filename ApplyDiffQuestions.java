
import bsh.Interpreter;
import java.util.Random;

public class ApplyDiffQuestions {
    
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

    public ApplyDiffQuestions() {
        correctAnswer = 0.0;
        expression = "";
        function = "";
        functions = new String[]{
            "Math.pow(Math.sin(x),2)/Math.pow(x,2)", 
            "(1-Math.cos(x))/Math.pow(x,2)", 
            "(Math.sin(x)-x)/Math.pow(x,3)", 
            "(Math.E-1-x)/Math.pow(x,2)", 
            "(Math.tan(x)/x"
        };
        x = 0;
        y = 0;
        question = "";
        rand = new Random();
        solver = new Interpreter();
    }
  
    private void generateQuestion1() {
        x = rand.nextInt(10) + 1;
        y = rand.nextInt(50) + 10;
        
        correctAnswer = ((double)x)/(((double)(y*y))*Math.PI);
        
        question = "Air is being pumped into a spherical balloon at a rate of " + x + " cm^3/min. Determine the rate at which the radius of the balloon is increasing when its diameter is " + y + "cm.";
    }
    
    private void generateQuestion2() {
        int i = rand.nextInt(5) + 0;
        function = functions [i];
        switch(i) {
            case 0 -> correctAnswer = 1.0;
            case 1 -> correctAnswer = 1.0/2.0;
            case 2 -> correctAnswer = -1.0/6.0;
            case 3 -> correctAnswer = 1.0/2.0;
            case 4 -> correctAnswer = 1.0;
        }
        
        question = "Evaluate the limit of y = " + function + " as x approaches zero.\n\nPlease enter the answer correct to two decimal places.";
    }

    private void generateQuestion3() {
        x = rand.nextInt(20) + 5;
        y = rand.nextInt(20) + 5;
        int z = rand.nextInt(20) + 5;
        
        correctAnswer = Math.sqrt((double)(y*y) + Math.pow((double)(-y*x)/(z),2));
        
        question = "A ladder is resting on a vertical wall. The foot of the ladder is sliding away at a rate of " + x 
                + " m/s and is " + y + "m from the wall. At this moment, the top of the ladder is moving down at a "
                + "rate of " + z + " m/s. Find the length of the ladder.";
    }

    private void generateQuestion4() {
        int x = rand.nextInt(96) + 5;
        
        correctAnswer = (32.0*Math.PI*x*x*x)/81.0;
        
        question = "Find the volume of the largest right circular cone that can be inscribed in a sphere of radius " + x + ".";
    }

    private void generateQuestion5() {
        int x = 10*(rand.nextInt(12) + 4);
        
        correctAnswer = (4.0*x)/(Math.PI+4.0);
        
        question = "A piece of wire " + x + "m long is cut into two pieces. One piece is "
                + "bent into a square and the other is bent into a circle. What is the length "
                + "of the square's piece so that the total area enclosed by both shapes is a minimum?"
                + "\n\nPlease do not enter in terms of pi.";
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