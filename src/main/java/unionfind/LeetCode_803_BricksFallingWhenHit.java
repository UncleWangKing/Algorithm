package unionfind;

import util.ZDaPangArrayUtil;

public class LeetCode_803_BricksFallingWhenHit {
    public static void main(String[] args) {
        int [][]grid = {
                {1,0,0,0},
                {1,1,1,0}
        };
        int [][]hits = {{1,0}};//[2]
        ZDaPangArrayUtil.printArray(hitBricks(grid, hits));
    }

    public static int[] hitBricks(int[][] grid, int[][] hits) {
        // Think of it reversely: from end to start each hit will add one blick to the grid,
        // What we want to find is how many blicks that have been fixed onto the ceiling.
        // This is a Union-Find Solution
        if (grid.length == 0) return new int[0];
        final int M = grid.length, N = grid[0].length, K = hits.length;
        for (int[] hit : hits) {
            // Using grid[i][j]-- every time, we can check if grid[i][j] > 0
            // to find the first time the blick has been removed if there are duplicated hits in the sequence.
            grid[hit[0]][hit[1]]--;
        }
        int[] ufs = new int[M * N];         // Union find set, ufs[key] is key's parent, thru which we can find root recursively
        int[] num = new int[M * N];         // Number of nodes in this set, only stored at the root node
        int[] ret = new int[K];             // the retval we need to compute
        // init ufs and num
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] < 1) continue;
                addOneBlick(grid, ufs, num, i, j, M, N, false);
            }
        }
        // start to go thru hits (reversely)
        for (int k = K - 1; k >= 0; k--) {
            int x = hits[k][0], y = hits[k][1];
            if (++grid[x][y] <= 0) ret[k] = 0;
            else ret[k] = addOneBlick(grid, ufs, num, x, y, M, N, true);
        }
        return ret;
    }

    private static int addOneBlick(int[][] grid, int[] ufs, int[] num, int x, int y, int M, int N, boolean union4) {
        // This func is used to union neighbors and calculate the count of blicks that have been unioned to ceiling
        // Note that if this is iterating from left-top to right-bottom, we only need to union current with left and top
        // So union4 is a flag determining if we go thru 4 neighbors or just 2;
        int key = x * N + y;
        ufs[key] = key;
        num[key] = 1;
        int count = key < N ? 1 : 0;    // if key is at ceiling, init count as 1, because this blick has already been fixed.
        if (x > 0 && grid[x-1][y] == 1) count += union(ufs, num, key - N, key, N);
        if (y > 0 && grid[x][y-1] == 1) count += union(ufs, num, key - 1, key, N);
        if (union4 && x < M - 1 && grid[x+1][y] == 1) count += union(ufs, num, key + N, key, N);
        if (union4 && y < N - 1 && grid[x][y+1] == 1) count += union(ufs, num, key + 1, key, N);
        count--;                        // remove the added blick itself if we have some blicks fixed.
        return Math.max(0, count);
    }

    private static int union(int[] ufs, int[] num, int k1, int k2, int N) {
        // Union the two set
        // If one root is ceiling, union the other to this
        // Only if some out-ceiling nodes has been unioned to ceiling set, we return the number of blicks settled.
        while (k1 != ufs[k1]) k1 = ufs[k1];
        while (k2 != ufs[k2]) k2 = ufs[k2];
        if (k1 == k2) return 0;
        if (k1 < N || k2 >= N) {
            num[k1] += num[k2];
            ufs[k2] = k1;
            if (k1 < N && k2 >= N) return num[k2];
            return 0;
        }
        // k1 >= N && k2 < N
        num[k2] += num[k1];
        ufs[k1] = k2;
        return num[k1];
    }
}
