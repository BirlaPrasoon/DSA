package leetcode.problems.google.easy.recursion;

public class DecodeString {
    public static void main(String[] args) {
        System.out.println(decodeString("3[232]2[]"));
    }

    public static String decodeString(String s) {
        if(s.length() == 0) return s;
        StringBuilder sb = new StringBuilder();
        StringBuilder number = new StringBuilder();
        StringBuilder running = new StringBuilder();
        int count = 0;
        for(char c: s.toCharArray()) {
            if(Character.isDigit(c)) {
                if(count == 0) {
                    number.append(c);
                } else {
                    running.append(c);
                }
            } else if(c == '[') {
                if(count != 0) {
                    running.append(c);
                }
                count++;
            }else if(c == ']') {
                if(count > 0) {
                    count--;
                }
                if(count == 0) {
                    int num = Integer.parseInt(number.toString());
                    String str = decodeString(running.toString());
                    while(num-- > 0) {
                        sb.append(str);
                    }
                    number = new StringBuilder();
                    running = new StringBuilder();
                } else {
                    running.append(c);
                }
            } else {
                if(count>0) {
                    running.append(c);
                } else {
                    sb.append(c);
                }
            }
        }
        sb.append(running);
        return sb.toString();
    }
}
