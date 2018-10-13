package array.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这道题的启发:再求一些数量 最值问题时候 并不需要将很多中间过程具体标识出来
 * 可以因地制宜的 用方便的办法 达到同样目的 比如写法1 2中都用某种方式标识了走法
 * 而并不需要解析出具体走法 只需最后统计每种走法的收益
 */
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
     * 但依旧是暴力O(n^4) 因为下方的LA 长度为N^2
     * LA LB遍历就是O((n^2)^2) == O(n^4)
     * 注意为何这个转换可行
     */
    public static int largestOverlap(int[][] A, int[][] B) {
        int N = A.length;
        List<Integer> LA = new ArrayList<>();
        List<Integer> LB = new ArrayList<>();
        Map<Integer, Integer> count = new HashMap<>();
        /**
         * 数组是正方形 且 长度 <= 30 所以百位千位以上记录行移动数量 个位十位记录例移动数量
         * 相应的LA LB的 i - j 就能得到一种行列走法的唯一标识
         */
        for (int i = 0; i < N * N; ++i) if (A[i / N][i % N] == 1) LA.add(i / N * 100 + i % N);
        for (int i = 0; i < N * N; ++i) if (B[i / N][i % N] == 1) LB.add(i / N * 100 + i % N);
        for (int i : LA) for (int j : LB)
            count.put(i - j, count.getOrDefault(i - j, 0) + 1);
        int res = 0;
        for (int i : count.values()) res = Math.max(res, i);
        return res;
    }

    /**
     * 暴力解 遍历两个数组中为1的值 横纵移动位置为走法 记录这种走法的"收益"
     */
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
