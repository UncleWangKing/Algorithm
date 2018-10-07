package array.classic;

import java.util.Arrays;
import java.util.Stack;

public class LeetCode_768_MaxChunksToMakeSortedII {
    public static void main(String[] args) {
        int list[] = {2,1,3,4,4};//4
        System.out.println(maxChunksToSorted3(list));
    }

    /**
     * 利用块内值和相同
     * 有序 块内n个元素 迭代过程中 会使sum1 sum2相等n次
     * 无序 一个完整块sum1 sum2只会相等一次
     */
    public static int maxChunksToSorted(int[] arr) {
        int res = 0, sum1 = 0, sum2 = 0;
        int [] expect = arr;
        Arrays.sort(expect);
        for (int i = 0; i < arr.length; ++i) {
            sum1 += arr[i];
            sum2 += expect[i];
            if (sum1 == sum2) ++res;
        }
        return res;
    }

    /**
     *  当前位置出现过的最大值小于等于之后还未遍历到的最小值时
     *  说明当前位置是一个独立块儿的最后一个元素
     *  arr被就地取材放rightMin了 因为最后的循环不需要arr
     */
    public static int maxChunksToSorted2(int[] arr) {
        int res = 1, n = arr.length;
        int [] leftMax = arr.clone();
        for (int i = 1; i < n; ++i) leftMax[i] = Math.max(arr[i], leftMax[i - 1]);
        for (int i = n - 2; i >= 0; --i) arr[i] = Math.min(arr[i], arr[i + 1]);
        for (int i = 0; i < n - 1; ++i)
            if (leftMax[i] <= arr[i + 1]) ++res;

        return res;
    }

    /**
     * 单调栈
     * 和解法2 类似
     * 每一个独立块儿有一个最大值和最小值
     * 可随意记录最大值 最小值的数量
     * 这里记录最大值数量  ---- grandyang博客这里没有看清这一层 算是超过了大佬当时的思维？
     * 这样最大值的存在数量 也就是块儿的数量了
     * [2,1,3,4,4] 为例
     * 2
     * 2
     * 2 3
     * 2 3 4
     * 2 3 4 4
     */
    public static int maxChunksToSorted3(int[] arr) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < arr.length; ++i) {
            if (st.isEmpty() || st.peek() <= arr[i]) {
                st.push(arr[i]);
            } else {
                int curMax = st.pop();
                while (!st.empty() && st.peek() > arr[i]) st.pop();
                st.push(curMax);
            }
        }
        return st.size();
    }
}
