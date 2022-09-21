package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14244KB / Time:132ms

public class BJ_1453_피시방알바 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		boolean[] used = new boolean[101]; // 0:dummy, 100대의 컴퓨터
		
		int res = 0;
		int N = Integer.parseInt(in.readLine());
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			int seatNumber = Integer.parseInt(st.nextToken());
			
			if( used[seatNumber] ) res++;
			else used[seatNumber] = true;
		}
		
		System.out.println(res);
		
		in.close();
	}

}
