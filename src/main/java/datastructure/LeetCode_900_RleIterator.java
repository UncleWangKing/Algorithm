package datastructure;

public class LeetCode_900_RleIterator {
    public static void main(String[] args) {
        int list[] = {3,8,0,9,2,5};
        RLEIterator rleIterator = new RLEIterator(list);
        System.out.println(rleIterator.next(2));
        System.out.println(rleIterator.next(1));
        System.out.println(rleIterator.next(1));
        System.out.println(rleIterator.next(2));
    }

    /**
     * 输入：["RLEIterator","next","next","next","next"], [[[3,8,0,9,2,5]],[2],[1],[1],[2]]
     * 输出：[null,8,8,5,-1]
     */
    public static class RLEIterator {
        private int list[];
        private int curIndex = 0;
        private int curNum = 0;

        public RLEIterator(int[] A) {
            list = A;
        }

        public int next(int n) {
            while (list.length - 2 >= curIndex){
                if(list[curIndex] - curNum >= n) {
                    curNum += n;
                    return list[curIndex + 1];
                }else {
                    n -= list[curIndex] - curNum;
                    curNum = 0;
                    curIndex += 2;
                }
            }

            return -1;
        }
    }
}
