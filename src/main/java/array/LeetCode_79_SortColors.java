package array;

import util.ZDaPangArrayUtil;

/**
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class LeetCode_79_SortColors {
    public static void main(String[] args) {
//        int [] list = {0,0,1,0,1,1};
//        int [] list = {2,0,1};
        int [] list = {0,0,1,0,1,1};
        sortColors(list);
        ZDaPangArrayUtil.printArray(list);
    }

    /**
     * 双指针
     * 左边指针扫描 0 过 -- 1 等 -- 2 和右指针换
     * 右边指针扫描 2 过 -- 1 等 -- 0 和左指针换
     * 关键在于两边都是1的情况 选一遍开始走 这里选左开始
     * 所以左右是区别对待的 左边多一个firstOneFromLeft 记录左边被跳过的第一个1 防止后面出现0要回来换
     */
    public static void sortColors(int[] nums) {
        int left = 0,right = nums.length - 1, firstOneFromLeft = 0;
        while (left < right){
            /**
             * 针对关注的三种情况操作
             */
            if(2 == nums[left] && 0 != nums[left])//左边等于2 右边等于2也换
                swap(nums, left, right);
            else if(0 == nums[right] && 2 != nums[right]){//右边等于0 左边等于0也换
                swap(nums, firstOneFromLeft++, right);
                left++;
            }
            else if(1 == nums[left] && 1 == nums[right]){//最关键的两边都等于1的情况
                while (1 == nums[left]){//从左往右 找出第一个不等于1的数的下标
                    if(left >= right)
                        return;
                    left++;
                }
                if(2 == nums[left])
                    swap(nums, left, right);
                else if(0 == nums[left])
                    swap(nums, firstOneFromLeft++, left++);
            }
            /**
             * 每轮最后都滤掉左右方已经排好的数据
             */
            //左方发现0 不能直接跳 因为left左边是可能有1存在的 要看firstOneFromLeft情况而定
            while (0 == nums[left] && left < right) {
                if (left != firstOneFromLeft)
                    swap(nums, left, firstOneFromLeft);
                left++;
                firstOneFromLeft++;
            }
            //右方发现2 直接跳过
            while (2 == nums[right] && left < right) right--;
        }
    }
    public static void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}