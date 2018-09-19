package array.simulation;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/18 18:38
 */
public class LeetCode_643_MaximumAverageSubarrayI {
    public static void main(String[] args) {
        int list[] = {1,12,-5,-6,50,3};
        System.out.println(findMaxAverage(list, 4));
    }

    public static double findMaxAverage(int[] nums, int k) {
        double local = 0;
        double global = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if(i >= k){
                global = Math.max(global, local);
                local -= nums[i - k];
            }
            local += nums[i];
        }

        return Math.max(global, local) / (double)k;//解决最后一次未统计的local
    }
}
