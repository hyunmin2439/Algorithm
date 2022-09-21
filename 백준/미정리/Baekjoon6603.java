import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:16748KB / Time:176ms
public class Baekjoon6603 {
	
	static int N;
	static int[] S; // 로또 번호로 사용될 수 집합
	static boolean[] used;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			
			if(N == 0) break;
			
			S = new int[N];
			used = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				S[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs("", 0, 0);
			sb.append("\n");
		}
	
		System.out.println(sb);
		in.close();
	}

	private static void dfs(String ans, int start, int idx) {
		if(idx == 6) {
			sb.append(ans).append("\n");
		}
		
		for (int i = start; i < N; i++) {
			if( used[i] ) continue;
			
			used[i] = true;
			dfs(ans + S[i] + " ", i, idx + 1);
			used[i] = false;
		}
	}
}
