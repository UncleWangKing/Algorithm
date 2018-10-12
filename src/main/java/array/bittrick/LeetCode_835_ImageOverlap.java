package array.bittrick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_835_ImageOverlap {
    public static void main(String[] args) {
        int A[][] = {
            {1,1,0},
            {0,1,0},
            {0,1,0}
        };
        int B[][] = {
            {0,0,0},
            {0,1,1},
            {0,0,1}
        };
        System.out.println(largestOverlap(A, B));
    }

    /**
     * 先思考两个一维数组情况，那么A从1到n-1步挨个移动 可得到移动后和B的最大匹配值
     * 然后将二维数组转一维即可解决
     * 从暴力的O(n^4)降到O(n^2)
     * 注意为何这个转换可行
     */
    public static int largestOverlap(int[][] A, int[][] B) {
        int N = A.length;
        List<Integer> LA = new ArrayList<>();
        List<Integer> LB = new ArrayList<>();
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < N * N; ++i) if (A[i / N][i % N] == 1) LA.add(i / N * 100 + i % N);
        for (int i = 0; i < N * N; ++i) if (B[i / N][i % N] == 1) LB.add(i / N * 100 + i % N);
        for (int i : LA) for (int j : LB)
            count.put(i - j, count.getOrDefault(i - j, 0) + 1);
        int res = 0;
        for (int i : count.values()) res = Math.max(res, i);
        return res;
    }

    public static int largestOverlap2(int[][] A, int[][] B) {
        int N = A.length;
        int[][] count = new int[2 * N - 1][2 * N - 1];
        int max = 0;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                if(1 == A[i][j])
                    for(int i2 = 0; i2 < N; i2++)
                        for(int j2 = 0; j2 < N; j2++)
                            if(1 == B[i2][j2])
                                count[i - i2 + N - 1][j - j2 + N - 1]++;

        for(int i = 0; i < 2 * N - 1; i++)
            for(int j = 0; j < 2 * N - 1; j++)
                max = Math.max(max,count[i][j]);
        return max;
    }
}
