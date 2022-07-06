import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, K, cnt;
	
	public static void main(String[] args) throws Exception {
		int[] kit = input();
		
		dfs(kit, new boolean[N], 500, 0);
		
		System.out.print(cnt);
	}
	
	private static void dfs(int[] kit, boolean[] used, int weight, int idx) {
		if(idx == N) {
			cnt++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if( used[i] ) continue;
			
			if( weight - K + kit[i] < 500 ) continue;
			
			used[i] = true;
			dfs(kit, used, weight - K + kit[i], idx + 1);
			used[i] = false;
		}
	}
	
	private static int[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[] kit = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			kit[i] = Integer.parseInt(st.nextToken());
		}
		
		in.close();
		
		return kit;
	}

}
