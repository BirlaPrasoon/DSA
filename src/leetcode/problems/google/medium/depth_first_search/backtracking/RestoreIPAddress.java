package leetcode.problems.google.medium.depth_first_search.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RestoreIPAddress {
    /*
    * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.



Example 1:

Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]
Example 2:

Input: s = "0000"
Output: ["0.0.0.0"]
Example 3:

Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

    * */

    private final int LOWEST = 0, BIGGEST = 255, MAX_LENGTH = 3, DIGITS = 4;
    private final Set<String> ipAddresses = new HashSet<>();
    public List<String> restoreIpAddresses(String s) {
        slv(0, new StringBuilder(), DIGITS, s);
        return new ArrayList<>(ipAddresses);
    }

    private void slv(int index, StringBuilder current, int numDigits, String str) {
        if((str.length() - current.length() + (DIGITS - numDigits)) > numDigits * MAX_LENGTH) return;
        if(numDigits == 0) {
            ipAddresses.add(current.substring(0, current.length()-1));
            return;
        }
        int count = 1;
        for (int i = index; i < Math.min(str.length(), index + MAX_LENGTH); i++) {
            String part = str.substring(index, i + 1);
            if(isValidDigit(part)) {
                current.append(part).append(".");
                slv(i + 1, current, numDigits - 1, str);
                current.delete(current.length() - count - 1, current.length());
            }
            count++;
        }
    }

    private boolean isValidDigit(String part) {
        //
        if(part.length() > 1 && part.startsWith("0")) return false;
        int value = Integer.parseInt(part);
        return value >= LOWEST && value <= BIGGEST;
    }
}
