package leetcode.problems.google.hard.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NQueens {
    /**
     * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
     * <p>
     * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
     * <p>
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: n = 4
     * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
     * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
     * Example 2:
     * <p>
     * Input: n = 1
     * Output: [["Q"]]
     */

    class Solution {
        private int size;
        private List<List<String>> solutions = new ArrayList<List<String>>();
        int count;

        public List<List<String>> solveNQueens(int n) {
            size = n;
            count = 0;
            char emptyBoard[][] = new char[size][size];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    emptyBoard[i][j] = '.';
                }
            }

            backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>(), emptyBoard);
            return solutions;
        }

        // Making use of a helper function to get the
        // solutions in the correct output format
        private List<String> addBoardToAnswer(char[][] state) {
            List<String> board = new ArrayList<String>();
            for (int row = 0; row < size; row++) {
                String current_row = new String(state[row]);
                board.add(current_row);
            }

            return board;
        }

        private void backtrack(int row, Set<Integer> diagonals, Set<Integer> antiDiagonals, Set<Integer> cols, char[][] state) {
            // Base case - N queens have been placed
            if (row == size) {
                solutions.add(addBoardToAnswer(state));
                count++;
                return;
            }

            for (int col = 0; col < size; col++) {
                int currDiagonal = row - col;
                int currAntiDiagonal = row + col;
                // If the queen is not placeable
                if (cols.contains(col) || diagonals.contains(currDiagonal) || antiDiagonals.contains(currAntiDiagonal)) {
                    continue;
                }

                // "Add" the queen to the board
                cols.add(col);
                diagonals.add(currDiagonal);
                antiDiagonals.add(currAntiDiagonal);
                state[row][col] = 'Q';

                // Move on to the next row with the updated board state
                backtrack(row + 1, diagonals, antiDiagonals, cols, state);

                // "Remove" the queen from the board since we have already
                // explored all valid paths using the above function call
                cols.remove(col);
                diagonals.remove(currDiagonal);
                antiDiagonals.remove(currAntiDiagonal);
                state[row][col] = '.';
            }
        }
    }

}
