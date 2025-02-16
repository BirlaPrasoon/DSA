package leetcode.problems.google.hard.graphs.toposort.still_not_understood_but_very_much_asked;

import java.util.HashMap;
import java.util.Stack;

public class DesignExcelSumFormula {
    /**
     * Design the basic function of Excel and implement the function of the sum formula.</br></br>
     * <p>
     * Implement the Excel class:</br></br>
     * <p>
     * Excel(int height, char width) Initializes the object with the height and the width of the sheet. The sheet is an integer matrix mat of size height x width with the row index in the range [1, height] and the column index in the range ['A', width]. All the values should be zero initially.</br></br>
     * void set(int row, char column, int val) Changes the value at mat[row][column] to be val.</br></br>
     * int get(int row, char column) Returns the value at mat[row][column].</br></br>
     * int sum(int row, char column, List<String> numbers) Sets the value at mat[row][column] to be the sum of cells represented by numbers and returns the value at mat[row][column]. This sum formula should exist until this cell is overlapped by another value or another sum formula. numbers[i] could be on the format:</br></br>
     * "ColRow" that represents a single cell.</br>
     * For example, "F7" represents the cell mat[7]['F'].</br></br>
     * "ColRow1:ColRow2" that represents a range of cells. The range will always be a rectangle where "ColRow1" represent the position of the top-left cell, and "ColRow2" represents the position of the bottom-right cell.</br></br>
     * For example, "B3:F7" represents the cells mat[i][j] for 3 <= i <= 7 and 'B' <= j <= 'F'.</br>
     * Note: You could assume that there will not be any circular sum reference.
     * <p>
     * For example, mat[1]['A'] == sum(1, "B") and mat[1]['B'] == sum(1, "A").
     */
    public static class Excel {
        Formula[][] Formulas;

        class Formula {
            // This represents which cells are directly or indirectly dependent on this and how many times.
            HashMap<String, Integer> dependendentOnTimes;
            int val;

            Formula(HashMap<String, Integer> c, int v) {
                val = v;
                dependendentOnTimes = c;
            }

            public boolean isDependentOn(String s) {
                return this.dependendentOnTimes.containsKey(s);
            }
        }

        // To be used to generate topo sorting order.
        Stack<int[]> stack = new Stack<>();

        public Excel(int H, char W) {
            Formulas = new Formula[H][(W - 'A') + 1];
        }

        public int get(int r, char c) {
            if (Formulas[r - 1][c - 'A'] == null)
                return 0;
            return Formulas[r - 1][c - 'A'].val;
        }

        public void set(int r, char c, int v) {
            Formulas[r - 1][c - 'A'] = new Formula(new HashMap<String, Integer>(), v);
            topologicalSort(r - 1, c - 'A');
            execute_stack();
        }

        public int sum(int r, char c, String[] strs) {
            HashMap<String, Integer> cells = convert(strs);
            System.out.println("Converted cells:" + cells);
            int summ = calculate_sum(r - 1, c - 'A', cells);
            set(r, c, summ);
            Formulas[r - 1][c - 'A'] = new Formula(cells, summ);
            return summ;
        }

        public void topologicalSort(int r, int c) {
            String cellStr = "" + (char) ('A' + c) + (r + 1);
            for (int i = 0; i < Formulas.length; i++) {
                for (int j = 0; j < Formulas[0].length; j++) {
                    if (Formulas[i][j] != null && Formulas[i][j].isDependentOn(cellStr)) {
                        topologicalSort(i, j);
                    }
                }
            }
            stack.push(new int[]{r, c});
        }

        public void execute_stack() {
            while (!stack.isEmpty()) {
                int[] top = stack.pop();
                int x = top[0];
                int y = top[1];
                calculate_sum_and_set(x, y);
            }
        }

        public HashMap<String, Integer> convert(String[] strs) {
            HashMap<String, Integer> res = new HashMap<>();
            for (String st : strs) {
                if (!st.contains(":"))
                    res.put(st, res.getOrDefault(st, 0) + 1);
                else {
                    String[] cells = st.split(":");
                    int si = Integer.parseInt(cells[0].substring(1));
                    int ei = Integer.parseInt(cells[1].substring(1));

                    char sj = cells[0].charAt(0);
                    int  ej = cells[1].charAt(0);
                    for (int i = si; i <= ei; i++) {
                        for (char j = sj; j <= ej; j++) {
                            res.put("" + j + i, res.getOrDefault("" + j + i, 0) + 1);
                        }
                    }
                }
            }
            return res;
        }

        public void calculate_sum_and_set(int r, int c) {
            if (Formulas[r][c].dependendentOnTimes.isEmpty()) return;
            calculate_sum(r, c, Formulas[r][c].dependendentOnTimes);
        }


        public int calculate_sum(int r, int c, HashMap<String, Integer> cells) {
            int sum = 0;
            for (String s : cells.keySet()) {
                int x = Integer.parseInt(s.substring(1)) - 1;
                int y = s.charAt(0) - 'A';
                sum += (Formulas[x][y] != null ? Formulas[x][y].val : 0) * cells.get(s);
            }
            Formulas[r][c] = new Formula(cells, sum);
            return sum;
        }

        public static void main(String[] args) {
            Excel excel = new Excel(10, 'H');
            excel.set(1, 'A', 2);
            excel.set(1, 'B', 5);
            excel.set(1, 'C', 11);
            excel.set(1, 'D', 12);
            excel.set(1, 'E', 4);
            excel.set(1, 'F', 10);
            excel.set(1, 'G', 11);
            excel.set(1, 'H', 7);
            excel.sum(3, 'C', new String[]{"A1", "A1:B2"});
            excel.set(2, 'B', 2);
            excel.set(2, 'B', 2);
            System.out.println(excel.get(3, 'C'));
        }
    }


}
