package array.simulation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/27 18:29
 */
public class LeetCode_697_DegreeofanArray {
    public static void main(String[] args) {
        int listp[] = {1, 2, 2, 3, 1};

        System.out.println(findShortestSubArray(listp));
    }

    public static int findShortestSubArray(int[] nums) {
        int n = nums.length, res = Integer.MAX_VALUE, degree = 0;
        Map<Integer, Integer> m = new HashMap<>();
        Map<Integer, Integer> startIdx = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            m.put(nums[i], m.getOrDefault(nums[i], 0) +1);
            if (!startIdx.containsKey(nums[i])) startIdx.put(nums[i], i);
            if (m.get(nums[i]) == degree) {
                res = Math.min(res, i - startIdx.get(nums[i]) + 1);
            } else if (m.get(nums[i]) > degree) {
                res = i - startIdx.get(nums[i]) + 1;
                degree = m.get(nums[i]);
            }
        }
        return res;
    }
}
