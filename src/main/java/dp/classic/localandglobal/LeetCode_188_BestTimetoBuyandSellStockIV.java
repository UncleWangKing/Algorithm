package dp.classic.localandglobal;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/29 13:53
 */
public class LeetCode_188_BestTimetoBuyandSellStockIV {
    public static void main(String[] args) {
        int list[] = {3,2,6,5,0,3};
        System.out.println(maxProfit2(2, list));
    }

    public static int maxProfit(int k, int[] prices) {
        if (0 == prices.length) return 0;
        int maxBuyTime = Math.min(k, prices.length);
        int global[] = new int[maxBuyTime + 1];
        int local[] = new int[maxBuyTime + 1];
        for (int i = 0; i < prices.length - 1; ++i) {
            int diff = prices[i + 1] - prices[i];
            for (int j = maxBuyTime; j >= 1; --j) {
                local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
                global[j] = Math.max(local[j], global[j]);
            }
        }
        return global[maxBuyTime];
    }

    public static int maxProfit2(int k, int[] prices) {
        /**
         * 剪枝动作
         * 次数大于长度一半 由于一次买卖至少需要2个位置，所以等效成了任意次买卖，直接吃掉每一个上升区间。
         */
        if(k >= prices.length / 2) {
            int res = 0;
            for(int i = 1; i < prices.length; i++) {
                if(prices[i] > prices[i - 1]) {
                    res += prices[i] - prices[i - 1];
                }
            }
            return res;
        }
        /**
         * 核心代码 以手上的钱为核心思路来思考
         * 参考714解法1
         */
        int[] buy = new int[prices.length + 1];
        int[] sell = new int[prices.length + 1];
        for(int i = 0; i < prices.length + 1; i++) {
            buy[i] = Integer.MIN_VALUE;
            sell[i] = 0;//默认就是0 没有必要赋值 写在这为了表达思路
        }

        for(int i = 0; i < prices.length; i++) {
            for(int j = 1; j <= k; j++) {
                buy[j] = Math.max(buy[j], sell[j - 1] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);
            }
        }
        return sell[k];
    }
}
