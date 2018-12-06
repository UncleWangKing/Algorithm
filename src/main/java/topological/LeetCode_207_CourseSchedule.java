package topological;

public class LeetCode_207_CourseSchedule {
    public static void main(String[] args) {
        int num = 2;
        int [][] list = {{1,0},{0,1}};
        System.out.println(canFinish(num, list));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int []root = new int[numCourses];
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }
        for (int i = 0; i < numCourses; i++) {
            for (int j = 0; j < numCourses; j++) {
                
            }
        }
        return true;
    }
}
