package leetcode.problems.google.hard.backtracking;

public class CrosswordPuzzle {


    class Solution {
        public boolean placeWordInCrossword(char[][] board, String word) {
            int m = board.length, n = board[0].length, k = word.length();

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == '#' || (board[i][j] != ' ' && board[i][j] != word.charAt(0))) {
                        continue;
                    }

                    // Left to right
                    boolean leftToRight = (j == 0 || board[i][j - 1] == '#') && canPlaceWord(board, word, m, n, k, i, j, 0, 1);

                    // Right to left
                    boolean rightToLeft = (j == n - 1 || board[i][j + 1] == '#') && canPlaceWord(board, word, m, n, k, i, j, 0, -1);

                    // Up to down
                    boolean upToDown = (i == 0 || board[i - 1][j] == '#') && canPlaceWord(board, word, m, n, k, i, j, 1, 0);

                    // Down to up
                    boolean downToUp = (i == m - 1 || board[i + 1][j] == '#') && canPlaceWord(board, word, m, n, k, i, j, -1, 0);

                    // If any direction is possible, return true
                    if (leftToRight || rightToLeft || upToDown || downToUp) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean canPlaceWord(char[][] board, String word, int m, int n, int k, int i, int j, int rowInc, int colInc) {
            int endRow = i + rowInc * k;
            int endCol = j + colInc * k;

            if (endRow >= 0 && endRow < m && endCol >= 0 && endCol < n && board[endRow][endCol] != '#') {
                return false;
            }

            for (int p = 0; p < k; ++p) {
                if (i < 0 || i >= m || j < 0 || j >= n || (board[i][j] != ' ' && board[i][j] != word.charAt(p))) {
                    return false;
                }

                i += rowInc;
                j += colInc;
            }

            return true;
        }
    }

}
