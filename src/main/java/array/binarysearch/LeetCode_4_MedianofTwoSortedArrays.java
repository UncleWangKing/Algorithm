package array.binarysearch;

public class LeetCode_4_MedianofTwoSortedArrays {
    public static void main(String[] args) {
        int nums1 [] = {1,3};
        int nums2 [] = {2};
        System.out.println(findMedianSortedArrays2(nums1, nums2));
    }
    //m + n
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int []list = new int[nums1.length + nums2.length];
        int left = 0, right = 0;
        for (int i = 0; i < list.length; i++) {
            if(right >= nums2.length || (left < nums1.length && nums1[left] < nums2[right]))
                list[i] = nums1[left++];
            else
                list[i] = nums2[right++];
        }
        if(0 == (1 & list.length))
            return (list[list.length / 2] + list[list.length / 2 - 1]) / 2.0;
        else
            return list[list.length / 2];
    }

    //log(m + n)
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m < n) return findMedianSortedArrays(nums2, nums1);
        if (n == 0) return (nums1[(m - 1) / 2] + nums1[m / 2]) / 2.0;
        int left = 0, right = 2 * n;
        while (left <= right) {
            int mid2 = (left + right) / 2;
            int mid1 = m + n - mid2;
            double L1 = mid1 == 0 ? Double.MIN_VALUE : nums1[(mid1 - 1) / 2];
            double L2 = mid2 == 0 ? Double.MIN_VALUE : nums2[(mid2 - 1) / 2];
            double R1 = mid1 == m * 2 ? Double.MAX_VALUE : nums1[mid1 / 2];
            double R2 = mid2 == n * 2 ? Double.MAX_VALUE : nums2[mid2 / 2];
            if (L1 > R2) left = mid2 + 1;
            else if (L2 > R1) right = mid2 - 1;
            else return (Math.max(L1, L2) + Math.min(R1, R2)) / 2;
        }
        return -1;
    }
    //log(m + n) -- best
    public static double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (total % 2 == 1) {
            return findKth(nums1, 0, nums2, 0, total / 2 + 1);
        }else {
            return (findKth(nums1, 0, nums2, 0, total / 2) + findKth(nums1, 0, nums2, 0, total / 2 + 1)) / 2.0;
        }
    }

    public static int findKth(int[] nums1, int a, int[] nums2, int b, int k) {
        if (nums1.length - a > nums2.length - b) {
            return findKth(nums2, b, nums1, a, k);
        }
        if (nums1.length - a == 0) {
            return nums2[b + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[a], nums2[b]);
        }
        int k1 = Math.min(nums1.length - a, k / 2);
        int k2 = k - k1;
        if (nums1[a + k1 - 1] < nums2[b + k2 - 1]) {
            return findKth(nums1, a + k1, nums2, b, k - k1);
        }else if (nums1[a + k1 - 1] > nums2[b + k2 - 1]) {
            return findKth(nums1, a, nums2, b + k2, k - k2);
        }else {
            return nums1[a + k1 - 1];
        }
    }
}
