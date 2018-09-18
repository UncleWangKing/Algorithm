package array.simulation;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/18 17:14
 */
public class LeetCode_495_TeemoAttacking {
    public static void main(String[] args) {
        int list[] = {1,4};
        System.out.println(findPoisonedDuration(list, 2));
    }

    public static int findPoisonedDuration(int[] timeSeries, int duration) {
        if(0 == timeSeries.length) return 0;
        int result = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            int diff = timeSeries[i + 1] - timeSeries[i];
            result +=  Math.min(diff, duration);
        }

        return result + duration;
    }
}
