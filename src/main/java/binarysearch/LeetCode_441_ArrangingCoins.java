package binarysearch;

public class LeetCode_441_ArrangingCoins {
    public static void main(String[] args) {
        System.out.println(arrangeCoins(1804289383));
    }

    /**
     * 二分 sum = mid * (mid + 1) / 2;
     * 找的是sum大于n的第一个mid
     */
    public static int arrangeCoins(int n) {
        int left = 1, right = (int)(Math.sqrt(n) * Math.sqrt(2)) + 1;
        while (left != right){
            int mid = left + (right - left) / 2;
            long sum = (0 == (mid & 1)) ? mid / 2 * (mid + 1) :  (mid + 1) / 2 * mid;
            if(sum > n)
                right = mid;
            else if(sum < n)
                left = mid + 1;
            else
                return mid;
        }
        return left - 1;
    }

    /**
     * 求根公式
     */
    public static int arrangeCoins2(int n) {
        return (int) (Math.sqrt( 2 * (long) n + 0.25) - 0.5);
    }
}
