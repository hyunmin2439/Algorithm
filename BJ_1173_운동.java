package no_upload;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1173_운동 {

	private static int N, m, M, T, R;
	
	public static void main(String[] args) throws Exception {
		input();
		
		int time = m + T > M ? -1 : solve();
		
		System.out.print(time);
	}
	
	private static int solve() {
		int time = 0, timeExercised = 0, curr = m;
		
		while(timeExercised < N) {
			if(curr + T <= M) {
				curr += T;
				timeExercised++;
			}
			else {
				curr -= R;
				curr = curr > m ? curr : m;
			}
			time++;
		}
		
		return time;
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
	}

}
