package leetcode.problems.google.hard.union_find;

import java.util.Arrays;

public class CheckIfRectangleCornerIsReachable {

    /**
     * You are given two positive integers xCorner and yCorner, and a 2D array circles,</br>
     * where circles[i] = [xi, yi, ri] denotes a circle with center at (xi, yi) and radius ri.</br>
     * </br>
     * There is a rectangle in the coordinate plane with its bottom left corner at the origin and
     * top right corner at the coordinate (xCorner, yCorner).</br></br>
     * <p>
     * You need to check whether there is a path from the bottom left corner to the top right corner such that</br>
     * the entire path lies inside the rectangle,</br> does not touch or lie inside any circle,</br> and touches the rectangle only at the two corners.</br></br>
     * <p>
     * Return true if such a path exists, and false otherwise.
     * <p>
     * The intuition is to find if any of the four walls of the rectangle are connected by the intersecting circles
     * such that it blocks the path from origin to (X, Y).
     * <br>
     * <b>Approach:</b><br>
     * Stating First we have traverse through the circles
     * If the circle contains the origin point or destination point then no path can be found because everything is inside the circle
     * If the circle touches the bottom wall we connected it to some arbitary node say n + 1 in the DSU.
     * Similary if the circle touches the right, top, and left walls we connect it to n + 2, n + 3 and n + 4 respectively.
     * Now its time to traverse through pair of circles and check if they intersect.
     * If circles intersect join them using DSU set.
     * After performing all these operation, the only way possible to block the path is if
     * the left and right wall are connected or
     * left and bottom wall are connected or
     * right and bottom wall are connected or
     * top and bottom wall are connected.
     * So we can easily check that using DSU set.
     */

    // This solution is missing an edge case, but good to see. Look at one below.
    class Solution {
        public boolean canReachCorner(int X, int Y, int[][] circles) {
            int n = circles.length;
            DSU dsu = new DSU(n + 5);

            int[] ORIGIN = {0, 0};
            int[] DESTINATION = {X, Y};
            int LEFT_WALL = n + 1;
            int UPPER_WALL = n + 2;
            int RIGHT_WALL = n + 3;
            int BOTTOM_WALL = n + 4;

            int i = 0;
            for (int[] circle : circles) {
                int cx = circle[0];
                int cy = circle[1];
                int r = circle[2];

                if (isInside(circle, ORIGIN) || isInside(circle, DESTINATION))
                    return false;

                boolean isLeftWallInside = Math.abs(cy) <= r;
                boolean isUpperWallInside = Math.abs(cx - X) <= r;
                boolean isRightWallInside = Math.abs(cy - Y) <= r;
                boolean isBottomWallInside = Math.abs(cx) <= r;

                if (isLeftWallInside)
                    dsu.union(i, LEFT_WALL);
                if (isUpperWallInside)
                    dsu.union(i, UPPER_WALL);
                if (isRightWallInside)
                    dsu.union(i, RIGHT_WALL);
                if (isBottomWallInside)
                    dsu.union(i, BOTTOM_WALL);
                i++;
            }

            for (i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (circlesIntersect(circles[i], circles[j])) {
                        dsu.union(i, j);
                    }
                }
            }
            return !(dsu.areConnected(LEFT_WALL, BOTTOM_WALL) || dsu.areConnected(UPPER_WALL, RIGHT_WALL) ||
                    dsu.areConnected(UPPER_WALL, BOTTOM_WALL) || dsu.areConnected(LEFT_WALL, RIGHT_WALL));
        }

        public boolean circlesIntersect(int[] circle1, int[] circle2) {
            int cx1 = circle1[0], cy1 = circle1[1], r1 = circle1[2];
            int cx2 = circle2[0], cy2 = circle2[1], r2 = circle2[2];
            return (double) (cx1 - cx2) * (cx1 - cx2) + (double) (cy1 - cy2) * (cy1 - cy2) <= (double) (r1 + r2) * (r1 + r2);
        }

        public boolean isInside(int[] circle, int[] point) {
            int cx = circle[0], cy = circle[1], r = circle[2];
            int x = point[0], y = point[1];
            return (double) (cx - x) * (cx - x) + (double) (cy - y) * (cy - y) <= (double) r * r;
        }
    }

    public class DSU {
        int set[];

        DSU(int size) {
            set = new int[size];
            Arrays.fill(set, -1);
        }

        public boolean isParent(int node) {
            return set[node] < 0;
        }

        public int findParent(int node) {
            if (set[node] < 0)
                return node;
            return set[node] = findParent(set[node]);
        }

        public void union(int node1, int node2) {
            node1 = findParent(node1);
            node2 = findParent(node2);
            if (node1 == node2)
                return;

            if (set[node1] <= set[node2]) {
                set[node1] += set[node2];
                set[node2] = node1;
            } else {
                set[node2] += set[node1];
                set[node1] = node2;
            }
        }

        public boolean areConnected(int node1, int node2) {
            return findParent(node1) == findParent(node2);
        }
    }


    class SolutionDFS {
        public boolean canReachCorner(int xCorner, int yCorner, int[][] circles) {
            int[] edges = new int[circles.length];
            boolean[] isInside = new boolean[circles.length];
            for (int i = 0; i < circles.length; i++) {
                long x = circles[i][0];
                long y = circles[i][1];
                long r = circles[i][2];
                isInside[i] = (x <= xCorner && y <= yCorner);
                if (x * x + y * y <= r * r || (x - xCorner) * (x - xCorner) + (y + yCorner) * (y + yCorner) <= r * r)
                    return false;
                if ((y - r <= 0 && y + r >= 0 && x <= xCorner) || (x + r >= xCorner && x - r <= xCorner && y <= yCorner))
                    edges[i] = 1;
                if ((x - r <= 0 && x + r >= 0 && y <= yCorner) || (y + r >= yCorner && y - r <= yCorner && x <= xCorner))
                    edges[i] += 2;
                if (edges[i] == 3)
                    return false;
            }
            // System.out.println(links);
            // System.out.println(Arrays.toString(edges));
            boolean[] seens = new boolean[circles.length];
            for (int i = 0; i < circles.length; i++) {
                if (!seens[i] && dfs(circles, seens, edges, isInside, xCorner, yCorner, i) == 3)
                    return false;
            }
            return true;
        }

        public int dfs(int[][] circles, boolean[] seens, int[] edges, boolean[] isInside, int xCorner, int yCorner, int i) {
            seens[i] = true;
            int res = edges[i];
            long x = circles[i][0];
            long y = circles[i][1];
            long r = circles[i][2];
            for (int j = 0; j < circles.length; j++) {
                if (seens[j])
                    continue;
                long x1 = circles[j][0];
                long y1 = circles[j][1];
                long r1 = circles[j][2];
                long dX = x - x1;
                long dY = y - y1;
                long dR = r + r1;
                if (dX * dX + dY * dY <= dR * dR && (isInside[i] || isInside[j] ||
                        (x + x1 <= 2L * xCorner && y + y1 <= 2L * yCorner))) {
                    res |= dfs(circles, seens, edges, isInside, xCorner, yCorner, j);
                    if (res == 3)
                        return res;
                }
            }
            return res;
        }
    }
}
