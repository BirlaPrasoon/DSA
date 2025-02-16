package leetcode.problems.google.medium.implementation;

import java.util.HashMap;

public class DayBetweenDates {
    class Solution {
        public int daysBetweenDates(String date1, String date2) {
            int year1 = Integer.parseInt(date1.split("-")[0]);
            int month1= Integer.parseInt(date1.split("-")[1]);
            int day1  = Integer.parseInt(date1.split("-")[2]);
            int year2 = Integer.parseInt(date2.split("-")[0]);
            int month2= Integer.parseInt(date2.split("-")[1]);
            int day2  = Integer.parseInt(date2.split("-")[2]);
            int res = 0;

            int total1 = day1-1;
            for (int i = 1971; i < year1; ++i) total1 += totaldaysofayear(i, 12);
            total1 += totaldaysofayear(year1, month1-1);

            int total2 = day2-1;
            for (int i = 1971; i < year2; ++i) total2 += totaldaysofayear(i, 12);
            total2 += totaldaysofayear(year2, month2-1);

            return Math.abs(total2-total1);
        }
        public static int totaldaysofayear(int year, int untilthismonth) {
            int total = 0;
            boolean leap = isleap(year);
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(1, 31);
            map.put(2, leap?29:28);
            map.put(3, 31);
            map.put(4, 30);
            map.put(5, 31);
            map.put(6, 30);
            map.put(7, 31);
            map.put(8, 31);
            map.put(9, 30);
            map.put(10, 31);
            map.put(11, 30);
            map.put(12, 31);

            for (int i = 1; i <= untilthismonth; ++i) total += map.get(i);
            return total;
        }
        public static boolean isleap(int year) {
            // leap year if perfectly divisible by 400
            if (year % 400 == 0) {
                return true;
            }
            // not a leap year if divisible by 100
            // but not divisible by 400
            else if (year % 100 == 0) {
                return false;
            }
            // leap year if not divisible by 100
            // but divisible by 4
            else if (year % 4 == 0) {
                return true;
            }
            // all other years are not leap years
            else {
                return false;
            }
        }
    }
}
