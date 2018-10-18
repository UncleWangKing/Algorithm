package dp.classic;

import java.util.Stack;

public class LeetCode_907_SumofSubarrayMinimums {
    public static void main(String[] args) {
//        int list[] = {3,1,2,4};//17
//        int list[] = {4,3,2,1};//20
        int list[] = {71,55,82,55};//593
        System.out.println(sumSubarrayMins2(list));
    }

    /**
     * 思路:统计每个数被使用的的次数
     * 因为子数组是连续的 所以一个位置上的数使用次数只和左右第一个小于自身数位置有关
     * 为res += (i - left[i]) * (right[i] - i) * A[i];
     */
    public static int sumSubarrayMins(int[] A) {
        /**
         * 取每个值的左右第一个小于自己的值 可用stack辅助
         */
        int left[] = new int[A.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < A.length; i++){
            while (!stack.empty() && A[stack.peek()] > A[i])//注意下方不同
                stack.pop();
            if(!stack.empty())
                left[i] = stack.peek();
            else
                left[i] = -1;
            stack.push(i);
        }
        stack.clear();
        int right[] = new int[A.length];
        for (int i = A.length - 1; i >= 0; i--){
            while (!stack.empty() && A[stack.peek()] >= A[i])//相等只计算一次
                stack.pop();
            if(!stack.empty())
                right[i] = stack.peek();
            else
                right[i] = A.length;
            stack.push(i);
        }

        long res = 0;
        for (int i = 0; i < A.length; i++)
            res += (i - left[i]) * (right[i] - i) * A[i];

        return (int)(res % 1000000007);
    }
    /**
     * 以每个i加入后的增量为目标dp
     * 依然用栈来辅助获取left值
     * dp[i]= dp[left]+ (i-left）* a[i]
     *
     * if(-1 != left[i])
     *  dp[i] = dp[left[i]] + A[i] * (i - left[i]);
     * else
     *  dp[i] = A[i] * (i - left[i]);
     *
     *  dp[i]代表第i个数为总值带来的增量
     *  这个增量分成两部分
     *  A[x] > A[i] , x < i的时候 将都是A[i]被加入 次数为A[i] * (i - left[i]) -- A[i]最小为时left[i]为-1
     *  A[x] <= A[i] , x < i的时候 就等于dp[A[x]]
     *  初始项dp[0] = A[0]
     *
     */
    public static int sumSubarrayMins2(int[] A) {
        /**
         * 取每个值的左右第一个小于自己的值 可用stack辅助
         */
        int left[] = new int[A.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < A.length; i++){
            while (!stack.empty() && A[stack.peek()] > A[i])
                stack.pop();
            if(!stack.empty())
                left[i] = stack.peek();
            else
                left[i] = -1;
            stack.push(i);
        }
        int dp[] = new int[A.length];
        dp[0] = A[0];
        for (int i = 1; i < A.length; i++)
            if(-1 != left[i])
                dp[i] = dp[left[i]] + A[i] * (i - left[i]);
            else
                dp[i] = A[i] * (i - left[i]);
        long sum = 0;
        for (int i = 0; i < dp.length; i++)
            sum += dp[i];

        return (int)(sum % 1000000007);
    }
}
