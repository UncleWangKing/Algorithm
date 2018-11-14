package str.slidwindow;

public class LeetCode_209_MinimumSizeSubarraySum {
    public static void main(String[] args) {
        int list[] = {2,3,1,2,4,3};int n = 7;//2
//        int list[] = {1,2,3,4,5};int n = 11;//3
//        int list[] = {1,2,3,4,5};int n = 15;//3
//        int list[] = {1,4,4};int n = 4;//1
        System.out.println(minSubArrayLen2(n, list));
    }

    public static int minSubArrayLen(int s, int[] nums) {
        int index = 0, sum = 0, left = 0, global = nums.length + 1;
        while (index < nums.length){
            sum += nums[index];
            while (left <= index && sum >= s){
                global = Math.min(index - left + 1, global);
                sum -= nums[left++];
            }
            index++;
        }

        return global == nums.length + 1 ? 0 : global;
    }

    /**
     * 累加数组 二分
     */
    public static int minSubArrayLen2(int s, int[] nums) {
        int res = Integer.MAX_VALUE, n = nums.length;
        /**
         * 累加数组漂亮写法 多一个空间就可以解决迭代初始化问题
         */
        int[] sums = new int[n + 1];
        for (int i = 1; i < n + 1; ++i) sums[i] = sums[i - 1] + nums[i - 1];
        for (int i = 0; i < n; ++i) {
            int left = i + 1, right = n + 1, t = sums[i] + s;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (sums[mid] < t) left = mid + 1;
                else right = mid;
            }
            /**
             * 必要的防越界判断
             */
            if (left == n + 1) break;
            res = Math.min(res, left - i);
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
