package leetcode.problems.google.hard.implementation;

public class ValidNumber {


    /**
     * An integer number followed by an optional exponent.
     * A decimal number followed by an optional exponent.
     * An integer number is defined with an optional sign '-' or '+' followed by digits.
     * <p>
     * A decimal number is defined with an optional sign '-' or '+' followed by one of the following definitions:
     * <p>
     * Digits followed by a dot '.'.
     * Digits followed by a dot '.' followed by digits.
     * A dot '.' followed by digits.
     * An exponent is defined with an exponent notation 'e' or 'E' followed by an integer number.
     * <p>
     * The digits are defined as one or more digits.
     */
    public boolean isNumber(String s) {
        boolean seenDigit = false;
        boolean seenExponent = false;
        boolean seenDot = false;

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (Character.isDigit(curr)) {
                seenDigit = true;
            } else if (curr == '+' || curr == '-') {
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (curr == 'e' || curr == 'E') {
                if (seenExponent || !seenDigit) {
                    return false;
                }
                seenExponent = true;
                seenDigit = false;
            } else if (curr == '.') {
                if (seenDot || seenExponent) {
                    return false;
                }
                seenDot = true;
            } else {
                return false;
            }
        }

        return seenDigit;
    }
}
