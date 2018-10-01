package array.sub;

public class LeetCode_713_SubarrayProductLessThanK {
    public static void main(String[] args) {
//        int list[] = {10,5,2,6};//100 -- 8
        int list[] = {1,2,3};//0 -- 0
//        int list[] = {1,1,1};//1 -- 0
        System.out.println(numSubarrayProductLessThanK(list, 1));
    }

    /**
     * nums[i]到nums[j](i < j)的乘积如果小于k 那么期间包含的以nums[j]为结尾的子数组个数为 j - i + 1
     * 见惯了套路 第一次就最优解AC 小开心
     */

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if(1 >= k) return 0;
        int count = 0, fast = 0, slow = 0;
        int temp = 1;
        while (fast < nums.length){
            temp *= nums[fast];
            while (temp >= k){
                temp /= nums[slow];
                slow++;
            }
            count += fast - slow + 1;
            fast++;
        }
        return count;
    }

    /**
     * 超时暴力剪枝
     */
    public static int numSubarrayProductLessThanK2(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int temp = 1;
                for (int l = i; l <= j; l++) {
                    temp *= nums[l];
                    if(temp >= k)
                        break;
                }
                if(temp < k)
                    count++;
                else
                    break;
            }
        }

        return count;
    }
}
