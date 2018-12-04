package binarysearch;

public class LeetCode_154_FindMinimuminRotatedSortedArrayII {
    public static void main(String[] args) {
//        int list[] = {1,3};
//        int list[] = {2,2,2,0,1};
        int list[] = {2,1,2,2,2};
        System.out.println(findMin(list));
    }

    /**
     * 碰上重复就完全没办法选择左右了 只能+1去重 最坏退化到O(n)
     */
    public static int findMin(int[] nums) {
        int begin = 0, end = nums.length - 1;

        while(begin < end) {
            if(nums[begin] < nums[end])
                return nums[begin];
            int mid = (begin + end) / 2;
            if(nums[begin] < nums[mid]) {//左单调 -- 题目不存在重复
                if(nums[mid] < nums[end])
                    return nums[begin];
                else begin = mid;
            }
            else if(nums[begin] > nums[mid]){
                if(nums[mid] > nums[end])
                    return nums[end];
                end = mid;
            }
            else
                begin++;
        }

        return nums[begin];
    }
}
