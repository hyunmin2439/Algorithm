package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * Memory:16124KB / Time:144ms
 */

public class BJ_2822_점수계산 {

	public static void main(String[] args) throws Exception {
		int[][] score = input();
		
		int[] res = solve(score);
		
		output(res);
	}
	
	private static void output(int[] res) {
		System.out.println(res[5]);
		
		for(int i = 0; i < 5; i++) {
			System.out.print(res[i] + " ");
		}
	}
	
	private static int[] solve(int[][] score) {
		int[] res = new int[6];
		
		for(int i = 0; i < 5; i++) {
			res[0] += score[i][0];
			res[i + 1] = score[i][1]; 
		}
		
		Arrays.sort(res);
		
		return res;
	}
	
	private static int[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] score = new int[8][2];
		
		for(int i = 0; i < 8; i++) {
			score[i][0] = Integer.parseInt(in.readLine());
			score[i][1] = i + 1;
		}
		
		Arrays.sort(score, (a, b) -> b[0] - a[0]);

		in.close();
		
		return score;
	}
}
