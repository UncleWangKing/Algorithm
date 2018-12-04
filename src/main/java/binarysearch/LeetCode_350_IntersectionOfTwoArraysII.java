package binarysearch;

import util.ZDaPangArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_350_IntersectionOfTwoArraysII {
    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1}, nums2 = {2,2};
        ZDaPangArrayUtil.printArray(intersect(nums1, nums2));
    }

    /**
     * 排序后 双指针
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) { list.add(nums1[i]); i++; j++; }
            else if (nums1[i] < nums2[j]) i++;
            else j++;
        }

        int[] res = new int[list.size()];
        for (int k = 0; k < res.length; k++) {
            res[k] = list.get(k);
        }

        return res;
    }

    /**
     * 暴力也很快
     */
    public static int[] intersect2(int[] nums1, int[] nums2) {
        int[] res = new int[Math.max(nums1.length, nums2.length)];
        int len1 = 0;
        boolean[] bl1 = new boolean[res.length];
        for (int i=0; i < nums2.length; i++) {
            int start = 0;
            while( (start = find(nums1, nums2[i], start)) != -1 ) {
                if(bl1[start] == false) {
                    res[len1++] = nums2[i];
                    bl1[start] = true;
                    break;
                }
                start++;
            }
        }

        int[] ret = new int[len1];
        for (int i=0; i<len1; i++) {
            ret[i] = res[i];
        }

        return ret;
    }

    public static int find(int[] nums, int val, int i) {
        for (; i < nums.length; i++) {
            if(nums[i] == val) {
                return i;
            }
        }
        return -1;
    }
}
