package dp.other;

public class LeetCode_718_MaximumLengthofRepeatedSubarray {
    public static void main(String[] args) {
        int A[] = {1,2,3,2,1};
        int B[] = {3,2,1,4,7};
        System.out.println(findLength(A, B));
    }

    /**
     * 和LCS类似 但有区别的dp
     */
    public static int findLength(int[] A, int[] B) {
        int res = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 1; i < dp.length; ++i) {
            for (int j = 1; j < dp[i].length; ++j) {
                dp[i][j] = (A[i - 1] == B[j - 1]) ? dp[i - 1][j - 1] + 1 : 0;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
