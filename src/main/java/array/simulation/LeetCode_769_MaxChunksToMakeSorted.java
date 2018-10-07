package array.simulation;

public class LeetCode_769_MaxChunksToMakeSorted {
    public static void main(String[] args) {
//        int list[] = {1,0,2,3,4};//4 [1, 0], [2], [3], [4]
        int list[] = {4,3,2,1,0};//1
        System.out.println(maxChunksToSorted(list));
    }

    /**
     * 留意 值是下标范围内 可利用
     * 应该是O(n)
     */
    public static int maxChunksToSorted(int[] arr) {
        int count = 0, left = 0;

        while(left < arr.length) {
            if(left == arr[left]) {
                left++;
            }
            else {//因为值是0-arr.length - 1 的一个排列 从小到大遍历 不等于就一定大于
                int right = arr[left];//搜索范围 在范围内找更大的值
                while (left <= right){
                    if(arr[left] > right)
                        right = arr[left];
                    left++;
                }
            }
            count++;
        }

        return count;
    }
}
