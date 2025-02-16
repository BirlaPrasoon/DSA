package algoexpert.recursion;

import java.util.ArrayList;

public class ValidIPAddress {

    public ArrayList<String> validIPAddresses(String string) {
        ArrayList<String> ans = new ArrayList<>();
        if(string == null || string.length() < 4) return ans;
        helper(string, "", 0, 3, ans);
        return ans;
    }

    private void helper(String input, String cur, int index, int k, ArrayList<String> ans) {
        if(index > input.length() || k < 0) return;
        if(k == 0) {
            String remainingString = getCurrentSegment(cur) + input.substring(index);
            if (validateValidIPSegment(remainingString)) {
                ans.add(cur + input.substring(index));
            }
            return;
        }
        // index can be out of bound when k > 0, we need to handle that in case k = 0
        // but we also need to handle that when k is not 0, in that case we should just return.
        if(index == input.length()) return;
        if(cur.length() == 0) {
            helper(input, cur + input.charAt(index), index+1, k, ans);
            return;
        }
        String curSegment = getCurrentSegment(cur);
        if(curSegment.length() == 0) {
            helper(input, cur + input.charAt(index), index+1, k, ans);
        } else if(curSegment.length() == 3) {
            helper(input, cur + '.' + input.charAt(index), index+1, k-1, ans);
        } else {
            String newSeg = curSegment + input.charAt(index);
            helper(input, cur + '.' + input.charAt(index), index+1, k-1, ans);
            if(validateValidIPSegment(newSeg)) {
                helper(input, cur + input.charAt(index), index + 1, k, ans);
            }
        }
    }

    private static String getCurrentSegment(String cur) {
        String[] segments = cur.split("\\.");
        return segments[segments.length-1];
    }

    private boolean validateValidIPSegment(String s) {
        if(s == null || s.length()>3) return false;
        int x = Integer.parseInt(s);
        // This is crucial, number should not start with 0. I made a mistake here.
        String num = Integer.toString(x);
        return num.length() == s.length() && x>=0 && x<256;
    }

    public static void main(String[] args) {
        ValidIPAddress v = new ValidIPAddress();
        String s= "3700100";
        System.out.println(v.validIPAddresses(s));
    }
}
