package array.indexmagic;

public class LeetCode_287_FindtheDuplicateNumber {
    public static void main(String[] args) {
        int list[] = {3, 1, 2, 3, 4};
        System.out.println(findDuplicate_2(list));
    }

    /**
     * 二分法:判断在这区间内的数的个数是否大于这份区间本身
     * 如果大于则一定有重复--- 鸽巢原理
     * [1, 4, 4, 2, 4]这种情况少3的情况也不必担心 因为区间内每少一个不重复数 必然多一个重复数
     */
    public static int findDuplicate(int[] nums) {
        return find(1, nums.length - 1, nums);
    }

    public static int find(int begin, int end, int[] nums) {
        if(begin == end)
            return begin;

        int count = 0;
        int mid = (end + begin) / 2;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] >= begin && nums[i] <= mid)
                count++;
        }

        boolean inLeft = count > mid - begin + 1;

        if (inLeft) {
            return find(begin, mid, nums);
        } else
            return find(mid + 1, end, nums);
    }

    /**
     * 若能证明：根据题意存在任意一组满足要求的数,可分为重复和非重复数两类，
     * 一定存在一个按从nums[0]开始 ，step = nums[step]的步骤循环嵌套执行形成的带环链表，
     * 且入环口的值(某个nums[step])，一定是重复数的值。
     * 则可按成环链表求入环口的方式操作，处理本题。
     *
     * 拆分为两步的等效证明:
     * 证明1:从nums[0]开始,一定会走到nums[step]是重复数的一步
     * 证明2:nums[step]是重复数的情况，那么nums[steps]之后成环，且是链表环入口
     *
     * 若能完成证明1，2 -- 即可完成证明。
     *
     * 引理1:若nums[step]是非重复数，一定会走到nums[step]是重复数
     *
     * 引理1证明:
     * 先假设我们能永远不走到nums[step]是重复数，那执行过程中必然在非重复数之间成环，只需证明费非复数无法成环即可。
     * 假设能成环，入环口为某个非重复数，自然其位置是step_temp,值是nums[step_temp]，
     * 若要再次回到这个位置，必须是某另一个step等于step_temp,
     * (或者nums[step_temp] == step_temp,但这种情况最初就无法到达step_temp，比如数值1在[1]位置上，1不重复，怎么都到不了)，
     * 由于不重复，所有永远无法回到该入环口，无法成环。
     * 由于无法在非重复数位置间成环，则必将走到非重复数以外的位置，而其他所有位置都是重复数，固一定会走到nums[step]是重复数。
     * 证毕
     *
     * 证明1:
     * 由于一开始从nums[0]开始
     * 如果nums[0]本身是重复数，则直接达成nums[step]是重复数 -- 达成
     * 如果nums[0]本身是非重复数，由引理1证明，将走到nums[step]是重复数 -- 达成
     * 证毕。
     *
     * 证明2:
     * 若nums[step]下一步也是重复数，nums[steps]之后成环，且是链表环入口 -- 达成
     * 若nums[step]下一步是非重复数，则由于引理论1，一定会走到nums[step]是重复数，nums[steps]之后成环，且是链表环入口 -- 达成
     * 证毕
     *
     *
     */
    public static int findDuplicate_2(int[] nums) {
        int slow = 0;
        int fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
