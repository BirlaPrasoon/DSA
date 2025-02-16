package leetcode.problems.google.hard.graphs.surprisingly_dp;

public class MinimumDegreeToConnectTrioInGraph {

/**
    You are given an undirected graph. You are given an integer n which is the number of nodes in the graph and an array edges,
    where each edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi.</br></br>

    A connected trio is a set of three nodes where there is an edge between every pair of them.</br></br>

    The degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.</br></br>

    Return the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.</br></br>

    Example 1:</br>

    Input: n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]</br>
    Output: 3</br>
    Explanation: There is exactly one trio, which is [1,2,3]. The edges that form its degree are bolded in the figure above.</br></br>

    Example 2:</br>

    Input: n = 7, edges = [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]</br>
    Output: 0</br>
    Explanation: There are exactly three trios:</br>
    1) [1,4,3] with degree 0.</br>
    2) [2,5,6] with degree 2.</br>
    3) [5,6,7] with degree 2.</br>

    */

    class Solution {
        public int minTrioDegree(int n, int[][] edges) {
            int[] degree=new int[n];
            int[][] adjMatrix=new int[n][n];
            for (int[] edge : edges) {
                adjMatrix[edge[0] - 1][edge[1] - 1] = 1;
                adjMatrix[edge[1] - 1][edge[0] - 1] = 1;
                degree[edge[0] - 1]++;
                degree[edge[1] - 1]++;
            }
            int minDegree=Integer.MAX_VALUE;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    for(int k=0;k<n;k++){
                        if(adjMatrix[i][j]==1 && adjMatrix[i][k]==1 && adjMatrix[k][j]==1){
                            int temp_degree=degree[i]-2+degree[j]-2+degree[k]-2;
                            minDegree=Math.min(minDegree,temp_degree);
                        }
                    }
                }
            }
            return minDegree==Integer.MAX_VALUE?-1:minDegree;

        }
    }
}
