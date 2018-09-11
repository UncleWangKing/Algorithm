package dp.classic.localandglobal;

public class LeetCode_121_BestTimetoBuyandSellStock {
    public static void main(String[] args) {
        int list[] = {1,3,2,4,0,5};
        System.out.println(maxProfit2(list));
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
    //local的定义非常巧妙~ 代表着以当前位置卖出所获的收益
    //  -- 买入位置就是i之前的最小值的位置。再次强调local是'当前位置'卖出的'最大'利润。
    //'当前位置'的体现很明显
    //'最大'自然是以之前的最小值买入，
    //可用[1,3,2,4,0,5]这一个输入样例来调试即可观察出local变化方式
    //一开始是以0位置买入，1卖出。
    //可以观察到，只要卖出位置的价格不低于等于买入价，买入位置是不变的
    //当然变了也不存买入位置，因为不需要知道买入位置，只需要知道以i之前最低价格的位置买入时local的值即可，只需要清零，local值即可等效到新的买入位置。
    //由我们的迭代方式可得，这个买入位置一定是i之前最小的值得位置。
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
