package str.subsequence;

public class LeetCode_730_CountDifferentPalindromicSubsequences {
    public static void main(String[] args) {
        System.out.println(countPalindromicSubsequences("bccb"));
    }

    /**
     *   b c c b
     * b 1 2 3 6
     * c 0 1 2 3
     * c 0 0 1 2
     * b 0 0 0 1
     */
    public static int countPalindromicSubsequences(String S) {
        int n = S.length(), M = 1000000007;
        int [][]dp = new int[n][n];
        for (int i = 0; i < n; ++i) dp[i][i] = 1;
        for (int len = 1; len < n; ++len) {
            for (int i = 0; i < n - len; ++i) {
                int j = i + len;
                if (S.charAt(i) == S.charAt(j)) {
                    /**
                     * 这三句本质是为了求中间有多少个和左右两边相等的个数
                     * 使用双指针往中间移动到第一个等于两边数的情况
                     * left > right 表示没有重复 这种情况left能走到最右 right能走到最左
                     * left == right 表示只有一个重复 left right都在中间停下了
                     * left < right 表示至少2个重复
                     */
                    int left = i + 1, right = j - 1;
                    while (left <= right && S.charAt(left) != S.charAt(i)) ++left;
                    while (left <= right && S.charAt(right) != S.charAt(i)) --right;

                    if (left > right) {
                        /**
                         * 假设为b 无重复 那么等于中间个数 * 2 + 2
                         * 乘2是因为中间任意回文子序列都可以和首位加上b组成新的
                         * +2是因为内部没有b 所以b和bb都是新的
                         */
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    } else if (left == right) {
                        /**
                         * 假设为b 重复1个 那么等于中间个数 * 2 + 1
                         * 乘2是因为中间任意回文子序列都可以和首位加上b组成新的
                         * +1是因为内部有b 只有bb是新的
                         */
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    } else {
                        /**
                         * 假设为b 重复2个以上 那么等于中间个数 * 2 - 内部bb的首位组成的串的个数
                         * 乘2是因为中间任意回文子序列都可以和首位加上b组成新的
                         * -内部bb的首位组成的串的个数 去重 (b和bb都被内部bb的首位组成的串算上了 也就不用+1 +2)
                         */
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[left + 1][right - 1];
                    }
                } else {
                    /**
                     * 不相等 那么等于
                     * 右边少一个
                     * 加上左边少一个
                     * "减去"左右各少一个(最后这个减去 也是去重操作)
                     */
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                }
                /**
                 * 这是个漂亮的取模操作 将有的情况直接用+代替了%
                 */
                dp[i][j] = (dp[i][j] < 0) ? dp[i][j] + M : dp[i][j] % M;
            }
        }
        return dp[0][n - 1];
    }
}
