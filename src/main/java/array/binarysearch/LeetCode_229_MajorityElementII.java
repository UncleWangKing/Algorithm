package array.binarysearch;

import java.util.*;

public class LeetCode_229_MajorityElementII {
    public static void main(String[] args) {
//        int list[] = {1,1,1,3,3,2,2,2};//[1,2]
        int list[] = {3,2,3};//[3]
        System.out.println(majorityElement(list));
    }

    /**
     * 题目要求空间为O(1)
     */
    public static List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length / 3;
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if(map.get(nums[i]) > length && map.get(nums[i]) > 0) {
                res.add(nums[i]);
                map.put(nums[i], -1);
            }
        }
        return res;
    }
    /**
     * 摩尔投票法应用
     */
    public static List<Integer> majorityElement2(int[] nums) {
        List<Integer> res = new LinkedList<>();
        int m = 0, n = 0, countm = 0, countn = 0;
        for (int a : nums) {
            if (a == m) ++countm;
            else if (a == n) ++countn;
            else if (countm == 0) {m = a; countm = 1;}
            else if (countn == 0) {n = a; countn = 1;}
            else {--countm; --countn;}
        }
        countm = countn = 0;
        for (int a : nums) {
            if (a == m) ++countm;
            else if (a == n) ++countn;
        }
        if (countm > nums.length / 3) res.add(m);
        if (countn > nums.length / 3) res.add(n);
        return res;
    }
}
