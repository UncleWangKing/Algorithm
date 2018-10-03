package other;

/**
 * 输入一串数字代表一排紧挨着的若干间房子的宽度。
 * 现在为了新建一间宽度为k的房子，问至少要拆除几间老房子？
 * 比如输入：2 3 1 2 4 3，k=7.则输出为2.
 */
public class BuildHouse {
    public static void main(String[] args) {
        int list[] = {2,3,1,2,4,3};
        System.out.println(minWidth(list, 7));
    }

    public static int minWidth(int [] list, int k){
        int minWidth = list.length;
        int sum = 0, left = 0, right = 0;

        while (right < list.length){
            sum += list[right];
            if(sum >= k) {
                while (left <= right && sum - list[left] >= k)
                    sum -= list[left++];
                minWidth = Math.min(minWidth, right - left + 1);
            }
            right++;
        }

        return minWidth;
    }
}
