package binarysearch;

import util.ZDaPangArrayUtil;

import java.util.*;

public class LeetCode_349_IntersectionOfTwoSrrays {
    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1}, nums2 = {2,2};
        ZDaPangArrayUtil.printArray(intersection(nums1, nums2));
    }

    /**
     * 将短数组排序 长数组二分查找
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        if(0 == nums1.length || 0 == nums2.length)
            return new int[]{};
        if(nums1.length > nums2.length)
            return intersection(nums2, nums1);
        Arrays.sort(nums1);
        Set<Integer> list = new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
            int left = 0, right = nums1.length - 1;
            while (left != right){
                int mid = left + (right - left) / 2;
                if(nums2[i] > nums1[mid])
                    left = mid + 1;
                else
                    right = mid;
            }
            if(nums1[left] == nums2[i])
                list.add(nums2[i]);
        }
        int[] res = new int[list.size()];
        int index = 0;
        for (Integer i : list) {
            res[index++] = i;
        }
        return res;
    }

    /**
     * 妙不可言 桶排序 但是空间消耗大
     */
    public static int[] intersection2(int[] nums1, int[] nums2) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        // 求出数组nums1中最大值 与最小值
        for (int i = 0; i < nums1.length; i++) {
            max = max < nums1[i] ? nums1[i] : max;
            min = min > nums1[i] ? nums1[i] : min;
        }
        // 标记
        boolean[] booleanArray = new boolean[max - min + 1];
        for (int i = 0; i < nums1.length; i++) {
            booleanArray[nums1[i] - min] = true;
        }
        int index = 0;
        int[] tempArray = new int[nums2.length];
        for (int i = 0; i < nums2.length; i++) {
            // 找出nums2中的元素，标记改为false
            if (!(nums2[i] < min) && !(nums2[i] > max) && booleanArray[nums2[i] - min]) {
                booleanArray[nums2[i] - min] = false;
                // 找出相同的数据
                tempArray[index++] = nums2[i];
            }
        }
        // 复制
        return Arrays.copyOf(tempArray, index);
    }
}
