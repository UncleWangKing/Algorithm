package array.simulation;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/18 17:59
 */
public class LeetCode_485_MaxConsecutiveOnes {
    public static void main(String[] args) {
        int list[] = {1,1,0,1,1,1};
        System.out.println(findMaxConsecutiveOnes(list));
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int local = 0;
        int global = 0;
        for (int i = 0; i < nums.length; i++) {
            if(0 == nums[i])
                local = 0;
            else
                local++;
            global = Math.max(local, global);
        }

        return global;
    }
}
