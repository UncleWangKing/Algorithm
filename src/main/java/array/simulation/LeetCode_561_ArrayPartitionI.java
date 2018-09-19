package array.simulation;

import java.util.Arrays;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/19 17:33
 */
public class LeetCode_561_ArrayPartitionI {
    public static void main(String[] args) {
        int list[] = {1,1};
        System.out.println(arrayPairSum(list));
    }
    //n对不是两对 别看错了 排序取间隔就好
    public static int arrayPairSum(int[] nums) {
        int result = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i+=2)
            result += nums[i];

        return result;
    }
}
