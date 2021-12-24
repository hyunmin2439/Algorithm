package solved.notsubmit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_15649_N과M1 {

	static int N, M;
	static int[] numArr;
	static boolean[] isSelected;
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder  sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader( new InputStreamReader( System.in) );
		bw = new BufferedWriter( new OutputStreamWriter( System.out) );
		sb = new StringBuilder();
		
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		numArr = new int[M];
		isSelected = new boolean[N + 1]; // 0 : dummy
		
		permutation(0);
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

	private static void permutation(int cnt) {
		if(cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(numArr[i]).append(" ");				
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if(isSelected[i]) continue;
			
			numArr[cnt] = i;
			isSelected[i] = true;
			permutation(cnt + 1);
			isSelected[i] = false;
		}
	}

}
