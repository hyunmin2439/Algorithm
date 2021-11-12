import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * Dynamic Programming
 * 
 * 연속 3잔을 마실 수 없다. 라는 규칙이 핵심인 문제
 * 
 * dp[i]가 의미하는 것은 i번째 포도주까지 규칙을 지키면서 최대 마신양.
 * 
 * max(arr[i - 1] + dp[i - 3], dp[i - 2])
 * 
 *   	      -3 -2 -1  i		 -2 -1  i
 * 	        ...O  X  O  O    , ...O  X  O   O:마신 포도주, X:안 마신 포도주
 * 
 *  이러한 규칙으로 풀었는데, 안 풀린다. 문제가 무엇인지 보니,
 *  
 *  무조건 포도주를 한번 안마신게 최대라고 할 수 없다.
 *  
 *  O X X O 와 같이 두번을 건너 뛰는 경우가 더 최대 일 수 있다는 것.
 *  
 *  세번을 이상을 건너뛰는 것은 음수가 없기 때문에 위 경우들보다 최대일 수가 없다.
 *  
 *  때문에 위 점화식에서 최대값을 구하고 max(dp[i - 1], dp[i])를 구해준다.
 *  
 *  
 *        -1 i					 -1 i
 *  이것은 O X의 경우 뿐만 아니라 X X의 경우도 포함하여 최대의 값을 가지고 있다.
 *  
 *  이러한 점화식도 추가하면 결과 값이 구해진다.
 *  
 *  Memory:15644KB / Time:156ms
 */


public class BJ_2156_포도주시식_1_dp {
	
	static int N;
	static int[] arr, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		arr = new int[N + 1];
		dp = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		// N이 1이나 2일때는 점화식이 성립이 안되고 오류 발생.
		if(N < 3) {
			System.out.println( N == 1 ? arr[1] : (arr[1] + arr[2]) );
			return;
		}
		
		// 기본값 세팅
		dp[1] = arr[1];
		dp[2] = arr[1] + arr[2];
		
		for(int i = 3; i <= N; i++) {
			// 1번 점화식
			dp[i] = arr[i] + Integer.max(arr[i - 1] + dp[i - 3], dp[i - 2]);

			// 2번 점화식
			dp[i] = Integer.max(dp[i - 1], dp[i]);
		}
		
		System.out.print(dp[N]);
		
		in.close();
	}

}
