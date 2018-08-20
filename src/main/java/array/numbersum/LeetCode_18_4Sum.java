package array.numbersum;

import java.util.*;

public class LeetCode_18_4Sum {
    public static void main(String[] args) {
        int list[] = {-5,-5,-5,-5,-3,-3,-3,3,3,3,5,5,5};
        System.out.println(fourSum(list, 0));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; ++i) {
            //排重关键
            if (i > 0 && nums[i] == nums[i-1]) continue;
            for (int j = i + 1; j < nums.length - 2; ++j) {
                //排重关键
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(new Integer[]{nums[i], nums[j], nums[left], nums[right]}));
                        //排重关键
                        while (left < right && nums[left] == nums[left + 1]) ++left;
                        while (left < right && nums[right] == nums[right - 1]) --right;
                        ++left; --right;
                    } else if (sum < target) ++left;
                    else --right;
                }
            }
        }
        return res;
    }
}
