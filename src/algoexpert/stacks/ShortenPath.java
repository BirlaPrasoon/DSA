package algoexpert.stacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class ShortenPath {

    public static String shortenPath(String path) {
        String p[] = path.split("/");
        Stack<String> stack = new Stack<>();
        for(String s: p) {
            if(s.equals(".") || s.length() == 0) continue;
            else if(s.equals("..")) {
                if(stack.size()>0) stack.pop();
            } else {
                stack.push(s);
            }
        }
        System.out.println(stack);
        StringBuilder sb = new StringBuilder("/");
        ArrayList<String> ar = new ArrayList<>();
        while(!stack.isEmpty()) {
            ar.add(stack.pop());
        }
        Collections.reverse(ar);
        for(String s: ar) {
            sb.append(s);
            sb.append("/");
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
