package topological;

import util.ZDaPangArrayUtil;

public class LeetCode_210_CourseScheduleII {
    public static void main(String[] args) {
        int num = 4; int [][] list = {{1,0},{2,0},{3,1},{3,2}};//[0,1,2,3] or [0,2,1,3]
        ZDaPangArrayUtil.printArray(findOrder(num, list));
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {

    }
}
