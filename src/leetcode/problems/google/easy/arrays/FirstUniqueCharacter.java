package leetcode.problems.google.easy.arrays;

public class FirstUniqueCharacter {
    public static void main(String[] args) {
        FirstUniqueCharacter f = new FirstUniqueCharacter();
        System.out.println(f.firstUniqChar("leetcode"));
    }

    public int firstUniqChar(String s) {
        int[] chars = new int[26];
        char[] array = s.toCharArray();
        for(char c: array){
            chars[c-'a']++;
        }
        for(int i = 0; i<array.length; i++) {
            char c = array[i];
            if(chars[c-'a'] == 1) return i;
        }

        return -1;
    }
}
