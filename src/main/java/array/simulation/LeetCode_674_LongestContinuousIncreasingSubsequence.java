package array.simulation;

public class LeetCode_674_LongestContinuousIncreasingSubsequence {
    public static void main(String[] args) {
        int list[] = {1};
        System.out.println(findLengthOfLCIS(list));
    }

    public static int findLengthOfLCIS(int[] nums) {
        int maxLength = 0;
        int temp = 1;
        for (int i = 0; i < nums.length; i++) {
            if(i + 1 < nums.length && nums[i] < nums[i + 1])
                temp++;
            else
                temp = 1;
            maxLength = Math.max(temp, maxLength);
        }

        return maxLength;
    }
}
