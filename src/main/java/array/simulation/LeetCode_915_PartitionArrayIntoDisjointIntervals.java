package array.simulation;

public class LeetCode_915_PartitionArrayIntoDisjointIntervals {
    public static void main(String[] args) {
        int list[] = {5,0,3,8,6};
        System.out.println(partitionDisjoint(list));
    }

    public static int partitionDisjoint(int[] A) {
        int ll = A[0],left = 0,right = A.length-1;
        while (right < A.length && left < right){
            if (A[right] >= ll) right--;
            else if (A[right] < ll){
                for (; left < right; left++) {
                    if (A[left] > ll) ll = A[left];
                }
                right = A.length - 1;
            }
        }
        return left + 1;
    }

    public int partitionDisjoint2(int[] A) {
        int n = A.length;
        int []rightLargest = new int[n];
        rightLargest[n - 1] = A[n - 1];
        for(int i = n - 2;i >= 0; --i)
            rightLargest[i] = Math.min(rightLargest[i + 1], A[i]);
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n - 1; ++i) {
            max = Math.max(max, A[i]);
            if(max <= rightLargest[i + 1])
                return i + 1;
        }
        return 0;
    }
}
