package leetcode.problems.google.medium.depth_first_search.backtracking;

public class KthLexicographicalStringOfAllHappyNumbers {
    /*
    * A happy string is a string that:
    * consists only of letters of the set ['a', 'b', 'c'].
    * s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
    * For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.
    *
    * Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.
    *
    * Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
    *
    * Example 1:
    *
    * Input: n = 1, k = 3
    * Output: "c"
    * Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
    * Example 2:
    *
    * Input: n = 1, k = 4
    * Output: ""
    * Explanation: There are only 3 happy strings of length 1.
    * Example 3:
    *
    * Input: n = 3, k = 9
    * Output: "cab"
    * Explanation: There are 12 different happy string of length 3
    * ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"].
    * You will find the 9th string = "cab"
    *
    * */

    int cnt = 0;
    String res = "";
    public String getHappyString(int n, int k) {
        StringBuilder sb = new StringBuilder();

        dfs(sb,n,k);

        return res;
    }
    public void dfs(StringBuilder sb, int n,int k){

        if (res.length() > 0) return;

        if (sb.length() == n){
            cnt++;
            if (cnt == k) {
                res = sb.toString();
            }
            return;
        }
        char[] ch = {'a','b','c'};

        for(char c : ch){
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) != c){
                sb.append(c);
                dfs(sb,n,k);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
