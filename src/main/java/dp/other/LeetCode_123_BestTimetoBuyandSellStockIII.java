package dp.other;

public class LeetCode_123_BestTimetoBuyandSellStockIII {
    public static void main(String[] args) {
        int list[]  ={3,3,5,0,0,3,1,4};
        System.out.println(maxProfit(list));
    }

    //差值第一和第二大的两个单调上升区间
    public static int maxProfit(int[] prices) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        int index = 0;
        while (index < prices.length - 1) {

        }

        return first + second;
}
