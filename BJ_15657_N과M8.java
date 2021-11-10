package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Memory:18240KB / Time:220ms
public class BJ_15657_N과M8 {

	static int N, M;
	static int[] arr;
	
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		permu(0, 0, "");
		
		System.out.print(sb);
		in.close();
	}

	private static void permu(int idx, int start, String str) {
		if(idx == M) {
			sb.append(str).append('\n');
			return;
		}
		
		for(int i = start; i < N; i++) {
			permu(idx + 1, i, str + arr[i] + " ");
		}
	}

}
