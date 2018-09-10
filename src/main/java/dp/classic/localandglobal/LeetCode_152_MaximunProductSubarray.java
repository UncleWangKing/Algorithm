package dp.classic.localandglobal;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */
public class LeetCode_152_MaximunProductSubarray {
    public static void main(String[] args) {
        int []list = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxProduct(list));
    }

    ////local and global
    public static int maxProduct(int[] nums) {
        int[] max = new int[2];
        int[] min = new int[2];
        max[0] = min[0] = nums[0];
        int global = nums[0];
        for(int i = 1; i < nums.length; i++){
            max[i % 2] = Math.max(Math.max(max[(i - 1) % 2] * nums[i], min[(i - 1) % 2] * nums[i]), nums[i]);
            min[i % 2] = Math.min(Math.min(max[(i - 1) % 2] * nums[i], min[(i - 1) % 2] * nums[i]), nums[i]);
            global = Math.max(max[i % 2], global);
        }
        return global;
    }
    /**
     * 用两个dp数组，
     * f[i]表示子数组[0, i]范围内的最大子数组乘积，
     * g[i]表示子数组[0, i]范围内的最小子数组乘积，  ---- 最小值可能是一个绝对值很大的负数 所以要考虑！
     * f[0]g[0]都初始化为nums[0]，其余都初始化为0。
     * 那么从数组的第二个数字开始遍历，
     * 那么此时的最大值和最小值只会在这三个数字之间产生，
     * f[i-1]*nums[i]，g[i-1]*nums[i]，和nums[i]。
     * 所以我们用三者中的最大值来更新f[i]，用最小值来更新g[i]，
     * 然后用f[i]来更新结果res即可
     */
    public static int maxProduct2(int[] nums) {
        int res = nums[0],n = nums.length;
        int []f = new int[n];
        int []g = new int[n];
        f[0] = nums[0];
        g[0] = nums[0];
        for (int i = 1; i < n; i++) {
            f[i] = Math.max(Math.max(f[i - 1] * nums[i], g[i - 1] * nums[i]), nums[i]);
            g[i] = Math.min(Math.min(f[i - 1] * nums[i], g[i - 1] * nums[i]), nums[i]);
            res = Math.max(res, f[i]);
        }
        return res;
    }
}
