package leetcode.problems.google.hard.backtracking;

public class FriendsPairing {

    /**
     * https://leetcode.com/discuss/general-discussion/1349122/friends-pairing-problem <br>
     * <br>
     * Given n friends, each one can remain single or can be paired up with some other friend.<br>
     * Each friend can be paired only once.<br>
     * Find out the total number of ways in which friends can remain single or can be paired up.<br>
     *
     * <code>
     * Input  : n = 3<br>
     * Output : 4<br><br>
     * Explanation:<br>
     * {1}, {2}, {3} : all single<br>
     * {1}, {2, 3} : 2 and 3 paired but 1 is single.<br>
     * {1, 2}, {3} : 1 and 2 are paired but 3 is single.<br>
     * {1, 3}, {2} : 1 and 3 are paired but 2 is single.<br>
     * Note that {1, 2} and {2, 1} are considered same.<br>
     * </code>
     * <br>
     * Recursive Algo<br>
     * <code>
     * f(n) = ways n people can remain single or pair up<br>
     * <p>
     * For n-th person there are two choices:<br>
     * 1) n-th person remains single, so only 1 way so  we recur<br>
     * for remaining i.e f(n - 1)   or you can say 1*f(n-1)<br>
     * 2) n-th person pairs up with any of the <br>
     * remaining n - 1 persons. So apart from the 2 people forming a pair for remaining n-2 persons we We get (n - 1) * f(n - 2) ways<br>
     * </code>
     * <br>
     * Therefore we can recursively write <code>f(n)</code> as:<br>
     * <code>f(n) = f(n - 1) + (n - 1) * f(n - 2)</code><br>
     */

    int countFriendsPairings(int n) {
        if (n <= 2) return n;
        return countFriendsPairings(n - 1) + (n - 1) * countFriendsPairings(n - 2);
    }


    int countFriendsPairingsDP(int n) {
        if (n <= 2) return n;
        int a = 1, b = 2, c = 2;
        for (int i = 3; i <= n; i++) {
            c = 1 * b + (i - 1) * (a);
            a = b;
            b = c;
        }
        return c;
    }

}
