package binarysearch;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2019/3/21 14:41
 */
public class LeetCode_1014_CapacityToShipPackagesWithinDDays {
    public static void main(String[] args) {
        int []weights = {1,2,3,1,1};
        int D = 4;
        System.out.println(shipWithinDays(weights, D));
    }

    /**
     * 初步分析 nlog2n 范围 maxWeight - maxWeight*weights.length / D
     */
    public static int shipWithinDays(int[] weights, int D) {
        int maxWeight = Integer.MIN_VALUE;
        for (int weight:weights){
            maxWeight = maxWeight < weight ? weight : maxWeight;
        }
        /**
         * + 1 避免[2,2] 3这样的情况
         */
        int maxCapacity = maxWeight * weights.length / D + 1;
        int minCapacity = maxWeight;
        while (minCapacity < maxCapacity){
            int mid = minCapacity + (maxCapacity - minCapacity) / 2;
            if(canDo(weights, D, mid))
                maxCapacity = mid;
            else
                minCapacity = mid + 1;
        }
        return minCapacity;
    }

    /**
     * 此方法如果capacity < maxWeight 会有问题
     */
    public static boolean canDo(int[] weights, int D, int capacity){
        int temp = 0;
        for (int weight:weights){
            temp += weight;
            if(temp > capacity){
                temp = weight;
                if(0 == --D)
                    return false;
            }
        }

        return true;
    }
}
