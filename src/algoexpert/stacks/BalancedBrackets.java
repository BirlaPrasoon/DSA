package algoexpert.stacks;

import java.util.Stack;

public class BalancedBrackets {

    public static boolean balancedBrackets(String str) {
        if(str.isEmpty()) return true;
        Stack<Character> stack = new Stack<>();
        for(char c: str.toCharArray()) {
            if(!valid(c)) continue;
            if(c == ')') {
                if(stack.isEmpty()) return false;
                if(stack.pop() != '(') return false;
            } else
            if(c == '}') {
                if(stack.isEmpty()) return false;
                if(stack.pop() != '{') return false;
            } else
            if(c == ']') {
                if(stack.isEmpty()) return false;
                if(stack.pop() != '[') return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    static boolean valid(char c) {
        return "(){}[]".contains(""+c);
    }

    public static void main(String[] args) {
        String s1 = "";
        String s2 = "()";
        String s3 = "[(])";
        String s4 = "()[]";
        String s5 = "[()]";
        String s6 = "{[]()}";
        String s7 = "[{((}]]";
        System.out.println(balancedBrackets(s1));
        System.out.println(balancedBrackets(s2));
        System.out.println(balancedBrackets(s3));
        System.out.println(balancedBrackets(s4));
        System.out.println(balancedBrackets(s5));
        System.out.println(balancedBrackets(s6));
        System.out.println(balancedBrackets(s7));

    }
}
