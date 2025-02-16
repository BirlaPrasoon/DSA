package leetcode.problems.google.easy.arrays;

public class AddBinary {
    public String addBinary(String a, String b) {
        if(a.isEmpty()) return b;
        if(b.isEmpty()) return a;
        if(b.length() < a.length()) {
            String t = b;
            b = a;
            a=t;
        }
        if(a.length() < b.length()) {
            int diff = b.length() - a.length();
            StringBuilder sb = new StringBuilder();
            while(diff-->0){
                sb.append(0);
            }
            sb.append(a);
            a = sb.toString();
        }
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for(int i = a.length()-1; i>=0;i--) {
            int first = a.charAt(i) - '0';
            int second = b.charAt(i) - '0';
            int sum = first + second + carry;
            carry = sum/2;
            sum%=2;
            sb.append(sum);
        }
        if(carry>0)
            sb.append(carry);
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        System.out.println(new AddBinary().addBinary(a, b));
    }
}
