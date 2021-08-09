package solved.submit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon15650 {

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
		
		combi(0, 1);
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

	private static void combi(int cnt, int start) {
		if(cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(num[i]).append(" ");				
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= N; i++) {
			num[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}

}
