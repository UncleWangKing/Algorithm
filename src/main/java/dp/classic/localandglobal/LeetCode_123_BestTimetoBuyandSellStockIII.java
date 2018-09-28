package dp.classic.localandglobal;

import util.ZDaPangArrayUtil;

public class LeetCode_123_BestTimetoBuyandSellStockIII {
    public static void main(String[] args) {
        int list[]  ={1,3,2,4,0,5};
        System.out.println(maxProfit5(list));
    }

    //dp
    public static int maxProfit(int[] prices) {
        if (0 == prices.length) return 0;
        int maxBuyTime = 2;
        int n = prices.length, global[][] = new int[n][maxBuyTime + 1], local[][] = new int[n][maxBuyTime + 1];
        for (int i = 1; i < prices.length; ++i) {
            int diff = prices[i] - prices[i - 1];
            for (int j = 1; j <= 2; ++j) {
                /**
                 * 注意 local[i][j] 才是表示必须在第i天卖出
                 * global[i][j] 表示第i天时的最优结果 并不一定在第i天卖出
                 * 所以 global[i - 1][j - 1] + Math.max(diff, 0) 这个式子意思是 想取到 i天比i - 1天有上升，
                 * 并且i - 1比i - 2或之前的数有下降的情况，增加一次 i - 1 到 i 的交易就能取到
                 * 但是如果是global[i - 1][j - 1]是等于local[i - 1][j - 1]也就是i - 1天卖出的时候，即使上升，
                 * 也没法通过增加交易次数来吃掉这个上升，因为只多出一个值，买卖至少要两个值
                 * 而这个遗憾被local[i - 1][j] + diff 弥补了
                 * 递推式解决dp 就是靠分类讨论 保证你的分类是完备的就好 方程可以多样
                 */
                local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(diff, 0), local[i - 1][j] + diff);//两个max 四种情况
                global[i][j] = Math.max(local[i][j], global[i - 1][j]);//当前局部的最优或者过去的全局最优
            }
        }
        ZDaPangArrayUtil.printArray2(local);
        System.out.println("---------------");
        ZDaPangArrayUtil.printArray2(global);
        System.out.println("---------------");
        return global[n - 1][maxBuyTime];
    }

    //dp空间压缩
    public static int maxProfit2(int[] prices) {
        if (0 == prices.length) return 0;
        int maxBuyTime = 2;
        int global[] = new int[maxBuyTime + 1];
        int local[] = new int[maxBuyTime + 1];
        for (int i = 0; i < prices.length - 1; ++i) {
            int diff = prices[i + 1] - prices[i];
            for (int j = 2; j >= 1; --j) {
                local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
                global[j] = Math.max(local[j], global[j]);
            }
        }
        return global[2];
    }
    //买两次
    public static int maxProfit3(int[] prices) {
        if(prices == null || prices.length<=1)
            return 0;
        //只买一次
        int[] profiles = new int[prices.length];
        int maxValue = 0;
        int small = prices[0];
        for(int i=0;i<prices.length;i++){
            if(prices[i] < small){
                profiles[i] = 0;
                small = prices[i];
            }else{
                maxValue = Math.max(maxValue,prices[i] - small);
            }
            profiles[i] = maxValue;
        }

        //在一次的基础之上买第二次
        int currentMax = prices[prices.length-1];
        for(int i=prices.length-2;i>0;i--){
            if(prices[i] > currentMax)
                currentMax = prices[i];
            else
                maxValue = Math.max(maxValue,currentMax - prices[i] + profiles[i-1]);
        }

        return maxValue;
    }
    //最优雅

    /**
     * 四个变量，分别表示第一次买完，第一次卖完，第二次买完，第二次卖完后手上的钱。
     * 那么转移就很好写了，每次操作完都要保证手上的钱最多，
     * b1为之前的值和买当前股票的最大值。
     * s1为s1和卖掉股票+b1的最大值。
     * b2、s2以此类推。
     */
    public static int maxProfit5(int[] prices) {
        int b1 = Integer.MIN_VALUE,b2 = Integer.MIN_VALUE;
        int s1 = 0,s2 = 0;
        for(int i = 0; i < prices.length; i++){
            b1 = Math.max(b1, 0  - prices[i]);
            s1 = Math.max(s1, b1 + prices[i]);
            b2 = Math.max(b2, s1 - prices[i]);
            s2 = Math.max(s2, b2 + prices[i]);
        }
        return s2;
    }
}
