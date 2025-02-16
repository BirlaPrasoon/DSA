package leetcode.problems.google.medium.breadth_first_search;

import leetcode.Pair;

import java.util.LinkedList;
import java.util.List;

public class SurroundedRegions {

    /*
    * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:

Connect: A cell is connected to adjacent cells horizontally or vertically.
Region: To form a region connect every 'O' cell.
Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
A surrounded region is captured by replacing all 'O's with 'X's in the input matrix board.


    * */

    public class SolutionDFS {
        protected Integer ROWS = 0;
        protected Integer COLS = 0;

        public void solve(char[][] board) {
            if (board == null || board.length == 0) {
                return;
            }
            this.ROWS = board.length;
            this.COLS = board[0].length;

            List<Pair<Integer, Integer>> borders = new LinkedList<Pair<Integer, Integer>>();
            // Step 1). construct the list of border cells
            for (int r = 0; r < this.ROWS; ++r) {
                borders.add(new Pair(r, 0));
                borders.add(new Pair(r, this.COLS - 1));
            }
            for (int c = 0; c < this.COLS; ++c) {
                borders.add(new Pair(0, c));
                borders.add(new Pair(this.ROWS - 1, c));
            }

            // Step 2). mark the escaped cells
            for (Pair<Integer, Integer> pair : borders) {
                this.DFS(board, pair.key, pair.value);
            }

            // Step 3). flip the cells to their correct final states
            for (int r = 0; r < this.ROWS; ++r) {
                for (int c = 0; c < this.COLS; ++c) {
                    if (board[r][c] == 'O') board[r][c] = 'X';
                    if (board[r][c] == 'E') board[r][c] = 'O';
                }
            }
        }

        protected void DFS(char[][] board, int row, int col) {
            if (board[row][col] != 'O') return;

            board[row][col] = 'E';
            if (col < this.COLS - 1) this.DFS(board, row, col + 1);
            if (row < this.ROWS - 1) this.DFS(board, row + 1, col);
            if (col > 0) this.DFS(board, row, col - 1);
            if (row > 0) this.DFS(board, row - 1, col);
        }
    }

}
