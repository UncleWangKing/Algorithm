package binarysearch;

public class LeetCode_852_PeakIndexInAMountainArray {
    public static void main(String[] args) {
        int[] list = {0,2,1,0};
        System.out.println(peakIndexInMountainArray(list));
    }

    public static int peakIndexInMountainArray(int[] A) {
        int left = 0, right = A.length - 1;

        while (left != right){
            int mid = left + (right - left) / 2;
            if(A[mid] < A[mid + 1])
                left = mid + 1;
            else
                right = mid;
        }

        return left;
    }
}
