package topological;

import util.ZDaPangArrayUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode_210_CourseScheduleII {
    public static void main(String[] args) {
        int num = 4; int [][] list = {{1,0},{2,0},{3,1},{3,2}};//[0,1,2,3] or [0,2,1,3]
        ZDaPangArrayUtil.printArray(findOrder(num, list));
    }

    /**
     * DFS
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] res = new int[numCourses];
        int index = 0;
        int[] in = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
            in[edge[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                queue.add(i);
                res[index++] = i;
            }
        }

        while(!queue.isEmpty()) {
            int i = queue.poll();
            for (int a : graph.get(i)) {
                in[a]--;
                if (in[a] == 0) {
                    queue.add(a);
                    res[index++] = a;
                }
            }
        }

        return index == numCourses ? res : new int[]{};
    }
}
