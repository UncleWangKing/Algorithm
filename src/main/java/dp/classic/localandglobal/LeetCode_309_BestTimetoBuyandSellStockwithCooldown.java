package dp.classic.localandglobal;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/29 13:56
 */
public class LeetCode_309_BestTimetoBuyandSellStockwithCooldown {
    public static void main(String[] args) {
        int [] list = {1,2,3,0,2};
        System.out.println(maxProfit(list));
    }

    public static int maxProfit(int[] prices) {
        int buy = Integer.MIN_VALUE, pre_buy = 0, sell = 0, pre_sell = 0;
        for (int price : prices) {
            pre_buy = buy;
            buy = Math.max(pre_sell - price, pre_buy);
            pre_sell = sell;
            sell = Math.max(pre_buy + price, pre_sell);
        }
        return sell;
    }

    /**
     (动态规划) O(n)O(n)
     设计状态
     f[i]表示第i天，当前不持有股票且当前没有发生卖出交易的最大收益；
     g[i]表示第i天，不持有股票，且当前刚刚卖出股票的最大收益；
     h[i]表示当前持有股票的最大收益。
     状态转移为
     f[i] = max(f[i - 1], g[i - 1])，表示构成第i天不持有股票且当天无交易有两种方式，一种是前一天也不持有且前一天没有卖出交易，另一种是前一天持有且前一天刚刚卖出股票；二者取最大值。
     g[i] = h[i - 1] + prices[i]，表示构成第i天不持有股票且当天有交易仅有一种方式，即当天卖出前一天持有的股票。
     h[i] = max(h[i - 1], f[i - 1] - prices[i])，表示构成第i天持有股票有两种方式，一种是前一天持有，另一种是前一天不持有且前一天无交易，但这一天刚刚买入。
     最终答案为max(f[n - 1], g[n - 1])，即最后一天不持有股票的两种情况
     **/
    public static int maxProfit2(int[] prices) {
        if (prices == null || prices.length <= 1){
            return 0;
        }
        int n = prices.length;
        int[] f = new int[n];
        int[] g = new int[n];
        int[] h = new int[n];
        f[0] = 0;
        g[0] = 0;
        h[0] = -prices[0];
        for (int i = 1; i < n; i++){
            f[i] = Math.max(f[i - 1],g[i - 1]);
            g[i] = h[i - 1] + prices[i];
            h[i] = Math.max(h[i - 1], f[i - 1] - prices[i]);
        }
        return Math.max(f[f.length - 1], g[g.length - 1]);
    }

    public static int maxProfit3(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;

        int n = prices.length;
        int[] hold = new int[n];
        int[] unhold = new int[n];
        hold[0] = - prices[0];
        for (int i = 1; i < n; i++){
            if (1 == i){
                hold[i] = Math.max(hold[i - 1], -prices[1]);
            }else{
                hold[i] = Math.max(hold[i - 1], unhold[i - 2] - prices[i]);
            }
            unhold[i] = Math.max(unhold[i - 1], hold[i - 1] + prices[i]);
        }

        return unhold[n-1];
    }
}
