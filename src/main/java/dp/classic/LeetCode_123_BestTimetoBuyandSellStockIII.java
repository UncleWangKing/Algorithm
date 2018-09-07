package dp.classic;

public class LeetCode_123_BestTimetoBuyandSellStockIII {
    public static void main(String[] args) {
        int list[]  ={3,3,5,0,0,3,1,4};
        System.out.println(maxProfit(list));
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
}
