package leetcode.problems.google.easy.greedy;

public class LexicographicallySmallestStringAfterASwap {
/**
    Given a string s containing only digits, return the
    lexicographically smallest string</br></br>
    that can be obtained after swapping adjacent digits in s with the same parity at most once.</br></br>

    Digits have the same parity if both are odd or both are even. For example, 5 and 9, as well as 2 and 4, have the same parity, while 6 and 9 do not.</br></br>



            Example 1:</br>

    Input: s = "45320"</br>

    Output: "43520"</br>

    Explanation:</br>

    s[1] == '5' and s[2] == '3' both have the same parity, and swapping them results in the lexicographically smallest string.</br></br>

            Example 2:</br>

    Input: s = "001"</br>

    Output: "001"</br>

    Explanation:</br>

    There is no need to perform a swap because s is already the lexicographically smallest.</br></br>



            Constraints:</br>

            2 <= s.length <= 100</br>
    s consists only of digits.</br>
        */

    class Solution {
        public String getSmallestString(String s) {
            int n = s.length();
            StringBuilder str = new StringBuilder(s);
            for(int i=0;i<n-1;i++){
                int a=Character.getNumericValue(str.charAt(i));
                int b=Character.getNumericValue(str.charAt(i+1));
                if((a%2==0 && b%2==0) || (a%2!=0 && b%2!=0)){
                    if(a>b){
                        char c1 = str.charAt(i);
                        char c2 = str.charAt(i+1);
                        str.setCharAt(i,c2);
                        str.setCharAt(i+1,c1);
                        break;
                    }
                }
            }
            return str.toString();
        }
}

}
