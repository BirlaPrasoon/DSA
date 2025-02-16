package leetcode.problems.google.medium.matrix;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MatchAlphaNumericalPatternInMatrix {
    public int[] findPattern(int[][] board, String[] pattern) {
        int M = pattern[0].length();
        int N = pattern.length;
        int n = board.length;
        int m = board[0].length;
        if(M>m || N>n) return new int[]{-1,-1};
        for(int i = 0; i<=n-N; i++) {
            for(int j = 0; j<=m-M; j++) {
                HashMap<Character, Integer> charIntMapping = new HashMap<>();
                HashMap<Integer, Character> intCharMapping = new HashMap<>();
                boolean isInvalidMatch = false;
                matching: for(int ii = 0; ii<N; ii++) {
                    for(int jj=0; jj<M; jj++) {
                        char c = pattern[ii].charAt(jj);
                        if(isChar(c)){
                            // isDigMappingAssigned?
                            int dig = charIntMapping.getOrDefault(c, -1);
                            if(dig >= 0) {
                                if(board[i+ii][j+jj] == dig) {
                                    continue;
                                } else {
                                    isInvalidMatch = true;
                                    break matching;
                                }
                            } else {
                                // If not, assign and continue
                                // check if this digit has a mapping with any character and this is not the same character.
                                char preExistingMappingForChar = intCharMapping.getOrDefault(board[i+ii][j+jj], ';');
                                if(isChar(preExistingMappingForChar)){
                                    if(c == preExistingMappingForChar) {
                                        continue;
                                    } else {
                                        isInvalidMatch = true;
                                        break matching;
                                    }
                                } else {
                                    charIntMapping.put(c, board[ii+i][jj+j]);
                                    intCharMapping.put(board[i+ii][j+jj], c);
                                }
                            }
                        } else {
                            if(board[i+ii][j+jj] != c-'0') {
                                isInvalidMatch = true;
                                break matching;
                            }
                        }
                    }
                }
                if(!isInvalidMatch) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    private boolean isChar(char c) {
        return c>='a' && c<='z';
    }

    private boolean isDigit(char c) {
        return c>='0' && c<='9';
    }

    public static void main(String[] args) {
        MatchAlphaNumericalPatternInMatrix m = new MatchAlphaNumericalPatternInMatrix();
        int matrix[][] = {{1,1,2},{3,3,4},{6,6,6}};
        String[] strings = {"ab","66"};
        System.out.println(Arrays.toString(m.findPattern(matrix, strings)));
    }
}
