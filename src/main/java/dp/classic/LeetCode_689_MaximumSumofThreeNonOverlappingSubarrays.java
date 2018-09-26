package dp.classic;

import util.ZDaPangArrayUtil;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/26 14:19
 */
public class LeetCode_689_MaximumSumofThreeNonOverlappingSubarrays {
    public static void main(String[] args) {
        int list[] = {1,2,1,2,6,7,5,1};
        ZDaPangArrayUtil.printArray(maxSumOfThreeSubarrays(list, 2));
    }

    /**
     * 针对该题的方案
     * 分成左中右三部分来解
     * 1.分别求出数组当前下标的左方和右方存在的3数和最大值
     * 2.再遍历一遍所有3数和，这个就是中间的3数和，匹配上相应的左右最大3数和
     * caution：
     * 1.使用累加数组来方便计算数组和。
     * 2.由于返回结果要求的原因，临时数组存的是数组的起始下标。
     * @param nums input array of numbers
     * @param k length of each interval
     * @return
     */
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        int[] left = new int[n];
        int[] right = new int[n];
        int[] ret = new int[3];

        // First get the prefix sum of nums.
        // Prefix sum enables us to get the sum of k consecutive element in O(1) time
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        // DP for the left intetval max sum
        for (int i = k, tot = sum[k] - sum[0]; i < n; i++) {
            if (sum[i + 1] - sum[i - k + 1] > tot) {
                tot = sum[i + 1] - sum[i - k + 1];
                left[i] = i - k + 1;
            } else {
                left[i] = left[i - 1];
            }
        }

        // DP for the right interval max sum
        right[n - k] = n - k;
        for (int i = n - 1 - k, tot = sum[n] - sum[n - k]; i >= 0; i--) {
            if (sum[i + k] - sum[i] >= tot) {
                tot = sum[i + k] - sum[i];
                right[i] = i;
            } else {
                right[i] = right[i + 1];
            }
        }

        // Find the max sum by iterating through the middle interval index based on above 2 cache.
        int maxSum = 0;
        for (int i = k; i <= n - 2 * k; i++) {
            int l = left[i - 1], r = right[i + k];
            int tot = sum[l + k] - sum[l] + sum[r + k] - sum[r] + sum[i + k] - sum[i];
            if (tot > maxSum) {
                ret[0] = l;
                ret[1] = i;
                ret[2] = r;
                maxSum = tot;
            }
        }

        return ret;
    }

    /**
     * 适用性更广的方案
     * @param nums input array of numbers
     * @param k length of each interval
     * @param n number of intervals
     * @return start index of each interval that has the maximum sum
     */
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k, int n) {
        int[][] dp = new int[n + 1][nums.length + 1];
        int[][] index = new int[n + 1][nums.length + 1];
        int tot = 0;
        // prefix sum
        int[] sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = nums[i] + sum[i];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = k - 1; j < nums.length; j++) {
                int tmpMax = sum[j + 1] - sum[j - k + 1] + dp[i - 1][j - k + 1];
                if (tmpMax > dp[i][j]) {
                    dp[i][j + 1] = tmpMax;
                    index[i][j + 1] = j - k + 1;
                } else {
                    dp[i][j + 1] = dp[i][j];
                    index[i][j + 1] = index[i][j];
                }
            }
        }

        int[] ret = new int[n];
        int prev = nums.length;
        for (int i = n; i > 0; i--) {
            ret[i - 1] = index[i][prev];
            prev = ret[i - 1];
        }

        return ret;
    }
}
