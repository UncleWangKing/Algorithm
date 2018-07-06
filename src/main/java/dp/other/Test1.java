package dp.other;
 
public class Test1 {
	public static void main(String[] args) {
		int N = 6;
		int[] e = {0,0};
		int[][] a = {{7,9,3,4,8,4},
					 {8,5,6,4,5,7}};
		int[][] t = {{2,3,1,3,4},
					 {2,1,2,2,1}};
		int[] x = {0,0};
		Object[] obj = fastestWay(a,t,e,x,N);
		int[][] l = (int[][]) obj[0];
		int lr = (int) obj[1];
		printStations(l,lr,N);
	}
	
	public static Object[] fastestWay(int[][] a,int[][] t,int[] e,int[] x,int N) {
		int[][] f = new int[N][N];
		int[][] l = new int[2][N];
		f[0][0] = e[0]+a[0][0];
		f[1][0] = e[1]+a[1][0];
		l[0][0] = 1;
		l[1][0] = 2;
		int fr=0,lr=0;
		Object[] obj = new Object[2];
		for(int i=1;i<N;i++) {
			if(f[0][i-1]+a[0][i]<f[1][i-1]+t[1][i-1]+a[0][i]) {
				f[0][i] = f[0][i-1]+a[0][i];
				l[0][i] = 1;
			}else {
				f[0][i] = f[1][i-1]+t[1][i-1]+a[0][i];
				l[0][i] = 2;
			}
			
			if(f[1][i-1]+a[1][i]<f[0][i-1]+t[0][i-1]+a[1][i]) {
				f[1][i] = f[1][i-1]+a[1][i];
				l[1][i] = 2;
			}else {
				f[1][i] = f[0][i-1]+t[0][i-1]+a[1][i];
				l[1][i] = 1;
			}
		}
		if(f[0][N-1]+x[0]<f[1][N-1]+x[1]) {
			fr = f[0][N-1]+x[0];
			lr = 1;
		}else {
			fr = f[1][N-1]+x[1];
			lr = 2;
		}
		obj[0] = l;
		obj[1] = lr;
		System.out.println(fr);
		return obj;
	}
	
	public static void printStations(int[][] l,int lr,int N) {
		int i = lr;
		System.out.println("line"+i+","+"station"+N);
		for(int j=N;j>=2;j--) {
			i = l[i-1][j-1];
			System.out.println("line"+i+","+"station"+(j-1));
		}
	}
}
