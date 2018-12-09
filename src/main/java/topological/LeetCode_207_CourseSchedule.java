package topological;

import java.util.*;

public class LeetCode_207_CourseSchedule {
    public static void main(String[] args) {
//        int num = 3; int [][] list = {{0,1},{0,2},{1,2}};//true
//        int num = 2; int [][] list = {{0,1},{1,0}};//false
//        int num = 3; int [][] list = {{0,2},{1,2},{2,0}};//false
        int num = 2; int [][] list = {{1,0}};//true
        System.out.println(canFinish(num, list));
    }

    /**
     * BFS 每次将入度为0的点剔除 并将其指向的点入度-1 看最后是否还有入度不为0的点 有就有环
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
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
            if (in[i] == 0) queue.add(i);
        }

        while(!queue.isEmpty()) {
            int i = queue.poll();
            for (int a : graph.get(i)) {
                in[a]--;
                if (in[a] == 0) queue.add(a);
            }
        }

        for (int i = 0; i < numCourses; i++)
            if (in[i] != 0) return false;
        return true;
    }

    /**
     * DFS
     */
    public static boolean canFinish2(int numCourses, int[][] prerequisites){
        int[] isVisited = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
        }
        for(int i = 0; i < numCourses; i++){
            if(!dfsFinish(graph, i, isVisited))
                return false;
        }
        return true;
    }
    private static boolean dfsFinish(List<List<Integer>> graph, int visit, int[] isVisited){
        if(1 == isVisited[visit])
            return true;
        if(-1 == isVisited[visit])
            return false;
        isVisited[visit] = -1;
        for(int next : graph.get(visit)){
            if(!dfsFinish(graph, next, isVisited))
                return false;
        }
        isVisited[visit] = 1;
        return  true;
    }
}
