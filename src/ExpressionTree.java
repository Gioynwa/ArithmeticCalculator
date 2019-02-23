import java.util.*;

public class ExpressionTree{

    double value;
    char operator;
    ExpressionTree leftChild, rightChild;

    public ExpressionTree(String input){

        input = input.trim();

        /*remove parentheses if there is no operator outside of them*/
        if(input.charAt(0) == '(' && input.charAt(input.length() - 1) == ')' ){

            char[] inputArray = input.toCharArray();
            int counter = 0;

            for(int i = 0; i < inputArray.length; i++){
                char ch = inputArray[i];

                if(ch == '('){
                    if(counter == 0){
                        inputArray[i] = ' ';
                    }
                    counter++;
                }
                if(ch == ')'){
                    counter--;
                    if(counter == 0){
                        inputArray[i] = ' ';
                    }
                }
            }

            input = String.valueOf(inputArray);

        }

        input = input.trim();

        /*checks if this part is number and creates a node*/
        if(CheckMethods.isDouble(input)){

            value = Double.parseDouble(input);
            operator = '~';
            leftChild = rightChild = null;

            return;
        }

        int min = 10, pos = -1, counter = 0, prec = 0;
        String left, right;
        char ch;

        /*a loop to find the operator which has the lowest precedence*/
        for(int i = 0; i < input.length(); i++){

            ch = input.charAt(i);

            if(ch == '('){
                counter++;
            }
            if(ch == ')') {
                counter--;
            }

            if(CheckMethods.isOperator(ch) && counter == 0){

                switch(ch){

                    case '+': {prec = 1; break;}
                    case '-': {prec = 2; break;}
                    case '*':
                    case 'x': {prec = 3; break;}
                    case '/': {prec = 4; break;}
                    case '^': {prec = 5; break;}

                }

                if(min > prec){
                    pos = i;
                    min = prec;
                }
            }
        }

        //System.out.println(input);

        /*if the character is an operator then creates a node with it.
         *The left and right substrings are its children*/
        if(CheckMethods.isOperator(input.charAt(pos))){

            left = input.substring(0, pos);
            right = input.substring(pos+1, input.length());

            value = -1;
            operator = input.charAt(pos);
            leftChild = new ExpressionTree(left);
            rightChild = new ExpressionTree(right);

        }
    }

    /*methods for creating dot string*/
    static int k = 0;

    public String toDotString(ExpressionTree root, int nodeLabel){

        String s = ""; String preset = "node";
        String roo = preset + nodeLabel;

        if(root.leftChild != null){

            if(root.leftChild.value == -1){
                s += preset + (k+1) + " [label=\"" + root.leftChild.operator + "\", shape=circle, color=black]\n\t";
            }
            else if(root.leftChild.value != -1){
                s += preset + (k+1) + " [label=\"" + root.leftChild.value + "\", shape=circle, color=black]\n\t";
            }
            s = s + roo + " -> " + preset + (k+1) + "\n\t";
            k = k + 2;
            s = s + toDotString(root.leftChild, k-1);

        }

        if(root.rightChild != null){

            if(root.rightChild.value == -1){
                s += preset + (k+1) + " [label=\"" + root.rightChild.operator + "\", shape=circle, color=black]\n\t";
            }
            else if(root.rightChild.value != -1){
                s += preset + (k+1) + " [label=\"" + root.rightChild.value + "\", shape=circle, color=black]\n\t";
            }
            s = s + roo + " -> " + preset + (k+1) + "\n\t";
            k = k + 2;
            s = s + toDotString(root.rightChild, k-1);
        }

        return s;

    }

    public String toDotString(ExpressionTree root){

        String s = "digraph ExpressionTree {\n\t";
        int pos;

        s += "fontcolor=\"navy\";\n\t";
        s += "fontsize=\"20\";\n\t";
        s += "labelloc=\"t\";\n\t";
        s += "label=\"Arithmetic Expression\"\n\t";

        s += "node0 [label=\"" + root.operator + "\", shape=circle, color=black]\n\t";
        s += toDotString(root, 0);
        pos = s.lastIndexOf('\t');
        StringBuilder sb = new StringBuilder(s);
        sb.deleteCharAt(pos);
        s = sb.toString();
        return s + "}";

    }

    /*method for printing the expression tree*/
    public void toString(ExpressionTree thisRoot){

        if(thisRoot != null){

            if(thisRoot.value == -1){
                System.out.print('(');
            }
            toString(thisRoot.leftChild);

            if(thisRoot.value != -1){
                System.out.print(thisRoot.value);
            }
            else if(thisRoot.value == -1){
                System.out.print(thisRoot.operator);
            }

            toString(thisRoot.rightChild);
            if(thisRoot.value == -1){
                System.out.print(')');
            }

        }
    }

    /*method for calculating the result*/
    public double calculate(ExpressionTree root){

        double left, right;

        if(root != null){

            if(root.value != -1){

                return root.value;

            }
            else{

                left = calculate(root.leftChild);
                right = calculate(root.rightChild);

                switch(root.operator){

                    case '+':
                        return left + right;
                    case '-':
                        return left - right;
                    case '*':
                    case 'x':
                        return left * right;
                    case '/':
                        return left / right;
                    case '^':
                        return Math.pow(left, right);
                }
            }
        }
        return 0;
    }
}
