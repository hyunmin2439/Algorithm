package solved.submit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_15651_N과M3 {

	static int N, M, num[];
	static BufferedReader  br;
	static BufferedWriter  bw;
	static StringTokenizer st;
	static StringBuilder   sb;
	
	public static void main(String[] args) throws IOException {	
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		num = new int[M];
		
		permu(0);
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

	private static void permu(int cnt) {
		if(cnt == M) {
			for (int i = 0; i < M; i++) {				
				sb.append(num[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			num[cnt] = i;
			permu(cnt + 1);
		}
	}

}
