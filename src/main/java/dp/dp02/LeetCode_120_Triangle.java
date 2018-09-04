package dp.dp02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_120_Triangle {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));

        System.out.println(minimumTotal2(triangle));
    }

    /**
     * 转态方程:dp[i][j] = min(dp[i-1][j], dp[i-1][j-1]) + triangle[i][j]
     * 初始项:dp[0][0] = triangle[0][0]
     * 正常操作 坐下
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.size();
        if(1 == length)
            return triangle.get(0).get(0);
        int dp[][] = new int[length][length];
        dp[0][0] = triangle.get(0).get(0);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                int left = j - 1 < 0 ? Integer.MAX_VALUE : dp[i - 1][j - 1];
                int right = j > i - 1 ? Integer.MAX_VALUE : dp[i - 1][j];
                dp[i][j] = Math.min(left, right) + triangle.get(i).get(j);

                if(i == length - 1){
                    res = Math.min(res, dp[i][j]);
                }
            }
        }

        return res;
    }

    public static int minimumTotal2(List<List<Integer>> triangle) {
        int length = triangle.size();
        if(1 == length)
            return triangle.get(0).get(0);
        int dp[] = new int[length];
        dp[0] = triangle.get(0).get(0);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < length; i++) {
            for (int j = i; j >= 0; j--) {
                int left = j - 1 < 0 ? Integer.MAX_VALUE : dp[j - 1];
                int right = j > i - 1 ? Integer.MAX_VALUE : dp[j];
                dp[j] = Math.min(left, right) + triangle.get(i).get(j);

                if(i == length - 1){
                    res = Math.min(res, dp[j]);
                }
            }
        }

        return res;
    }
}
