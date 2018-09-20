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
    //找到的优雅报告:http://www.cnblogs.com/bloomingFlower/p/9631843.html
    /**
     *  根据中位数定义分别在两个数组进行分割
     *  保证两点:
     *  1. mid1 + mid2 == (m - mid1) + (n - mid2) ==> mid1 = (m + n) / 2 - mid2
     *  2. max(m[i - 1], n[j - 1]) <= min(m[i], n[j])
     *
     *  切出来不满足条件 再根据情况二分调整 mid1和mid2联动 只需要保证left或者right正确二分调整即可
     */
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m < n) return findMedianSortedArrays(nums2, nums1);//确保数组1长度大于数组2 -- m >= n
        if (0 == n) return (nums1[(m - 1) / 2] + nums1[m / 2]) / 2.0;//如果数组2是空 -- 剪枝
        int left = 0, right = 2 * n;//n是较短数组的长度
        while (left <= right) {
            int mid2 = (left + right) / 2;//右边部分切割位置
            int mid1 = m + n - mid2;//左边部分切割位置
            //四种极限切割情况 以及默认正常情况
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
    //写一个两个有序数组中找第k个数的函数 -- k从1开始
    public static double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (1 == (1 & total))//奇数
            return findKth(nums1, 0, nums2, 0, total / 2 + 1);
        else
            return (findKth(nums1, 0, nums2, 0, total / 2) + findKth(nums1, 0, nums2, 0, total / 2 + 1)) / 2.0;
    }

    /**
     * 核心思路:每次排除min(nums1.length - a, k / 2)个结果 -- 尽量 k / 2
     */
    public static int findKth(int[] nums1, int a, int[] nums2, int b, int k) {//k从1开始
        if (nums1.length - a > nums2.length - b)//确保num2更长
            return findKth(nums2, b, nums1, a, k);
        if (0 == nums1.length - a)//有一个数组被排除完了 -- 未必是一开始传入的第一个
            return nums2[b + k - 1];
        if (1 == k)//必要的边界条件
            return Math.min(nums1[a], nums2[b]);
        /**
         * 每次排除掉 k1或k2个数 k也相应迭代
         */
        int k1 = Math.min(nums1.length - a, k / 2);
        int k2 = k - k1;
        if (nums1[a + k1 - 1] < nums2[b + k2 - 1])
            return findKth(nums1, a + k1, nums2, b, k - k1);//排除左边
        else if (nums1[a + k1 - 1] > nums2[b + k2 - 1])
            return findKth(nums1, a, nums2, b + k2, k - k2);//排除右边
        else
            return nums1[a + k1 - 1];//巧妙的剪枝
    }
}
