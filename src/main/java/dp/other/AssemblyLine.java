package dp.other;
 
public class AssemblyLine {
	public static void main(String[] args) {
		int[][] line = {{7,9,3,4,8,4},
						{8,5,6,4,5,7}};
		int[][] trans = {{2,3,1,3,4},
						 {2,1,2,2,1}};
		System.out.println(fastestWay(line, trans));
	}

	/**
	 * 简化问题:
	 * 1.去掉进入和离开生产线时候的消耗---本身也是加在生产线的头尾节点，没有特别意义
	 * 2.去掉生产线的数量参数---可直接通过数组长度获得
	 * 状态转换方程
	 * if(1 == i)
	 *  dp[0][0] = line[0][0]
	 *  dp[1][0] = line[1][0]
	 * else
	 *  dp[0][i] = Math.min(dp[1][i-1] + line[0][i] + trans[1][i-1], dp[0][i-1] + line[0][i]);
	 *  dp[1][i] = Math.min(dp[0][i-1] + line[1][i] + trans[0][i-1], dp[1][i-1] + line[1][i]);
	 */
	
	public static int fastestWay(int[][] line,int[][] trans) {
		int m = line.length;
		int n = line[0].length;
		int [][]dp = new int [m][n];
		dp[0][0] = line[0][0];
		dp[1][0] = line[1][0];

		for (int i = 1; i < n; i++) {
			//左换线 右直走
			dp[0][i] = Math.min(dp[1][i-1] + line[0][i] + trans[1][i-1], dp[0][i-1] + line[0][i]);
			dp[1][i] = Math.min(dp[0][i-1] + line[1][i] + trans[0][i-1], dp[1][i-1] + line[1][i]);
		}

		return Math.min(dp[0][n-1], dp[1][n-1]);
	}
}
