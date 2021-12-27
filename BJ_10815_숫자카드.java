package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory: 117064KB / Time: 776ms
public class BJ_10815_숫자카드 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		boolean[] isHaveCard = new boolean[20_000_001];
		
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			isHaveCard[Integer.parseInt(st.nextToken()) + 10_000_000] = true;
		}
		
		int M = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < M; i++) {
			sb.append( isHaveCard[Integer.parseInt(st.nextToken()) + 10_000_000] ? 1 : 0 ).append(" ");
		}
		
		System.out.print(sb);
		
		in.close();
	}

}
