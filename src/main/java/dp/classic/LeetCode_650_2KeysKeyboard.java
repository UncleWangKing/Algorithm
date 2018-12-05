package dp.classic;

public class LeetCode_650_2KeysKeyboard {
    public static void main(String[] args) {
        System.out.println(minSteps(16));
    }

    /**
     * n为最大步骤数(重复复制粘贴'A')
     * 递归 找约数(最大或者最小的都可以~ 因为最大和最小本质是一样的步骤数) i 步骤为i + minSteps(n / i)
     * 初始值res = n 解决了n是质数的情况
     */
    public static int minSteps(int n) {
        if (n == 1) return 0;
        int res = n;
        for (int i = 2; i < n / 2; ++i) {
            if (n % i == 0) {
                res = Math.min(res, minSteps(n / i) + i);
            }
        }
        return res;
    }

    /**
     * 递归改为循环 将n纳入迭代
     * 注意 i < n / 2 这个条件变成了 i <= n
     * 因为没有res的默认值来解决n是质数的情况
     */
    public static int minSteps2(int n) {
        int res = 0;
        for (int i = 2; i <= n; ++i) {
            while (n % i == 0) {
                res += i;
                n /= i;
            }
        }
        return res;
    }

    /**
     * dp 属于强行dp
     * 因为没有重复子项 所以上面的递归就很快了
     * 这里找的最大约数
     */
    public static int minSteps3(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; ++i) {
            dp[i] = i;
            for (int j = 2; j < i; ++j) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[j] + i / j);
                }
            }
        }
        return dp[n];
    }
}
