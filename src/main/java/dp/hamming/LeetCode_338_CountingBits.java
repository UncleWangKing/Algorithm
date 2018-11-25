package dp.hamming;

import util.ZDaPangArrayUtil;

public class LeetCode_338_CountingBits {
    public static void main(String[] args) {
        ZDaPangArrayUtil.printArray(countBits(5));
    }

    /**
     * 正常dp 奇偶区分
     */
    public static int[] countBits(int num) {
        int []dp = new int[num + 1];
        for (int i = 1; i <= num; ++i) {
            if (i % 2 == 0) dp[i] = dp[i / 2];
            else dp[i] = dp[i / 2] + 1;
        }
        return dp;
    }

    /**
     * 找规律之奇巧淫技
     * i & (i - 1)是一个比i小且少一个1的数
     * 比i少好说 为何少一个0
     * i & (i - 1)本质的意思是 删掉最右侧的一个1
     * 参考汉明距离191这道题
     */
    public static int[] countBits2(int num) {
        int []dp = new int[num + 1];
        for (int i = 1; i <= num; ++i) {
            dp[i] = dp[i & (i - 1)] + 1;
        }
        return dp;
    }
}
