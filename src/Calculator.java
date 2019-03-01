import java.util.*;
import java.io.*;

public class Calculator{

    public static void main(String[] args) {

        String expression
        String accepted = "0123456789+-/*x^.()\t ";
        int counter = 0, counter2 = 0;

        System.out.print("\nEnter math expression: ");
        Scanner input = new Scanner(System.in);

        expression = input.nextLine();

        //System.out.println(expression);

        /*checks if the given expression is correct*/
        for(int i = 0; i < expression.length(); i++){

            if(accepted.indexOf(expression.charAt(i)) == -1 ){
                System.out.println("Wrong Expression!");
                System.exit(1);
            }
            if(expression.charAt(i) == '('){
                counter++;
            }
            if(expression.charAt(i) == ')'){
                counter2++;
                if(counter2 > counter){
                    System.out.println("Wrong Expression!");
                    System.exit(1);
                }
            }
        }

        if(counter != counter2){
            System.out.println("Wrong Expression!");
            System.exit(1);
        }

        System.out.println("Math expression is: " + expression);

        /*tree construction*/
        ExpressionTree et = new ExpressionTree(expression);

        /*tree print*/
        System.out.print("\nExpression: ");
        et.toString(et);

        /*calculation of the expression*/
        double result = et.calculate(et);
        System.out.println("\nResult = " + result + "\n");

        //String dotString = et.toDotString(et);
        //System.out.println(dotString);

        /*code for dot file creation and compilation*/
        try {
            PrintWriter pfile = new PrintWriter("./output/ArithmeticExpression.dot");
            pfile.println(et.toDotString(et));
            pfile.close();
            System.out.println("PRINT DOT FILE OK!");

            Process p = Runtime.getRuntime().exec("dot -Tpng ./output/ArithmeticExpression.dot " +"-o ./output/ArithmeticExpression.png");
            p.waitFor();
            System.out.println("PRINT PNG FILE OK!");
        }
        catch(Exception ex) {
            System.err.println("Unable to write dotString!!!");
            ex.printStackTrace();
            System.exit(1);
        }

        System.out.println();
    }

}
