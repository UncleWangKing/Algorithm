package segmenttree;

public class LeetCode_850_RectangleAreaII {
    public static void main(String[] args) {
        int[][] rec = {{0,0,2,2},{1,0,2,3},{1,0,3,1}};
        System.out.println(rectangleArea(rec));
    }

    /**
     * TLE
     * O(N∗2^N) O(N)
     */
    public static int rectangleArea(int[][] rectangles) {
        int N = rectangles.length;

        long ans = 0;
        for (int subset = 1; subset < (1 << N); ++subset) {
            int[] rec = new int[]{0, 0, 1_000_000_000, 1_000_000_000};
            int parity = -1;
            for (int bit = 0; bit < N; ++bit)
                if (((subset >> bit) & 1) != 0) {
                    rec = intersect(rec, rectangles[bit]);
                    parity *= -1;
                }
            ans += parity * area(rec);
        }

        long MOD = 1_000_000_007;
        ans %= MOD;
        if (ans < 0) ans += MOD;
        return (int) ans;
    }

    public static long area(int[] rec) {
        long dx = Math.max(0, rec[2] - rec[0]);
        long dy = Math.max(0, rec[3] - rec[1]);
        return dx * dy;
    }

    public static int[] intersect(int[] rec1, int[] rec2) {
        return new int[]{
            Math.max(rec1[0], rec2[0]),
            Math.max(rec1[1], rec2[1]),
            Math.min(rec1[2], rec2[2]),
            Math.min(rec1[3], rec2[3])
        };
    }
}
