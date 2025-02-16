package leetcode.problems.google.hard.calculator;

import java.util.Stack;

public class BasicCalculator {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int number=0;
        int sign=1;
        int result=0;

        for(int i=0;i<s.length();i++)
        {
            char c = s.charAt(i);

            if(Character.isDigit(c))
            {
                number = number*10 + (c-'0');

            }

            else if(c=='+')
            {
                result += (sign*number);
                number = 0;
                sign=1;
            }// + ends

            else if(c=='-')
            {
                result += (sign*number);
                number=0;
                sign=-1;
            }//- ends

            else if(c=='(')
            {
                stack.push(result);
                stack.push(sign);
                number=0;
                result=0;
                sign=1;
            } // ( ends

            else if(c==')')
            {
                result += (sign*number);
                number=0;
                int prevSign=stack.pop();
                int prevResult=stack.pop();
                result = prevResult + prevSign*result;
            }// ) ends

        }//for ends

        result=result+sign* number;
        return result;

    }
    public static void main(String[] args) {

    }
}
