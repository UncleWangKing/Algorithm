package array.simulation;

public class LeetCode_896_MonotonicArray {
    public static void main(String[] args) {
        int list[] = {1,2,2,3};//true
        System.out.println(isMonotonic(list));
    }

    public static boolean isMonotonic(int[] A) {
        int increase = 0, decrease = 0;

        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] >= A[i + 1])
                increase++;
            if (A[i] <= A[i + 1])
                decrease++;
        }

        return A.length - 1 == Math.max(increase, decrease);
    }
}
