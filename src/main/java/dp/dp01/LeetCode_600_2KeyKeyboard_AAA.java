package dp.dp01;


/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */
public class LeetCode_600_2KeyKeyboard_AAA {
    public static void main(String[] args) {
        System.out.println(minSteps_dp_lessMemory(14));
    }

    /**
     * 先找规律
     * 当n = 1时，已经有一个A了，我们不需要其他操作，返回0
     * 当n = 2时，我们需要复制一次，粘贴一次，返回2
     * 当n = 3时，我们需要复制一次，粘贴两次，返回3
     * 当n = 4时，这就有两种做法，一种是我们需要复制一次，粘贴三次，共4步，
     * 另一种是先复制一次，粘贴一次，得到AA，然后再复制一次，粘贴一次，得到AAAA，两种方法都是返回4
     * 当n = 5时，我们需要复制一次，粘贴四次，返回5
     * 当n = 6时，我们需要复制一次，粘贴两次，得到AAA，再复制一次，粘贴一次，得到AAAAAA，共5步，返回5
     *
     * 通过分析上面这6个简单的例子，我想我们已经可以总结出一些规律了，
     * 首先对于任意一个n(除了1以外)，我们最差的情况就是用n步，不会再多于n步，但是有可能是会小于n步的，
     * 比如n=6时，就只用了5步，仔细分析一下，发现时先拼成了AAA，再复制粘贴成了AAAAAA。
     * 那么什么情况下可以利用这种方法来减少步骤呢，
     * 分析发现，小模块的长度必须要能整除n，这样才能拆分。
     * 对于n=6，我们其实还可先拼出AA，然后再复制一次，粘贴两次，得到的还是5。
     * 分析到这里，我想解题的思路应该比较清晰了，
     * 我们要找出n的所有因子，然后这个因子可以当作模块的个数，
     * 我们再算出模块的长度n/i，调用递归，加上模块的个数i来更新结果res即可.
     * *******************************
     * 总的来说 结果为n的所有因子的和
     * n = a * b * b;
     * res_n = a + b + b;
     * *******************************
     */
    public static int minSteps(int n) {
        if (1 == n) return 0;
        int res = n;
        for (int i = n - 1; i > 1; --i) {
            if (n % i == 0) {
                res = Math.min(res, minSteps(n / i) + i);
            }
        }
        return res;
    }

    /**
     * 上面方法存在重复子问题
     * 例:
     *  n = a * b * b;
     * res_n = a + b + b;
     * 此刻b会被重复计算两次 使用dp去掉重复子问题
     * 状态转换方程:
     * dp[i] = min(dp[i],dp[j]+i/j)
     * 其中j是i的因子，i/j是由dp[j]到dp[i]所需步骤
     * dp[i] 初始化为 i --- 最多需要的步骤
     * dp[j] 是计算出的最优值 而不是dp[i]初始等于i那样的假设
     * 可带入i = 6 ,j = 2进行观察验证
     * d[6] = min(6, dp[2] + 6 / 2)
     */
    public static int minSteps_dp(int n) {
        int []dp = new int[n+1];
        for (int i = 2; i <= n; ++i) {
            dp[i] = i;
            for (int j = i - 1; j > 1; --j) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[j] + i / j);
                }
            }
        }
        return dp[n];
    }

    /**
     * dp空间优化
     * 状态转换方程:
     * dp[i] = min(dp[i],dp[j]+i/j)
     * 观察该例:
     * n = a * b * b ;
     * res_n = a + b + b;
     * 若
     * m = n / b = a * b;
     * res_m = res_n - b;
     * 所以只要将n变为m 并将被去掉的b 记录即可
     */
    public static int minSteps_dp_lessMemory(int n) {
        int res = 0;
        for (int i = 2; i <= n; ++i) {
            while (0 == n % i) {
                res += i;
                n /= i;
            }
        }
        return res;
    }
}
