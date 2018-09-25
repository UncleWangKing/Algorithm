package array.simulation;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/25 13:54
 */
public class LeetCode_526_BeautifulArrangement {
    public static void main(String[] args) {
        System.out.println(10);
    }

    /**
     * 还以为有什么操作 结果就是全排列
     */
    public static int countArrangement(int N) {
        int[] nums = new int[N];
        for (int i = 0; i < N; ++i) nums[i] = i + 1;
        return helper(N, nums);
    }

    public static int helper(int n, int[] nums) {
        if (n <= 0) return 1;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (n % nums[i] == 0 || nums[i] % n == 0) {
                swap(nums, i, n - 1);
                res += helper(n - 1, nums);
                swap(nums, i, n - 1);
            }
        }
        return res;
    }

    public static void swap(int nums[], int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
