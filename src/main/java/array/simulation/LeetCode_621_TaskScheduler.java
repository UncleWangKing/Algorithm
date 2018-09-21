package array.simulation;

import java.util.Arrays;

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
     * AAAABBBEEFFGG 3为例
     * A---A---A---A
     * AB--AB--AB--A   (加入B)
     * ABE-ABE-AB--A   (加入E)
     * ABEFABE-ABF-A   (加入F，每次尽可能填满或者是均匀填充)
     * ABEFABEGABFGA   (加入G)
     * 再来看一个例子：
     * ACCCEEE 2
     * 我们发现任务C和E都出现了三次，那么我们就将CE看作一个整体，在中间加入一个位置即可：
     * CE-CE-CE
     * CEACE-CE   (加入A)
     * 注意最后面那个idle不能省略，不然就不满足相同两个任务之间要隔2个时间点了。
     */
    public static int leastInterval(char[] tasks, int n) {
        int cnt[] = new int[26];
        for (char task : tasks)
            ++cnt[task - 'A'];

        Arrays.sort(cnt);
        int i = 25, mx = cnt[25], len = tasks.length;
        while (i >= 0 && cnt[i] == mx) --i;
        /**
         * mx 最多字母减数量 ，mx - 1中间slot数量
         * n + 1 slot宽度
         * 25 - i 并列最多字母数量 AAABBB 就是2 A和B两种并列最多 -- 本质是最后一个slot之前没纳入计算 单独来处理
         */
        return Math.max(len, (mx - 1) * (n + 1) + 25 - i);
    }
}
