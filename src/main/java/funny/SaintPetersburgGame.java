package funny;

import java.util.Random;

/**
 * 圣彼得堡悖论游戏
 * 玩法:
 * 抛硬币，直至出现正面(1正面0背面)游戏结束。
 * 奖励为2的出现正面的次数n的次方  2^n
 * 例如
 * 第一次出现正面奖励为2
 * 第二次才出现正面奖励为4
 * 依次类推为 8 16 32.....
 * 纯数学上来说这个游戏的期望收益是无穷大
 * 期望E = 2^1 * p1 + 2^2 * p2 + 2^3 * p3 + ... + 2^n * pn
 * p1 p2 p3 分别是
 * p1 = (1/2)^1
 * p2 = (1/2)^2
 * p3 = (1/2)^3
 * E = 1 + 1 + 1 + .... + 1  = 无穷大
 * 但是前提是玩无穷多次 期望才是无穷大 这个程序验证了玩大概100万次期望在20左右。
 */
public class SaintPetersburgGame {
    public static void main(String[] args) {
        Random random = new Random();
        /**
         * 奖金总数
         */
        long total_sum = 0;
        /**
         * 玩的次数
         */
        long times = 1000_000;
        for (int i = 0; i < times; i++) {
            long reward = 0;
            while (1 != random.nextInt(2)){
                if(0 == reward)
                    reward = 2;
                else
                    reward *= 2;
            }
            total_sum += reward;
        }
        /**
         * 平均每次收益
         */
        System.out.println("平均每次收益" + total_sum * 1.0d / times);
    }


}
