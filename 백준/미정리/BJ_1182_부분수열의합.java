package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14144 / Time:216ms
public class BJ_1182_부분수열의합 {

	static int N, S, ans, flag, end;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
		}
		end = 1 << N;
		
		while(++flag < end) {
			if( isSame() ) ans++;
		}
		
		System.out.println(ans);
		
		in.close();
	}

	private static boolean isSame() {
		int sum = 0;
		
		for (int i = 0; i < N; i++) {
			if( (flag & 1 << i) != 0 ) 
				sum += arr[N - 1 - i];
		}

		if(sum == S) return true;
		
		return false;
	}

}
