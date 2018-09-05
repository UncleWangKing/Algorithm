package dp.classic;

public class LeetCode_121_BestTimetoBuyandSellStock {
    public static void main(String[] args) {
        int list[] = {7,1,5,3,6,4};
        System.out.println(maxProfit(list));
    }

    /**
     * 假定每个位置卖出 只需得到之前的最低价即可
     */
    public static int maxProfit(int[] prices) {
        int res = 0, buy = Integer.MAX_VALUE;
        for (int price : prices) {
            buy = Math.min(buy, price);
            res = Math.max(res, price - buy);
        }
        return res;
    }

    public static int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int local = 0;
        int global = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            local = Math.max(local + prices[i + 1] - prices[i], 0);
            global = Math.max(local, global);
        }
        return global;
    }
}
