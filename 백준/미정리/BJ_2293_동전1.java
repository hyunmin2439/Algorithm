import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * dp[money] : money를 주어진 코인으로 만들 수 있는 경우의 수
 * 
 * Memory : 14140KB / Time : 192ms
 */

public class BJ_2293_동전1 {

	static int N, K;
	static int[] coin;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		coin = new int[N];
		
		for(int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(in.readLine());
		}
		
		dp = new int[K + 1];
		dp[0] = 1; // 초기식 : dp[1] += dp[1 - 1], 1원 동전 => dp[0]가 0이 되면 안됨.
				   // 마찬가지로 dp[5] += dp[5 - 5], 5원 동전 => 5원 동전만 사용하는 경우 +1
		
		for(int i = 0; i < N; i++) {
			// coin 가치부터 시작
			for(int money = coin[i]; money <= K; money++) {
				// 현재 코인으로 만들 수 있는 경우 더하기
				// dp[money] : 이전 코인들만으로 만들 수 있는 경우의 수
				// dp[money - coin[i]] : 새로운 동전을 추가해 만들 수 있는 경우의 수
				dp[money] += dp[money - coin[i]];
			}
		}
		
		System.out.print(dp[K]);
		
		in.close();
	}

}
