package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11758_CCW {

	private static int[] y, x;
	
	public static void main(String[] args) throws Exception {
		input();
		
		int res = ccw();
		
		System.out.print( res );
	}

	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		x = new int[3];
		y = new int[3];
		
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(in.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		in.close();
	}
	
	private static int ccw() {
		int res = x[0] * y[1] + x[1] * y[2] + x[2] * y[0];
		res -= x[1] * y[0] + x[2] * y[1] + x[0] * y[2];
		
		if(res > 0) return 1; // 반 시계 방향
		if(res < 0) return -1; // 시계 방향
		return 0;
	}
}
