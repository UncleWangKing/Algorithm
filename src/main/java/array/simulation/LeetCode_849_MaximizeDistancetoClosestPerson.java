package array.simulation;

public class LeetCode_849_MaximizeDistancetoClosestPerson {
    public static void main(String[] args) {
//        int list[] = {1,0,0,0,1,0,1};
        int list[] = {1,0,0,1};
        System.out.println(maxDistToClosest(list));
    }

    /**
     * 本质 找值为0的最长连续子串长度
     * 奇数除以2
     * 偶数除以2 - 1
     * 和数组两头相连的连续0 单独处理
     */
    public static int maxDistToClosest(int[] seats) {
        int max = 0, i = 0;
        while (i < seats.length) {
            int temp = 0, left = i;
            while (i < seats.length && 0 == seats[i]) {
                i++;
                temp++;
            }
            if(0 == left || seats.length == i)
                max = Math.max(max, temp);
            else
                max = Math.max((0 == (temp & 1) ? temp / 2 : temp / 2 + 1), max);
            i++;//此处i要么是length - 1 要么i指向值为1 多加1无妨
        }

        return max;
    }
}
