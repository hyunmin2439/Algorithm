import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Dynamic Programming
 * 
 * LIS[i] : A수열의 i번째 숫자를 마지막으로 하는 증가 부분수열의 가장 큰 합.
 * 
 * Memory:14508KB / Time:156ms
 */

public class BJ_11055_가장큰증가부분수열_1_LIS {

	static int N, max;
	static int[] A, LIS;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		A = new int[N];
		LIS = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			LIS[i] = A[i] = Integer.parseInt(st.nextToken());
		}
		
		max = A[0]; // N이 1이고 값이 1개 밖에 없을 경우 max는 갱신되지 않아 0을 출력함
					// 이를 방지해주기 위해 max를 A[0]로 초기화.
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < i; j++) {
				if(A[j] < A[i]) {
					LIS[i] = Math.max(LIS[i], LIS[j] + A[i]);
				}
				
				max = Math.max(max, LIS[i]);
			}
		}
		
		System.out.print(max);
		
		in.close();
	}

}
