package array.simulation;

public class LeetCode_747_LargestNumberAtLeastTwiceOfOthers {
    public static void main(String[] args) {
        int list[] = {3, 6, 1, 0};
        System.out.println(dominantIndex(list));
    }
    //求前2个数
    public static int dominantIndex(int[] nums) {
        int max_1 = Integer.MIN_VALUE;
        int max_2 = Integer.MIN_VALUE;
        int max_id = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] >= max_1){
                max_2 = max_1;
                max_1 = nums[i];
                max_id = i;
            }else if(nums[i] > max_2)
                max_2 = nums[i];
        }
        return max_1 >= (max_2 * 2) ? max_id : -1;
    }
}
