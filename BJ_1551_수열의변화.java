package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14192KB / Time:128ms
public class BJ_1551_수열의변화 {

	private static int N, K;
	
	public static void main(String[] args) throws Exception {
		int[] arr = input();
		
		for(int i = 0; i < K; i++) {
			arr = solve(arr);			
		}
		
		output(arr);
	}
	
	private static int[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		String[] numbers = in.readLine().split(",");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(numbers[i]);
		}
		
		in.close();
		
		return arr;
	}
	
	private static int[] solve(int[] arr) {
		N = N - 1;
		
		int[] newArr = new int[N];
		
		for(int i = 0; i < N; i++) {
			newArr[i] = arr[i + 1] - arr[i];
		}
		
		return newArr;
	}
	
	private static void output(int[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < arr.length; i++) {
			sb.append(arr[i]).append(',');
		}
		
		sb.setLength(sb.length() - 1);
		
		System.out.print(sb);
	}
	
}
