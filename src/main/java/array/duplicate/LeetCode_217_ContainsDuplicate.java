package array.duplicate;

public class LeetCode_217_ContainsDuplicate {
    public static void main(String[] args) {
        int list [] = {1,2,3,1};
        System.out.println(containsDuplicate(list));
    }

    public static boolean containsDuplicate(int[] list) {
        for (int i = 1; i < list.length; i++){
            int index =  i - 1;
            //每轮新选的数
            int tempValue = list[index + 1];
            //选择插入位置 并同时将数往后移一位
            while(index > -1 && list[index] > tempValue){
                list[index + 1] = list[index];
                index--;
            }
            //完成交换
            list[index + 1] = tempValue;
            if(index  > -1){
                if(list[index] == list[index + 1]){
                    return true;
                }
            }
        }
        return false;
    }
}
