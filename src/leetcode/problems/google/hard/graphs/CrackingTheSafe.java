package leetcode.problems.google.hard.graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CrackingTheSafe {
/**
    There is a safe protected by a password. The password is a sequence of n digits where each digit can be in the range [0, k - 1].
    <br>
    The safe has a peculiar way of checking the password. When you enter in a sequence, it checks the most recent n digits that were entered each time you type a digit.
    <br><br>
    For example, the correct password is "345" and you enter in "012345":<br>
    After typing 0, the most recent 3 digits is "0", which is incorrect.<br>
    After typing 1, the most recent 3 digits is "01", which is incorrect.<br>
    After typing 2, the most recent 3 digits is "012", which is incorrect.<br>
    After typing 3, the most recent 3 digits is "123", which is incorrect.<br>
    After typing 4, the most recent 3 digits is "234", which is incorrect.<br>
    After typing 5, the most recent 3 digits is "345", which is correct and the safe unlocks.<br><br>
    <b>Return any string of minimum length that will unlock the safe at some point of entering it.</b>


 <br>
            Example 1:
 <br><br>
    Input: n = 1, k = 2<br>
    Output: "10"<br><br>
    Explanation: The password is a single digit, so enter each digit. "01" would also unlock the safe.<br><br>
            Example 2:<br><br>

    Input: n = 2, k = 2<br>
    Output: "01100"<br>
 <br>
    Explanation: For each possible password:<br>
            - "00" is typed in starting from the 4th digit.<br>
            - "01" is typed in starting from the 1st digit.<br>
            - "10" is typed in starting from the 3rd digit.<br>
            - "11" is typed in starting from the 2nd digit.<br>
    Thus "01100" will unlock the safe. "10011", and "11001" would also unlock the safe.<br>

 <br><br><br>
            Constraints:<br>

            1 <= n <= 4<br>
            1 <= k <= 10<br>
            1 <= k<sup>n</sup> <= 4096*/

    class Solution {
        public String crackSafe(int n, int k) {
            final String allZeros = "0".repeat(n);
            StringBuilder sb = new StringBuilder(allZeros);
            dfs((int) Math.pow(k, n), n, k, new HashSet<>(List.of(allZeros)), sb);
            return sb.toString();
        }

        private boolean dfs(int passwordSize, int n, int k, Set<String> seen, StringBuilder path) {
            if (seen.size() == passwordSize)
                return true;

            StringBuilder prefix = new StringBuilder(path.substring(path.length() - n + 1));

            for (char c = '0'; c < '0' + k; ++c) {
                prefix.append(c);
                final String prefixStr = prefix.toString();
                if (!seen.contains(prefixStr)) {
                    seen.add(prefixStr);
                    path.append(c);
                    if (dfs(passwordSize, n, k, seen, path))
                        return true;
                    path.deleteCharAt(path.length() - 1);
                    seen.remove(prefixStr);
                }
                prefix.deleteCharAt(prefix.length() - 1);
            }

            return false;
        }
    }
}
