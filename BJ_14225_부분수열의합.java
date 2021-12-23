package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:18832KB / Time:264ms
public class BJ_14225_부분수열의합 {

	static final int VISIT_MAX = 100_000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// input
		int N = Integer.parseInt(in.readLine());

		int[] arr = new int[N];
		boolean[] visit = new boolean[VISIT_MAX * N + 1];

		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// process
		int flag = 0;
		int end = 1 << N;
		
		while(++flag < end) {
			int sum = 0;
			
			for (int i = 0; i < N; i++) {
				if( (flag & 1 << i) != 0 )
					sum += arr[i];
			}
			
			visit[sum] = true;
		}
		
		// output
		int ans = 1;
		while( visit[ans] ) ans++;
		
		System.out.println(ans);
		
		in.close();
	}

}
