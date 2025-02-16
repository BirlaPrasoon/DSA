package algoexpert.strings;

public class ReverseWordsInAString {

    public String reverseWordsInString(String string) {
        int start = 0, end = 0;
        char[] chars = string.toCharArray();
        // AlgoExpert is the best!
        // start = 0, end = 0
        // i = 0 is not space, start = 0, end = 0
        // i = 1 is not space, start = 0, end = 1
        // i = 2 is not space, start = 0, end = 2
        // i = 3 is not space, start = 0, end = 3
        // i = 4 is not space, start = 0, end = 4
        // i = 5 is not space, start = 0, end = 5
        // i = 6 is not space, start = 0, end = 6
        // i = 7 is not space, start = 0, end = 7
        // i = 8 is not space, start = 0, end = 8
        // i = 9 is not space, start = 0, end = 9
        // i = 10 is space, start = 0, end = 10 => reverse (start, end -1); start = 10, wasPreviousSpace = true;
        // i = 11 is not space, start = 11, wasPreviousSpace = false, end = 11
        // i = 12 is not space, start = 11, wasPreviousSpace = false, end = 12
        // i = 13 is space, start = 11, end = 13 => reverse(start, end - 1); start = 13, wasPreviousSpace = true;
        // i = 14 is not space, start = 14, wasPreviousSpace = false, end = 14
        // i = 15
        boolean wasPreviousSpace = false;
        for(;end<chars.length; end++) {
            if(end == chars.length-1) {
                // This step is important
                if(wasPreviousSpace) {
                    reverseChars(chars, start+1, end);
                } else {
                    reverseChars(chars, start, end);
                }
//                System.out.println(string.substring(start, end+1));
                continue;
            }
            if(chars[end] != ' ') {
                if(wasPreviousSpace) {
                    wasPreviousSpace = false;
                    start = end;
                }
            } else if(chars[end] == ' ') {
                wasPreviousSpace = true;
                reverseChars(chars, start, end-1);
//                System.out.println(string.substring(start, end));
                start = end;
            }
        }
        StringBuilder sb = new StringBuilder(new String(chars));
        return sb.reverse().toString();
    }

    public void reverseChars(char[] chars, int i , int j) {
        while(i<j) {
            swap(chars, i, j);
            i++;
            j--;
        }
    }

    public void swap(char[] chars, int i, int j) {
        char c= chars[i];
        chars[i] = chars[j];
        chars[j] = c;
    }

    public static void main(String[] args) {
        ReverseWordsInAString rw = new ReverseWordsInAString();
        System.out.println(rw.reverseWordsInString("a ab a"));
    }
}
