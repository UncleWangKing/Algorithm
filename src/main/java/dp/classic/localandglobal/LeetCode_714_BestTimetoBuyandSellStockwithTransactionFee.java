package dp.classic.localandglobal;


/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/28 17:31
 */
public class LeetCode_714_BestTimetoBuyandSellStockwithTransactionFee {
    public static void main(String[] args) {
//        int[] list = {1, 3, 2, 8, 4, 9};//fee 2 -- 8
//        int[] list = {2,1,4,4,2,3,2,5,1,2};//fee 1 -- 4
        int[] list = {2,2,1,1,5,5,3,1,5,8};//fee 2 -- 4
        System.out.println(maxProfit(list, 2));
    }

    /**
     * 惊奇但是用的思路
     * 之前的操作都是以股票价格表为核心思考
     * 这里以手中钱为核心思考
     *
     * hold[i]代表第i天持有股票的手中钱最大值
     * sold[i]代表第i天卖出股票的手中钱最大值
     *
     * sold[i] = Math.max(sold[i - 1], hold[i - 1] + prices[i] - fee);
     * 第i天卖出，手中钱最大值，要么等于昨天卖出的最大钱sold[i - 1]，要么等于昨天持有的成本hold[i - 1] + 今天卖出的收益prices[i] - fee
     *
     * hold[i] = Math.max(hold[i - 1], sold[i - 1] - prices[i]);
     * 第i天持有，手中钱最大值，要么等于昨天持有的最大值hold[i - 1]，要么等于昨天卖出的最大值sold[i - 1] - prices[i]今天持有的代价
     */
    public static int maxProfit(int[] prices, int fee) {
        int[] sold = new int[prices.length];
        int[] hold = new int[prices.length];
        hold[0] = -prices[0];
        for (int i = 1; i < prices.length; ++i) {
            sold[i] = Math.max(sold[i - 1], hold[i - 1] + prices[i] - fee);
            hold[i] = Math.max(hold[i - 1], sold[i - 1] - prices[i]);
        }
        return sold[sold.length - 1];
    }
    //滚动数组
    public static int maxProfit2(int[] prices, int fee) {
        int sold = 0, hold = -prices[0];
        for (int price : prices) {
            int t = sold;
            sold = Math.max(sold, hold + price - fee);
            hold = Math.max(hold, t - price);
        }
        return sold;
    }
}
