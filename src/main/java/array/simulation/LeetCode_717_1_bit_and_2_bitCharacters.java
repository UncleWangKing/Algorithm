package array.simulation;

public class LeetCode_717_1_bit_and_2_bitCharacters {
    public static void main(String[] args) {
        int list[] = {1, 1, 1, 0};
        System.out.println(isOneBitCharacter(list));
    }

    /**
     * 因为输入有[0] 所以假定是true 并从后往前遍历 快速失败
     * PS:题目要求为末尾必定为1bit
     * 尾部出现如下情况即可返回
     * [0,0] --> true --- 不管左边的0被编码为1bit或者2bit右边只能是1bit
     * [0,1,0] --> false --- 同理，不管左边0被编码为1bit或者2bit，右边都可以是2bit(10)
     * 这里可以看出 出现0是一个有特点的情况
     * 深入分析:
     * 可看出，以0为分隔，其右方的编码方式是不受0及其左方值的影响的。
     * 那么只需计算从末尾的0开始，遇到第二个0时候(或者遍历完数组)，所遇到的1的个数的奇偶性
     * 偶数-->true
     * 奇数-->false
     * [1,1,0] --> true
     * 编码
     */
    public static boolean isOneBitCharacter(int[] bits) {
        /**
         * 记录奇数个数 边界条件 0==count 时 也是true 不用特殊处理
         */
        int count = 0;
        for (int i = bits.length - 2; i >= 0; i--) {
            if(1 == bits[i])
                count++;
            else
                break;
        }

        return 0 == (count & 1);//坐下 判断奇偶 基本操作
    }

    /**
     * 既然奇偶变化就是返回值的true false变化 也可以换一个写法
     */
    public static boolean isOneBitCharacter2(int[] bits) {
        boolean flag  = true;
        for (int i = bits.length - 2; i >= 0; i--) {
            if(1 == bits[i])
                flag = !flag;
            else
                break;
        }

        return flag;
    }
    /**
     * 网上的最优解 4m 我的是6m 但是明显我的剪枝效果更好
     * leetcode 就是酱紫 习惯了
     * 说说思路 也很漂亮
     * 从前往后
     * 遇到1 因为1编码开头的只有2bit，说明1本身连同后一个一定会被编码使用，所以 i + 2
     * 遇到0 同理，因为没有0开头为1bit的编码 所以只能使用一个 所以 i + 1
     * 最后判断 i 是否等于length - 1 也就是停留在最后一个位置上
     * 下面已经是省略if的简化写法
     * 虽然看的博主觉得操作很神奇 把未简化的版本也写了
     * 但是现在的我已经直接就会这样写了 一点都不神奇 嘤嘤嘤
     * 递归回溯什么的真的蠢 不写了就
     */
    public boolean isOneBitCharacter3(int[] bits) {
        int i = 0;
        while (i < bits.length - 1)
            i += bits[i] + 1;
        return i == bits.length - 1;
    }
}