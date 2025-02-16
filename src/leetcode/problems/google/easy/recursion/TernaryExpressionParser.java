package leetcode.problems.google.easy.recursion;

public class TernaryExpressionParser {
    public static void main(String[] args) {
        TernaryExpressionParser t = new TernaryExpressionParser();
        System.out.println(t.parseTernary("T?2:3"));
    }

    public String parseTernary(String expression) {
        if(expression.length() == 1) return expression;
        char c = expression.charAt(0);
        if(c == 'T') {
            return parseTernary(findLeft(expression));
        } else {
            return parseTernary(findRight(expression));
        }
    }

    private String findRight(String s) {
        StringBuilder running = new StringBuilder();
        int count = 0;
        int i = 0;
        for(char c: s.toCharArray()) {
            if(c == '?') {
                if(count != 0) {
                    running.append(c);
                }
                count++;
            } else if(c == ':') {
                count --;
                if(count == 0) {
                    return s.substring(i+1);
                } else {
                    running.append(c);
                }
            } else {
                if(count > 0) {
                    running.append(c);
                }
            }
            i++;
        }
        return running.toString();
    }
    private String findLeft(String s) {
        StringBuilder running = new StringBuilder();
        int count = 0;
        for(char c: s.toCharArray()) {
            if(c == '?') {
                if(count != 0) {
                    running.append(c);
                }
                count++;
            } else if(c == ':') {
                count --;
                if(count == 0) {
                    return running.toString();
                } else {
                    running.append(c);
                }
            } else {
                if(count > 0) {
                    running.append(c);
                }
            }
        }
        return running.toString();
    }
}
