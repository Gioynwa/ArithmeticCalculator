
public class CheckMethods{

    /*method to check if character is an operator*/
    public static boolean isOperator(char c){
        if(c == '+' || c == '-' || c == '/' ||
            c == '*' || c == 'x' || c == '^' ){
            return true;
        }
        return false;
    }

    /*method to check if a string is an operand*/
    public static boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
