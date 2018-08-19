package util;

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
}
