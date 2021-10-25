import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * A1 = 1, i ≥ 2인 경우에 Ai는 Ai-1의 배수
 * 
 * 위의 조건 때문에 Greedy Algorithm으로 풀 수 있는 문제
 * 
 * Memory: 14240KB / Time:124ms
 */

public class Baekjoon11047 {

	static int N, K, totCnt;
	static int[] valueOfCoins;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		valueOfCoins = new int[N];
		for (int i = 0; i < N; i++) {
			valueOfCoins[i] = Integer.parseInt(in.readLine());
		}
		
		int idx = N - 1;
		while(K > 0) {
			while(valueOfCoins[idx] > K) idx--;
			
			totCnt += K / valueOfCoins[idx];
			K = K % valueOfCoins[idx];
		}
		
		System.out.println(totCnt);
		in.close();
	}

}
