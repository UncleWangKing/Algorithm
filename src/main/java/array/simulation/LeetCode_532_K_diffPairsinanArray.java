package array.simulation;

import java.util.Arrays;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/19 11:28
 */
public class LeetCode_532_K_diffPairsinanArray {
    public static void main(String[] args) {
        int []list = {3,1,4,1,5};
        System.out.println(findPairs(list, 2));
    }

    public static int findPairs(int[] nums, int k) {
        if(k < 0) return 0;
        Arrays.sort(nums);
        int slow = 0, fast = 1, count = 0;

        while(fast < nums.length){
            if(nums[fast] - nums[slow] < k)
                fast++;
            else if(nums[fast] - nums[slow] > k)
                slow++;
            else {
                do slow++;
                while (slow < nums.length && nums[slow] == nums[slow - 1]);//解决重复项问题 fast的增加在下面的if中解决
                count++;
            }
            if(slow >= fast)
                fast = slow + 1;
        }
        return count;
    }
}
