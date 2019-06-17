package funny;

import java.util.Random;

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
