package leetcode.problems.google.easy.arrays;

public class AddNumbers {
    public String addStrings(String num1, String num2) {
        if(num2.length() < num1.length()) return addStrings(num2, num1);
        StringBuilder sb = new StringBuilder();
        int sizeDiff = num2.length() - num1.length();
        while(sizeDiff-->0) sb.append(0);
        sb.append(num1);
        num1 = sb.toString();
        sb = new StringBuilder();
        System.gc();
        int carry = 0;
        for(int i = num2.length()-1; i>=0; i--) {
            int first = num1.charAt(i) - '0';
            int second = num2.charAt(i)-'0';
            int sum = first + second + carry;
            carry = sum/10;
            sum%= 10;
            sb.append(sum);
        }
        if(carry>0) sb.append(carry);
        return sb.reverse().toString();
    }
}
