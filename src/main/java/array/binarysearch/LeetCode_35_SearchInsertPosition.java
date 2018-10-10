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
        return binarySearch(0, nums.length, nums, target);
    }

    //左闭右闭[begin，mid] 递归
    public static int binarySearch(int begin, int end, int[] list, int target){
        if(begin == end)
            return begin;

        int mid = begin + (end - begin) / 2;//防溢出 或者 无符号右移 (end + begin) >>> 2

        if (target < list[mid])
            return binarySearch(begin, mid, list, target);
        else if(target > list[mid])
            return binarySearch(mid + 1, end, list, target);
        else
            return mid;//题目说明无重复 相等直接剪枝
    }

    //左闭右闭[begin，mid] 循环
    public static int searchInsert2(int[] nums, int target) {
        int begin = 0, end = nums.length;
        //因为左开 [0, mid)，i==j的时候，迭代就已经完成了
        while(begin < end){
            int mid = begin + (end - begin) / 2;
            if(target < nums[mid])
                end = mid;
            else if(target > nums[mid])
                begin = mid + 1;
            else
                return mid;//题目说明无重复 相等直接剪枝
        }
        return end;
    }
}
