package array.binarysearch;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_169_MajorityElement {
    public static void main(String[] args) {
        int list[] = {2,3,2};
        System.out.println(majorityElement(list));
    }

    /**
     * 漂亮的分治
     */
    public static int majorityElement(int[] nums) {
        return   majorityElement( nums,0,nums.length-1) ;
    }

    public static int majorityElement(int[] nums,int start,int end) {
        if(start == end)
            return nums[start];

        int half = (start + end) >>> 1;
        int left = majorityElement(nums, start,  half);
        int right = majorityElement(nums, half + 1,  end);
        if(left == right)
            return left;

        int count = 0;
        int count1 = 0;
        for(int i = start; i <= end; i++){
            if(nums[i] == left)
                count++;
            if(nums[i] == right)
                count1++;
        }
        return count > count1 ? left : right;
    }

    /**
     * 摩尔投票法
     */
    public static int majorityElement2(int[] nums,int start,int end) {
        int res = 0, cnt = 0;
        for (int num : nums) {
            if (cnt == 0) {res = num; ++cnt;}
            else if (num == res) ++cnt;
            else --cnt;
        }
        return res;
    }

    /**
     * hashmap
     */
    public static int majorityElement2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++)
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() > nums.length >> 1)
                return entry.getKey();
        }
        return 0;
    }
}
