package array;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/17 14:58
 */
public class LeetCode_215_KthLargestElementinanArray {
    public static void main(String[] args) {
        int []list = {3,2,1,5,6,4};
        System.out.println(findKthLargest(list, 2));
    }

    /**
     * 这题真正想要考察的思路 利用快排的思想 分治处理
     */
    public static int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        while (true){
            int pos = partition(nums, left, right);
            if(k - 1 == pos) return nums[pos];
            else if(pos > k - 1) right = pos - 1;
            else left = pos + 1;
        }
    }

    public static int partition(int[] nums, int left, int right){
        int pivot = nums[left], l = left +1, r = right;
        while (l <= r){
            if(nums[l] < pivot && nums[r] > pivot)
                swap(nums, l, r);
            if (nums[r] <= pivot) r--;
            if (nums[l] >= pivot) l++;
        }
        swap(nums, left, r);

        return r;
    }

    public static void swap(int nums[], int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /**
     * 最快解法 也是最浪费空间的解法
     */
    public static int findKthLargest2(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i:nums){
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        int[] buffer = new int[max - min + 1];
        for(int i : nums){
            buffer[i - min] += 1;
        }
        int cnt = 0;
        int i = max - min;
        for(; i >= 0; i--){
            cnt += buffer[i];
            if(cnt >= k) break;
        }
        return i + min;
    }
}
