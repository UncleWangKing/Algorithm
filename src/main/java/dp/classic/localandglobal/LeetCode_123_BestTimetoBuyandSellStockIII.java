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
                local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(diff, 0), local[i - 1][j] + diff);
                global[i][j] = Math.max(local[i][j], global[i - 1][j]);
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
