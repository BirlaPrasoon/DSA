import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

public class Main {
    public static class Interval {
        int start;
        int end;
        int mergeCount;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    ", mergeCount=" + mergeCount +
                    '}';
        }
    }

    // generate a function that takes a graph using adjacency list and gives me articulation points.


    public static ArrayList<Integer> arrTolist() {
        ArrayList<Integer> list = new ArrayList<>();
        list.remove(list.size()-1);
        int[] x = new int[]{7, 10, 4, 3, 23, 15};
        for(int t:x){
            list.add(t);
        }
        return list;
    }

    private int dfs(String a, String b, List<String> words, HashSet<String> set, int count, HashMap<Integer, List<List<String>>> map, List<String> path){
        int min = Integer.MAX_VALUE;
        if(a.equals(b)) {
            if(map.containsKey(count)) {
                List<List<String>> list = new ArrayList<>();
                map.put(count, list);
            }
            map.get(count).add(new ArrayList<>(path));
            return count;
        }
        for(String w: words) {
            if(!set.contains(w) && isOneCharDiff(a, w)) {
                set.add(w);
                path.add(w);
                int t = dfs(w,b, words, set, count+1, map, path);
                if(t < min) {
                    min = t;
                }
                set.remove(w);
                path.remove(path.size()-1);
            }
        }
        return min;
    }

    private boolean isOneCharDiff(String a, String b) {
        int diff = 0;
        char[] x = a.toCharArray();
        char[] y = b.toCharArray();
        for(int i = 0; i<x.length; i++) {
            if(x[i] != y[i]) {
                diff++;
                if(diff>1) return false;
            }
        }
        return diff == 1;
    }

    public static void main(String[] args) {
//        int numOfSubsequencesWithSum = printSubsetWithSum(new int []{1,2,0,0,2,3,5}, 0, 4, 0, new ArrayList<>());
//        System.out.println(numOfSubsequencesWithSum);

        int n = 8;
        for(int x= n; x>=0; x--) {
            System.out.println(n--);
        }
    }


    private static int printSubsetWithSum(int a[], int index, int k, int sum, List<Integer> path) {
        if(index >= a.length) return 0;
        if(sum > k) return 0;
        if(sum == k) {
            System.out.println(path);
            return 1;
        }
        path.add(a[index]);
        int ans = printSubsetWithSum(a, index+1, k, sum + a[index], path);
        path.remove(path.size()-1);
        ans += printSubsetWithSum(a, index+1, k, sum, path);
        return ans;
    }


    private List<List<Integer>> generateSubSets(final int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        int subsetSize = 1<<nums.length;
        System.out.println("BINARY REpresentation: " + Integer.toBinaryString(subsetSize));
        for(int num = 0; num<subsetSize; num++) {
            List<Integer> list = new ArrayList<>();
            System.out.println("BINARY of num: " + Integer.toBinaryString(num));
            for(int i = 0; i<nums.length; i++) {
                if((num&(1<<i)) != 0) {
                    list.add(nums[i]);
                }
            }
            ans.add(list);
        }
        return ans;
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Arrays.stream(candies).max().getAsInt();
        List<Boolean> ans = new ArrayList<>();
        for(int t: candies) {
            if(t+extraCandies >= max) {
                ans.add(true);
            } else ans.add(false);
        }
        return ans;
    }


    public int gcd(int a, int b) {
        if(b> a) return gcd(b, a);
        if(b==0) return a;
        return gcd(b, a%b);
    }

    class Paired {
        int i,ele;
        Paired(int index, int element) {
            this.i = index;
            this.ele = element;
        }

        public boolean equals(Object o) {
            if(this ==o) return true;
            if(!(o instanceof Paired p)) return false;
            return p.i == i && p.ele == ele;
        }

        public int hashCode() {
            return 31* i + ele;
        }

        @Override
        public String toString() {
            return "{" +
                    "i=" + i +
                    ", ele=" + ele +
                    '}';
        }
    }

    class SmallerPairComparator implements Comparator<Paired> {
        public int compare(Paired p1, Paired p2) {
            if(p1.ele != p2.ele) {
                return p1.ele - p2.ele;
            } else return p1.i - p2.i;
        }
    }

    class GreaterPairComparator implements Comparator<Paired> {
        public int compare(Paired p1, Paired p2) {
            if(p1.ele != p2.ele) {
                return p2.ele - p1.ele;
            } else return p2.i - p1.i;
        }
    }


    // generate prime factors of a given numbers
    public List<Integer> primeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        int i = 2;
        while (n > 1) {
            if (n % i == 0) {
                factors.add(i);
                n /= i;
            } else {
                i++;
            }
        }
        return factors;
    }



    private static final Function<Integer, Integer> lower_bound = x -> x<0 ? ~x -1  : x;


    class Edge {
        int i, j, k;
        Edge(int i, int j, int k){
            this.i = i;
            this.j = j;
            this.k = k;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge edge)) return false;
            return i == edge.i && j == edge.j && k == edge.k;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j, k);
        }
    }

    public boolean isPathCrossing(String path) {
        Point current = new Point(0,0);
        HashSet<Point> visited = new HashSet<>();
        visited.add(current);
        for(char c: path.toCharArray()) {
            Point next = new Point(current.x, current.y);
            if(c == 'N') {
                next.x--;
            }else if(c == 'S') {
                next.x++;
            } else if(c == 'E') {
                next.y++;
            } else if(c == 'W') {
                next.y--;
            }
            if(visited.contains(next)) return true;
            visited.add(next);
        }
        return false;
    }

    class Point {
        int x,y;
        Point(int i, int j){
            this.x = i;
            this.y = j;
        }

        @Override
        public boolean equals(Object o) {
            if(!(o instanceof Point)) return false;
            Point p = (Point) o;
            return p.x == this.x && p.y == this.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }
    }
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for(int a: nums1){
            set1.add(a);
        }
        for(int b: nums2) {
            set2.add(b);
        }
        HashSet<Integer> set1copy = new HashSet<>(set1);
        HashSet<Integer> set2copy = new HashSet<>(set2);
        set1copy.removeAll(set2);
        set2copy.removeAll(set1);
        List<List<Integer>> ans =new ArrayList<>();
        ans.add(new ArrayList<>(set1copy));
        ans.add(new ArrayList<>(set2copy));
        return ans;
    }



    public void doSomething(List<Integer> list) {

    }

    public int mySqrt(int x) {
        return binarySearch(1, 46340, x);
    }

    private int binarySearch(int a, int b, int x) {
        if(a>=b) return b;
        int mid = a + (b-a)/2;
        int val = mid*mid;
        int next = (mid+1) * (mid+1);
//        System.out.println("a: " + a + " b:" + b + " mid:" + mid + " val: " + val);
        if(val == x) return mid;
        if(val < x && next > x) return mid;
        if(val > x) return binarySearch(a, mid, x);
        else return binarySearch(mid+1, b, x);

    }

    public int minTimeToVisitAllPoints(int[][] points) {
        int distance =0;
        for(int i = 0; i<points.length-1; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            int x2 = points[i+1][0];
            int y2 = points[i+1][1];
            System.out.println("x1: "+ x1 + " x2: " + x2 + " y1:" + y1 + " y2:"+ y2);
            int x = (x1 - x2)*(x1-x2);
            int y = (y1 - y2) * (y1-y2);
            int dist = (int)Math.sqrt(x+y);
            System.out.println("Dist: " + dist);
            distance += dist;
        }
        return distance;
    }

    public boolean isMatch(String s, String p) {
        p = processPattern(p);
        System.out.println("Pattern: " + p);
//        if(p.length() > s.length()) return false;
        int[][] dp = new int[s.length()+1][p.length()+1];
        return solve(s, p, 0, 0, dp);
    }

    private boolean solve(String s, String p, int i, int j, int[][] dp) {
        if(i>= s.length() && j>= p.length()) return true;
        if(i>=s.length()) {
            return j == p.length() - 1 && (p.charAt(j) == '*');
        }
        if(j>= p.length()) {
            return false;
        }
        System.out.println("s: " + s.substring(0, i+1) + " p: " + p.substring(0, j+1));
        if(dp[i][j] != 0) return dp[i][j] == 1;
        if(p.charAt(j) == '.') {
            if(j+1 < p.length() && p.charAt(j+1) == '*'){
                boolean ans = solve(s, p, i+1, j, dp) || solve(s, p, i+1, j+1, dp) || solve(s, p, i, j+1, dp);
                dp[i][j] = ans ? 1 : 2;
                return ans;
            }
            boolean ans = solve(s, p, i+1, j+1, dp);
            dp[i][j] = ans ? 1 : 2;
            return ans;
        } else if(p.charAt(j) == '*') {
            if(j-1>=0){
                char prev = p.charAt(j-1);
                if(prev == '.') {
                    boolean ans = solve(s, p, i+1, j+1, dp) || solve(s, p, i, j+1, dp) || solve(s, p, i+1, j, dp);
                    dp[i][j] = ans ? 1 : 2;
                    return ans;
                } else {
                    if(s.charAt(i) == prev) {
                        boolean ans = solve(s, p, i+1, j, dp) || solve(s, p, i, j+1, dp);
                        dp[i][j] = ans ? 1 : 2;
                        return ans;
                    } else {
                        boolean ans = solve(s, p, i, j+1, dp);
                        dp[i][j] = ans ? 1 : 2;
                        return ans;
                    }
                }
            }
            boolean ans = solve(s, p, i+1, j+1, dp) || solve(s, p, i, j+1, dp) || solve(s, p, i+1, j, dp);
            dp[i][j] = ans ? 1 : 2;
            return ans;
        } else {
            if(j+1 < p.length() && p.charAt(j+1) == '*'){
                boolean ans = solve(s, p, i+1, j, dp) || solve(s, p, i+1, j+1, dp) || solve(s, p, i, j+1, dp);
                dp[i][j] = ans ? 1 : 2;
                return ans;
            }
            boolean ans = s.charAt(i) == p.charAt(j) && solve(s, p, i+1, j+1, dp);
            dp[i][j] = ans ? 1 : 2;
            return ans;
        }
    }

    private String processPattern(String p) {
        if(p.length() == 0) return "";
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i<p.length(); i++) {
            if(i-1 >= 0 && p.charAt(i-1) == '*' && p.charAt(i) == '*') {

            } else {
                sb.append(p.charAt(i));
            }
        }

        return sb.toString();
    }

    static int findPlatform(int arr[], int dep[], int n)
    {
        Interval intervals[] = new Interval[arr.length];
        for(int i = 0; i<arr.length; i++) {
            intervals[i] = new Interval(arr[i], dep[i]);
        }
        ArrayList<Interval> intervals1 = mergeIntervals(intervals);
        int max = -1;
        for(Interval inte: intervals1) {
            if(inte.mergeCount > max) {
                max = inte.mergeCount;
            }
    }
        return max;
    }

    static class Meeting implements Comparable<Meeting> {
        int start, end, pos;
        public Meeting (int start, int end, int pos){
            this.start = start;
            this.end = end;
            this.pos = pos;
        }

        @Override
        public int compareTo(Meeting o2) {
            if(this.end < o2.end) {
                return -1;
            } else if (this.end > o2.end) {
                return 1;
            } else if(this.pos < o2.pos) {
                return -1;
            }
            return 1;
        }

        @Override
        public String toString() {
            return "Meeting{" +
                    "start=" + start +
                    ", end=" + end +
                    ", pos=" + pos +
                    '}';
        }
    }

    public static int getDistinctPair(int[] A) {
        if(A == null || A.length < 2) {
            return -1;
        }
        int i = 0;
        int size = A.length - 1;
        int ct = 0;
        if((A[0] + A[A.length - 1] ) % 2 == 0) {
            int s1 = A[0] + A[1];
            int s2 = A[A.length - 1] + A[A.length - 2];
            if(s1%2 == 0 && s2%2 ==0) {
                i = 1;
            } else {
                ct++;
                i = 2;
                size = A.length-2;
            }
        }

        for(; i<=size; i++) {
            if(i-1 >= 0 && (A[i] + A[i-1]) % 2 == 0) {
                ct++;
                i++;
            }
        }
        return ct;
    }

    static String reverseWords(String S)
    {
        // code here
        String s[] = S.split("\\.");
        int x[] = new int[10];
        OptionalInt min = Arrays.stream(x).min();
        int t = min.getAsInt();
        StringBuilder sb = new StringBuilder();
        for(int i = s.length-1;i>=0; i--) {
            String s2 = s[i];
            sb.append(s2);
            if(i>0) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    static char nonrepeatingCharacter(String S)
    {
        //Your code here
        int[] count= new int[26];
        char[] chars = S.toCharArray();
        for(char c: chars) {
            count[c-'a']++;
        }
        for(char c: chars) {
            if(count[c-'a'] == 1) {
                return c;
            }
        }
        return '$';
    }

    public static ArrayList<Interval> mergeIntervals(Interval[] arr)
    {
        Arrays.sort(arr, Comparator.comparingInt(i -> i.start));
        ArrayList<Interval> merged = new ArrayList<>();
        Interval current = new Interval();
        current = arr[0];
        current.mergeCount++;
        for(int i = 1; i<arr.length; i++) {
            if(arr[i].start >= current.end) {
                merged.add(current);
                current = arr[i];
                current.mergeCount++;
            } else if (arr[i].end > current.end) {
                current.mergeCount++;
                current.end = arr[i].end;
            } else {
                current.mergeCount++;
            }
        }
        merged.add(current);
        return merged;
    }

    static int[] rightGreatestElement(int a[]) {
        int ans[] = new int[a.length];
        int max = Integer.MIN_VALUE;
        for(int i = a.length-1; i>=0; i--) {
            if(a[i] > max) {
                max = a[i];
            }
            ans[i] = max;
        }
        return ans;
    }

    static int[] leftGreatestElement(int a[]) {
        int ans[] = new int[a.length];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < a.length; i++) {
            if(a[i] > max) {
                max = a[i];
            }
            ans[i] = max;
        }
        return ans;
    }

    static long trappingWater(int arr[], int n) {
        // Your code here
        int[] nge = rightGreatestElement(arr);
        int[] pge = leftGreatestElement(arr);
        long trappedWater = 0;
        for(int i = 0; i<arr.length; i++) {
            int ngeIndex = nge[i];
            int pgeIndex = pge[i];
            int diff = Math.min(ngeIndex, pgeIndex);
            trappedWater += diff- arr[i];
        }
        return trappedWater;
    }

    static int[] nge(int a[]) {
        Stack<Integer> st = new Stack<>();
        st.push(0);
        int[] nge = new int[a.length];
        // [13, 1, 4, 12, 5]
        for(int i =1; i<a.length; i++) {
            int ai = a[i];
            if(ai < a[st.peek()]) {
                st.push(i);
            } else {
                while(!st.isEmpty() && a[st.peek()] < ai) {
                    int x = st.peek();
                    nge[x] = i;
                    st.pop();
                }
                st.push(i);
            }
        }
        st.forEach(x -> nge[x] = -1);
        System.out.println(Arrays.toString(nge));
        return nge;
    }

    static int[] pge(int a[]) {
        Stack<Integer> st = new Stack<>();
        st.push(a.length -1);
        int[] nge = new int[a.length];
        // [13, 1, 4, 12, 5]
        for(int i =a.length -2; i>=0; i--) {
            int ai = a[i];
            if(ai < a[st.peek()]) {
                st.push(i);
            } else {
                while(!st.isEmpty() && a[st.peek()] < ai) {
                    int x = st.peek();
                    nge[x] = i;
                    st.pop();
                }
                st.push(i);
            }
        }
        st.forEach(x -> nge[x] = -1);
        System.out.println(Arrays.toString(nge));
        return nge;
    }


    static boolean ispar(String x)
    {
        Stack<Character> s= new Stack<>();
        for(char c: x.toCharArray()) {
            if(c == '[' || c=='{' || c == '(') {
                s.push(c);
            }
            else {
                if(s.isEmpty()) {
                    return false;
                }
                char top = s.peek();
                if(c == '}' && top == '{') {
                    s.pop();
                } else if(c == ']' && top == '[') {
                    s.pop();
                } else if(c == ')' && top == '(') {
                    s.pop();
                } else {
                    return false;
                }
            }
        }
        return s.isEmpty();
    }


    static class Pair implements Comparable<Pair>{
        int element, index;

        public Pair(int element, int index) {
            this.element = element;
            this.index = index;
        }

        @Override
        public int compareTo(Pair o) {
            return element - o.element;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return element == pair.element && index == pair.index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(element, index);
        }
    }
    public static int maximumGap(final List<Integer> A) {
        List<Pair> list = new ArrayList<>();
        for(int i=0; i<A.size(); i++){
            list.add(new Pair(A.get(i), i));
        }
        Collections.sort(list);
        int[] indexes = new int[list.size()];
        for(int i = 0; i<list.size(); i++) {
            indexes[i] = list.get(i).index;
        }
        int min = Integer.MAX_VALUE, minIndex = -1;
        int max = Integer.MIN_VALUE, maxIndex = -1;
        for(int i = 0; i<indexes.length; i++) {
            if(indexes[i] < min){
                min = indexes[i];
                minIndex = i;
            }
            if(indexes[i] > max) {
                max = indexes[i];
                maxIndex = i;
            }
        }

        int diff = maxIndex - minIndex + 1;
        return Math.max(diff, 0);
    }

    public static ArrayList<ArrayList<Integer>> solve(int A) {

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if(A == 0) {
            return ans;
        }
        ArrayList<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        ans.add(firstRow);
        for(int i = 1; i<A; i++) {
            ans.add(new ArrayList<Integer>());
            for(int j=0; j<=i; j++) {
                int first = 0;
                int second = 0;
                if(j>=0 && j<i) {
                    first = ans.get(i-1).get(j);
                }
                if(j-1>=0 && j-1<=i) {
                    second = ans.get(i-1).get(j-1);
                }
                ans.get(i).add(first+second);
            }
        }
        return ans;
    }

    public static  ArrayList<Integer> getRow(int A) {
        ArrayList<Integer> ans = new ArrayList<>(A);
        for(int i = 0; i<=A; i++) {
            ans.add((int)ncr(A, i));
        }
        return ans;
    }

    public static int repeatedNumber(final List<Integer> a) {
        int arr[] = new int[a.size()];
        for(int i =0; i<a.size(); i++){
            arr[i] = a.get(i);
        }
        int n = a.size();
        int count1 = 0, count2 = 0;

        // take the integers as the maximum
        // value of integer hoping the integer
        // would not be present in the array
        int first =  Integer.MIN_VALUE;
        int second = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            // if this element is previously
            // seen, increment count1.
            if (first == arr[i])
                count1++;

                // if this element is previously
                // seen, increment count2.
            else if (second == arr[i])
                count2++;

            else if (count1 == 0) {
                count1++;
                first = arr[i];
            }

            else if (count2 == 0) {
                count2++;
                second = arr[i];
            }

            // if current element is different
            // from both the previously seen
            // variables, decrement both the
            // counts.
            else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        // Again traverse the array and
        // find the actual counts.
        for (int i = 0; i < n; i++) {
            if (arr[i] == first)
                count1++;

            else if (arr[i] == second)
                count2++;
        }

        if (count1 > n / 3)
            return first;

        if (count2 > n / 3)
            return second;

        return -1;
    }

    static long ncr(int n, int r) {
        long res = 1;
        for(int i = 0; i<r; i++) {
            res = res * (n-i);
            res /= i+1;
        }
        return res;
    }

    public static class Solution {
        public ArrayList<Integer> plusOne(ArrayList<Integer> A) {
            int carry = 1;
            ArrayList<Integer> ans = new ArrayList<>();
            for(int i = A.size()-1; i>=0; i--) {
                int cur = A.get(i);
                cur = (cur + carry);
                if(cur >= 10) {
                    carry = 1;
                } else {
                    carry = 0;
                }
                cur %=10;
                ans.add(cur);
            }
            if(carry > 0){
                ans.add(carry);
            }
            Collections.reverse(ans);
            ArrayList<Integer> ans2 = new ArrayList<>();
            boolean trimComplete = false;
            for(Integer a: ans) {
                if(a==0 && !trimComplete){
                    continue;
                }
                trimComplete = true;
                ans2.add(a);
            }
            return ans2;
        }
    }
}

