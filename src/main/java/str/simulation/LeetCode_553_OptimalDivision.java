package str.simulation;

public class LeetCode_553_OptimalDivision {
    public static void main(String[] args) {
        int[] nums = {1000,100,10,2};
        System.out.println(optimalDivision(nums));
    }

    public static String optimalDivision(int[] nums) {
        String res = "";
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (i > 0) res += "/";
            if (i == 1 && n > 2) res += "(";
            res += String.valueOf(nums[i]);
            if (i == n - 1 && n > 2) res += ")";
        }
        return res;
    }
}
