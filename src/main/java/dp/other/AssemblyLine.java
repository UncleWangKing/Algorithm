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
	 * if(1 == j)
	 *  f1[j] = e1 + a1,1
	 *  f2[j] = e2 + a2,1
	 * else
	 * 	f1[j] = min(f1[j−1] + a1,j ,f2[j−1] +t2,j−1 + a1,j ) （j=2,3…n）
	 *  f2[j] = min(f2[j−1] + a2,j ,f1[j−1] +t1,j−1 + a2,j ) （j=2,3…n）
	 */
	
	public static int fastestWay(int[][] line,int[][] trans) {
		int n = line[0].length;
		int []dp = new int [n];
		int lineNumber = 0;
		if(line[0][0] + trans[0][0] < line[1][0] + trans[1][0]){
			lineNumber = 0;
			dp[0] = line[0][0];
		}
		else {
			lineNumber = 1;
			dp[0] = line[1][0];
		}

		for (int i = 1; i < n; i++) {
			if(0 == lineNumber){
				//左换线 右直走
				dp[i] = Math.min(dp[i-1] + line[1][i] + trans[0][i-1],dp[i-1] + line[0][i]);
			}else{
				dp[i] = Math.min(dp[i-1] + line[0][i] + trans[1][i-1],dp[i-1] + line[1][i]);
			}
		}

		return dp[n-1];
	}
}
