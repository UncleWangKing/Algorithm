package dp.dp01;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */
public class LeetCode_312_BurstBalloons {
    public static void main(String[] args) {
        int []list = {3,1,5,8};
        System.out.println(maxCoins(list));
    }

    public static int maxCoins(int[] nums) {
        int[] dpnums = new int[nums.length+2];
        dpnums[0] = 1;
        dpnums[dpnums.length-1] = 1;
        for(int i=0, j=1; i<nums.length; i++, j++) dpnums[j] = nums[i];

        int[][] coins = new int[dpnums.length][dpnums.length];
        for(int i=2; i<dpnums.length; i++) {
            for(int j=0; j+i<dpnums.length; j++) {
                for(int k=j+1; k<j+i; k++) {
                    coins[j][j+i] = Math.max(coins[j][j+i], coins[j][k] + coins[k][j+i] +
                            dpnums[j] * dpnums[k] * dpnums[j+i]);
                }
            }
        }

        return coins[0][dpnums.length-1];
    }
}
