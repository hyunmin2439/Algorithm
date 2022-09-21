package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * MST(Minimum Spanning Tree)
 * 
 * 정점 개수 - 1만큼의 간선을 연결하면 모든 정점이 다 이어진다.
 * 
 * Memory: 31604KB / Time: 252ms
 */

public class BJ_9372_상근이의여행 {

	static int T, N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(in.readLine());
		
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
			}
			
			System.out.println(N - 1);
		}
		
		in.close();
	}

}
