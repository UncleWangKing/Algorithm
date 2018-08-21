package array.binarysearch;

public class LeetCode_35_SearchInsertPosition {
    public static void main(String[] args) {
        int list[] = {1, 3, 5, 6};
        System.out.println(searchInsert2(list, 5));
    }

    public static int searchInsert(int[] nums, int target) {
        return binarySearch(0, nums.length - 1, nums, target);
    }

    //码是人家的香 [0,len)开区间 循环
    public static int searchInsert2(int[] nums, int target) {
        //[0,len)开区间
        int i = 0,j = nums.length;
        //因为是前闭后开，i=j的时候，其实就是没有意义了
        while(i < j){
            int mid = (i + j) / 2;
            if(nums[mid] == target)
                return mid;
            if(target < nums[mid])
                j = mid;//因为开区间，刚好没有取到mid
            else
                i = mid + 1;//闭区间，为了不取到mid，只能+1
        }
        return j;
    }

    //就是个二分 [0，len-1] 闭区间 递归
    public static int binarySearch(int begin, int end, int[] list, int target){
        if(begin == end)
            return target > list[begin] ? begin + 1 : begin;

        int mid = (end + begin) / 2;

        if (target <= list[mid])// <= 而不是 < [闭区间]
            return binarySearch(begin, mid, list, target);
        else
            return binarySearch(mid + 1, end, list, target);
    }
}
