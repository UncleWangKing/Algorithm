package array.binarysearch;

public class LeetCode_33_SearchinRotatedSortedArray {
    public static void main(String[] args) {
        int list[] = {3,1};
        System.out.println(search(list, 1));
    }

    public static int search(int[] nums, int target) {
        return binarySearch2(0, nums.length - 1, nums, target);
    }

    //循环比递归快很多
    public static int binarySearch2(int begin, int end, int[] list, int target) {
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (list[mid] == target) return mid;
            if (list[begin] <= list[mid]) {
                if (list[begin] <= target && list[mid] >= target)
                    end = mid - 1;
                else begin = mid + 1;
            }
            else {
                if (list[mid] <= target && list[end] >= target)
                    begin = mid + 1;
                else end = mid - 1;
            }
        }
        return -1;
    }

    //区分单调的情况即可
    public static int binarySearch(int begin, int end, int[] list, int target){
        if(begin == end)
            return target == list[begin] ? begin : -1;
        if(begin > end)
            return -1;

        int mid = (end + begin) / 2;

        if(target == list[mid])
            return mid;

        if (target < list[mid]) {//比中间值小 本应往左
            if(target < list[begin] && list[begin] <= list[mid])//左边没有比value小值 才往右
                return binarySearch(mid + 1, end, list, target);
            else
                return binarySearch(begin, mid - 1, list, target);
        } else {//大于中间值 本应往右
            if(target > list[end] && list[mid] <= list[end])//最右没有值比value大的值 才往左
                return binarySearch(begin, mid - 1, list, target);
            else
                return binarySearch(mid + 1, end, list, target);
        }
    }
}
