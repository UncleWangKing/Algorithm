package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/6 15:32
 */
public class ZDaPangArrayUtil {
    public static void printArray2(int [][] list){
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[0].length; j++) {
                System.out.print(list[i][j] + ",");
            }
            System.out.println();
        }
    }

    public static void printArray(int [] list){
        for (int i = 0; i < list.length; i++) {
                System.out.print(list[i] + ",");
        }
    }
    //生成指定[1-range]范围的count个随机整数组
    public static List<Integer> createRandomList(int range, int count){
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            list.add(random.nextInt(range + 1));
        }
        return list;
    }

    public static int[] createRandomArray(int range, int count){
        int []list = new int[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            list[i] = random.nextInt(range + 1);
        }
        return list;
    }
}
