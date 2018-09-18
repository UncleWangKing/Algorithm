package array.simulation;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/18 17:01
 */
public class LeetCode_605_CanPlaceFlowers {
    public static void main(String[] args) {
        int [] list = {1,0,0,0,1};

        System.out.println(canPlaceFlowers(list, 1));

    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            int left = i - 1, right = i + 1;
            if(left >= 0 && 1 == flowerbed[left]) continue;
            if(right < flowerbed.length && 1 == flowerbed[right]) continue;
            if(0 == flowerbed[i]){
                flowerbed[i] = 1;
                n--;
                i++;
            }
        }

        return n <= 0;
    }
}
