package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14424KB / Time:128ms
public class BJ_퇴사_14501 {

	private static int N, maxProfit, memoi[];
	
	public static void main(String[] args) throws Exception {
		int[][] consulting = input();
		
		for(int i = 1; i <= N; i++) {
			dfs(consulting, i, 0);			
		}
		
		System.out.print(maxProfit);
	}
	
	private static void dfs(int[][] consulting, int day, int pay) {
		if(day > N) {
			maxProfit = Math.max(maxProfit, pay);
			return;
		}
		
		if(pay != 0 && memoi[day] >= pay) return;
		
		memoi[day] = pay;
		
		if(consulting[day][0] + day <= N + 1) 
			dfs(consulting, day + consulting[day][0], pay + consulting[day][1]);
		
		dfs(consulting, day + 1, pay);
	}
	
	private static int[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		int[][] consulting = new int[N + 1][2];
		memoi = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			consulting[i][0] = Integer.parseInt(st.nextToken());
			consulting[i][1] = Integer.parseInt(st.nextToken());
		}
		
		in.close();
		
		return consulting;
	}
}
