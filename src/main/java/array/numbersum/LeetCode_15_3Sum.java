package array.numbersum;

import util.ZDaPangArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_15_3Sum {
    public static void main(String[] args) {
        int list[] = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(list));
    }

    //排序+双指针
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();

        Arrays.sort(nums);
        for (int k = 0; k < nums.length; ++k) {
            //排重关键
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            //剪枝代码
            if (nums[k] > 0) break;
            //核心部分
            int target = 0 - nums[k];
            int i = k + 1, j = nums.length - 1;
            while (i < j) {
                if (nums[i] + nums[j] == target) {
                    list.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    //排重关键
                    while (i < j && nums[i] == nums[i + 1]) ++i;
                    while (i < j && nums[j] == nums[j - 1]) --j;
                    ++i; --j;
                } else if (nums[i] + nums[j] < target) ++i;
                else --j;
            }
        }

        return list;
    }
}
