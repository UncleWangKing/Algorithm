package binarysearch;

public class LeetCode_81_SearchinRotatedSortedArrayII {
    public static void main(String[] args) {
        int list[] = {1,3,1,1,1};
        System.out.println(search(list, 3));
    }

    /**
     * 和33题不同的是有重复就无法简单通过当前值和开头结尾的比较来确定单调区间了。
     * 所以如果是和左边比较 那么相等就begin++
     * 同理 也可以是和右边比较 相等则就end--
     */
    public static boolean search(int[] nums, int target) {
        int begin = 0, end = nums.length - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (nums[mid] == target) return true;
            if (nums[begin] < nums[mid]) {//左单调
                if (nums[begin] <= target && nums[mid] >= target)
                    end = mid - 1;
                else begin = mid + 1;
            }
            else if(nums[begin] > nums[mid]){//右单调
                if (nums[mid] <= target && nums[end] >= target)
                    begin = mid + 1;
                else end = mid - 1;
            }else
                begin++;
        }
        return false;
    }
}
