package array;

public class LeetCode_287_FindtheDuplicateNumber {
    public static void main(String[] args) {
        int list[] = {1, 3, 2, 4, 4};
        System.out.println(findDuplicate_2(list));
    }

    /**
     * 二分法:判断在这区间内的数的个数是否大于这份区间本身
     * 如果大于则一定有重复--- 鸽巢原理
     * [1, 4, 4, 2, 4]这种情况少3的情况也不必担心 因为区间内每少一个不重复数 必然多一个重复数
     */
    public static int findDuplicate(int[] nums) {
        return find(1, nums.length - 1, nums);
    }

    public static int find(int begin, int end, int[] nums) {
        if(begin == end)
            return begin;

        int count = 0;
        int mid = (end + begin) / 2;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] >= begin && nums[i] <= mid)
                count++;
        }

        boolean inLeft = count > mid - begin + 1;

        if (inLeft) {
            return find(begin, mid, nums);
        } else
            return find(mid + 1, end, nums);
    }

    public static int findDuplicate_2(int[] nums) {
        int slow = 0;
        int fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
