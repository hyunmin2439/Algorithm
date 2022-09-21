import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// memory:38848KB / Time:412ms

public class BJ_15654_N과M5_Permutation {
	
	static int N, M;
	
	static int[] src;
	static boolean[] used;
	
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		used = new boolean[N];
		src = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			src[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(src);
		
		permu(0, "");
		
		sb.setLength(sb.length() - 1); // 마지막 \n 제거
		System.out.print(sb);
		
		in.close();
	}

	private static void permu(int idx, String str) {
		if(idx == M) {
			sb.append(str).append('\n');
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if( used[i] ) continue;
			used[i] = true;
			
			permu(idx + 1, str + src[i] + " ");
			
			used[i] = false;
		}
	}
}
