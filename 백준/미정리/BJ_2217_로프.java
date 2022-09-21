package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// Memory : 26,820KB / Time:344ms
public class BJ_2217_로프 {

	private static int N;
	
	public static void main(String[] args) throws Exception {
		int[] rope = input();
		
		Arrays.sort(rope);
		
		int max = 0;
		for(int i = 0; i < N; i++) {
			max = Math.max(max, rope[i] * (N - i));
		}
		
		System.out.print(max);
	}
	
	private static int[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());		
		
		int[] rope = new int[N];
		for(int i = 0; i < N; i++) {
			rope[i] = Integer.parseInt(in.readLine());
		}
		
		in.close();
		
		return rope;
	}

}
