
/**
 * Names: Ishan Garg, Krish Patel, Pranav Mahabal
 * Course: MCV4U0-1
 * Date: January 17, 2023
 * Teacher: Ms. Iulia Gugoiu
 */

import java.util.Random;

public class IntegrationQuestions {

    private double correctAnswer;
    private String expression;
    private String displayFunction;
    private int x;
    private int y;
    private String question;
    private Random rand;
    private String[] displayFunctions;
    private Function[] funcs;

    public IntegrationQuestions() {
        correctAnswer = 0.0;
        expression = "";
        funcs = new Function[] {
            (x) -> Math.pow(x,2),
            (x) -> Math.pow(x,3),
            (x) -> Math.sqrt(x),
            (x) -> Math.pow(x, 9) - Math.pow(x, 5) + Math.pow(x, 3) - x,
            (x) -> Math.pow(Math.E, x) - Math.pow(Math.E, -x),
            (x) -> Math.sin(x),
            (x) -> (Math.pow(x,2)*Math.sin(x))/(1+Math.pow(x,6)),
            (x) -> x/(Math.sqrt(x+1)),
            (x) -> Math.pow(Math.cos(x),2),
            (x) -> Math.pow(Math.sin(x),2)*Math.cos(x),
            (x) -> x/(Math.sqrt(Math.pow(x,2)+1)),
            (x) -> Math.pow(x,3)*Math.pow(Math.E, Math.pow(x,4)),
            (x) -> x*Math.sqrt(5+2*Math.pow(x,2)),
            (x) -> x*Math.cos(5*Math.pow(x,2))
        };
        displayFunctions = new String[] {
                "x^2",
                "x^3",
                "sqrt(x)",
                "x^9 - x^5 + x^3 - x",
                "e^x - e^(-x)",
                "sin(x)",
                "((x^2)*sin(x))/(1+x^6)",
                "x/(sqrt(x+1))",
                "cos^2(x)",
                "sin^2(x)*cos(x)",
                "x/(sqrt(x^2+1))",
                "(x^3)*e^(x^4)",
                "x*sqrt(5+2*(x^2))",
                "x*cos(5*(x^2))"
        };
        x = 0;
        y = 0;
        question = "";
        rand = new Random();
    }

    private void generateQuestion1() {
        x = rand.nextInt(4) + 1;
        y = rand.nextInt(5) + 5;
        int i = rand.nextInt(3) + 0;
        displayFunction = displayFunctions[i];

        correctAnswer = integrate(funcs[i], x, y);

        displayFunction = displayFunction.replaceAll("Math.", "");
        question = "Find the definite integral of y = " + displayFunction + " over the interval [" + x + ", " + y
                + "].";
    }

    private void generateQuestion2() {
        x = rand.nextInt(20) + 1;
        int i = rand.nextInt(4) + 3;
        displayFunction = displayFunctions[i];

        correctAnswer = 0.0;

        displayFunction = displayFunction.replaceAll("Math.E", "e").replaceAll("Math.", "");
        question = "Find the definite integral of y = " + displayFunction + " over the interval [-" + x + ", " + x
                + "].";
    }

    private void generateQuestion3() {
        String upperBound = "Math.pow(x, 2)";
        int x = rand.nextInt(10) + 1;
        int i = rand.nextInt(3) + 0;
        displayFunction = displayFunctions[i];
        
        correctAnswer = 2*(double)x*funcs[i].eval(Math.pow((double)x,2))-(funcs[i].eval(x));

        displayFunction = displayFunction.replaceAll("Math.", "");
        question = "Find F'(" + x + ") of F(t) = " + displayFunction.replaceAll("x", "t")
                + " over the interval [x, x^2].";
    }

    private void generateQuestion4() {
        int a = rand.nextInt(5) + 1;
        int b = rand.nextInt(5) + 6;
        int i = rand.nextInt(7) + 7;
        displayFunction = displayFunctions[i];

        correctAnswer = integrate(funcs[i],a,b);

        displayFunction = displayFunction.replaceAll("Math.", "");
        question = "Find the definite integral of y = " + displayFunction + " over the interval [" + a + ", " + b
                + "].";
    }

    private void generateQuestion5() {
        int a = rand.nextInt(3) + 1;
        int b = rand.nextInt(3) + 4;
        int c = rand.nextInt(3) + 7;

        Function f = (x) -> Math.pow(x, 3) - (a + b + c)*Math.pow(x, 2) + ((a * b) + (a * c) + (b * c))*x - (a * b * c);
        displayFunction = "x^3 - " + (a + b + c) + "*(x^2) + " + ((a * b) + (a * c) + (b * c)) + "*x - " + (a * b * c);

        correctAnswer = Math.abs(integrate(f, a, b)) + Math.abs(integrate(f, b, c));

        displayFunction = displayFunction.replaceAll("Math.", "").replaceAll("x", "t");
        question = "Find the total distance traveled by a particle moving at a velocity defined by v(t) = "
                + displayFunction + " over the interval [" + a + ", " + c + "]."
                + "\n\nNote: read the question carefully.";
    }

    // private String integrate(String f, int a, int b) {
    // return "((" + b + "-" + (a) + ")/8.0)*(" + f.replaceAll("x",
    // String.valueOf(a)) + "+(3.0*" +
    // f.replaceAll("x", String.valueOf((2.0*a+b)/3.0)) + ")+(3.0*" +
    // f.replaceAll("x", String.valueOf((a+2.0*b)/3.0)) + ")+" + f.replaceAll("x",
    // String.valueOf(b)) + ")";
    // }

//    private double integrate(String function, double a, double b) {
//        double ans = 0.0;
//        int n = 7500;
//        double fx = 0;
//
//        for (int i = 0; i < n; i++) {
//            try {
//                solver.eval("result = " + function.replaceAll("x",
//                        String.valueOf((double) a + (double) ((i - 1) * (((double) b - (double) a) / (double) n)))));
//                fx = Double.parseDouble(String.valueOf(solver.get("result")));
//                ans += (double) (((double) b - (double) a) / (double) n) * fx;
//            } catch (Exception e) {
//            }
//        }
//
//        return ans;
//    }
    
    private double integrate(Function f, double a, double b) {
        double ans = 0.0;
        double n = 20000000.0;
        for(int i = 0; i<n; i++) {
            double fx = f.eval(a + (double)(i-1)*(b-a)/n);
            ans += (double) ((b - a) / n) * fx;
        }
        return ans;
    }
    
    private interface Function {
        public double eval(double x);
    }

    public double getCorrectAns() {
        return correctAnswer;
    }

    public String getQuestion(int num) {
        switch (num) {
            case 100 -> generateQuestion1();
            case 200 -> generateQuestion2();
            case 300 -> generateQuestion3();
            case 400 -> generateQuestion4();
            case 500 -> generateQuestion5();
        }

        return question;
    }
}