package solved.submit;

import java.io.*;
import java.util.StringTokenizer;

/*
 * Frequency
 * 
 * Input
 * N (1 ≤ N ≤ 500,000)
 * num1 num2 ... numN (-10,000,000 <= num <= 10,000,000)
 * M (1 ≤ M ≤ 500,000)
 * num1 num2 ... numM
 * 
 * Ouput
 * Frequency of each m number
 */

public class Baekjoon10816 {
	
	public static void main(String[] args) throws IOException {
		int[] arr = new int[20_000_001];
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		while(N-- > 0) {
			int idx = Integer.parseInt(st.nextToken());
			arr[idx + 10_000_000]++;
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(M-- > 0) {
			int idx = Integer.parseInt(st.nextToken());
			sb.append(arr[idx + 10_000_000] + " ");
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

}
