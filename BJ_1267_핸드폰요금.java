package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:15816KB / Time:148ms
public class BJ_1267_핸드폰요금 {

	private static int N, telTime[];
	
	public static void main(String[] args) throws Exception {
		input();
		
		solve();
	}
	
	private static void solve() {
		int youngsic = 0, minsic = 0;
		
		for(int i = 0; i < N; i++) {
			youngsic += ((telTime[i] / 30) + 1) * 10;
			
			minsic += ((telTime[i] / 60) + 1) * 15;
		}
		
		if(youngsic < minsic)
			System.out.print("Y " + youngsic);
		else if(youngsic > minsic)
			System.out.print("M " + minsic);
		else
			System.out.print("Y M " + minsic);
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		telTime = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			telTime[i] = Integer.parseInt(st.nextToken());
		}
		
		in.close();
	}

}
