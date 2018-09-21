package array.simulation;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/21 16:15
 */
public class LeetCode_621_TaskScheduler {
    public static void main(String[] args) {
        char list[] = {'A','A','A','B','B','B'};
        System.out.println(leastInterval(list, 2));
    }

    /**
     * 分析:
     * 任务时间固定 只需分析等待时间
     * 任务种类大于延迟n 则无需等待
     * 任务种类小于等于n 等待t == n - k + 1 -- k 任务种类
     *
     * 注意善用多类任务 比如 A B 2C 2延迟 可以0等待
     *
     * 然后问题抽象成一个横轴是A-Z 纵轴是数量的直方图 可假设从高到低排好序
     */
    public static int leastInterval(char[] tasks, int n) {
        if(1 == n)
            return tasks.length;

    }
}
