package topological;

import java.util.*;

public class LeetCode_630_CourseScheduleIII {
    public static void main(String[] args) {
//        int [][]list = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};//[0,2,3] -- 3
        int [][]list = {{5,5},{4,6},{2,6}};//[1,2] -- 2
        System.out.println(scheduleCourse(list));
    }

    /**
     * 贪心
     * 维护目前总耗时 总耗时不超过截止 即可学习
     * 先按截止日期排序
     * 再维护一个耗时大根堆 每次时间不够 去掉耗时最多的
     */
    public static int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, new Comparator<int[]>(){
            public int compare(int[] a,int[] b){
                return a[1] - b[1];
            }
        });
        Queue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        int curTime = 0;
        for (int[] course : courses) {
            curTime += course[0];
            q.add(course[0]);
            if (curTime > course[1]) {
                curTime -= q.poll();
            }
        }
        return q.size();
    }
}
