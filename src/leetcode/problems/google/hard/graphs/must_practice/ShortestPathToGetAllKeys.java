package leetcode.problems.google.hard.graphs.must_practice;

import java.util.LinkedList;

public class ShortestPathToGetAllKeys {
    public int shortestPathAllKeys(String[] grid) {
        int y = grid.length, x = grid[0].length(), maskKeys = 0;
        boolean[][][] vis = new boolean[y][x][64];             //for all variants of sets of keys

        LinkedList<int[]> q = new LinkedList<>();      //key, y, x
        for(int i = 0; i != y; ++i) {
            for (int j = 0; j != x; ++j) {
                char ch = grid[i].charAt(j);
                if (ch == '@') q.add(new int[]{0, i, j});               //find out start position
                else if (isValidKey(ch))
                    maskKeys |= 1 << (ch - 'a');          //and constract mask for all presented keys
            }
        }
        for(int lev = 0; !q.isEmpty(); ++lev)          //BFS
            for(int n = q.size(); n != 0; --n){
                int[] t = q.pollFirst();                   //fetch current position
                int key = t[0], r = t[1], c = t[2];

                if(alreadyDoneOrShouldNotDo(y, x, vis, key, r, c)) continue;

                char ch = grid[r].charAt(c);
                if(ch == '#') continue;

                if(isValidLock(ch))                   //if we in lock position
                    if( (key & ( 1<< (ch - 'A'))) == 0) continue;   // and we don't have right key

                if(isValidKey(ch)) {                             //if we in key position
                    key |= 1<< (ch - 'a');
                    if(key == maskKeys) return lev;          //check ? have we all keys
                }

                vis[r][c][key] = true;

                q.addLast(new int[]{key, r-1, c});
                q.addLast(new int[]{key, r+1, c});
                q.addLast(new int[]{key, r, c-1});
                q.addLast(new int[]{key, r, c+1});
            }

        return -1;
    }

    private static boolean alreadyDoneOrShouldNotDo(int y, int x, boolean[][][] vis, int key, int r, int c) {
        return r == -1 || r == y || c == -1 || c == x || vis[r][c][key];
    }

    private static boolean isValidKey(char ch) {
        return ch >= 'a' && ch <= 'f';
    }

    private static boolean isValidLock(char ch) {
        return ch >= 'A' && ch <= 'F';
    }
}
