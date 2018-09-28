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
     * 模拟思路，走一遍
     * 从左到右找出上升区间 并判断是否值得交易
     * 记录两个点:
     * 1.假定买入点下标
     * 2.经过的最高价的点下标
     * 遇到三种情况的时候开始判断并结算:
     * 1.出现'累计'大于fee的下降，因为'累计'小于fee的下降不值得一次额外交易
     * 2.比买入点价格下降大于fee的点，因为势必切换买入点，前面不管多少利润都结算，同样需要大于fee才值得 -- 这种情况被情况1涵盖
     * 3.最后一个点
     * 结算方式:
     * 区间最高价 - 假定买入点
     */
//    public static int maxProfit(int[] prices, int fee) {
//        int max = 0;
//        int buyIndex = 0;
//        int localMaxIndex = 0;
//        for (int i = 0; i < prices.length - 1; i++) {
//            if(prices[i + 1] <= prices[i])
//                buyIndex = localMaxIndex = i + 1;
//            else
//                break;
//        }
//        for (int i = buyIndex; i < prices.length - 1; i++) {
//            int temp = prices[i + 1] - prices[i];
//            if(temp > 0)//更新区间最大值下标
//                localMaxIndex = i + 1;
//            if(prices[i + 1] < prices[localMaxIndex] - fee || i + 1 == prices.length - 1) {//情况1 || 3
//                max += Math.max(prices[localMaxIndex] - prices[buyIndex] - fee, 0);
//                buyIndex = localMaxIndex = i + 1;
//            }
//
//        }
//
//        return max;
//    }
    public static int maxProfit(int[] prices, int fee) {
        int sold = 0, hold = -prices[0];
        for (int price : prices) {
            int t = sold;
            sold = Math.max(sold, hold + price - fee);
            hold = Math.max(hold, t - price);
        }
        return sold;
    }
}
