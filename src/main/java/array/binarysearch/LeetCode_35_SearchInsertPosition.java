package array.binarysearch;

/**
 * 和一般的二分稍有不同 不是找某个值是否存在 而是找插入位置 所以结果一定是有的。
 * 可以有递归循环两种写法，具体又可以根据是否将mid值放入下一次迭代分为开区间和闭区间写法。
 */
public class LeetCode_35_SearchInsertPosition {
    public static void main(String[] args) {
        int list[] = {1, 3, 5, 6};
        System.out.println(searchInsert2(list, 5));
    }

    public static int searchInsert(int[] nums, int target) {
        return binarySearch(0, nums.length - 1, nums, target);
    }

    //左闭[0，mid] 右开(mid, len-1]写法 递归
    public static int binarySearch(int begin, int end, int[] list, int target){
        if(begin == end)//和下方不同 end一开始是length - 1
            return target > list[begin] ? begin + 1 : begin;

        int mid = (end + begin) / 2;

        if (target <= list[mid])// <= 而不是 < -- 因为左方是 闭区间 相等的情况要包含进左方
            return binarySearch(begin, mid, list, target);
        else
            return binarySearch(mid + 1, end, list, target);
    }

    //左开 [0, mid) 右开(mid, len-1] 写法 循环
    public static int searchInsert2(int[] nums, int target) {
        int i = 0,j = nums.length;
        //因为左开 [0, mid)，i==j的时候，迭代就已经完成了
        while(i < j){
            int mid = (i + j) / 2;
            if(nums[mid] == target)//左右开的写法 必须要有这个返回
                return mid;
            if(target < nums[mid])//< 而不是 <= -- 左右开
                //因为左开 [0, mid)的写法target < nums[mid] 所以即使j==mid,mid也刚好没有取到
                j = mid;
            else
                //因为右开(mid, len-1] ，为了target == nums[mid]时不取到mid，所以mid + 1
                i = mid + 1;
        }
        return j;
    }
}
