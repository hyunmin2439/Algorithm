import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, ordered[];
	
	public static void main(String[] args) throws Exception {
		input();
		
		dfs(0, new boolean[N], new int[N]);
	}
	
	private static void dfs(int idx, boolean[] selected, int[] numbers) {
		if(idx == N) {
			for(int i = 0; i < N; i++) {
				System.out.print(numbers[i] + 1 + " ");				
			}
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if( selected[i] ) continue;
			
			if( ! isCorrectOrdered(i, idx, numbers) ) continue;

			selected[i] = true;
			numbers[idx] = i;
			
			dfs(idx + 1, selected, numbers);
			
			selected[i] = false;
		}
	}
	
	private static boolean isCorrectOrdered(int myNumber, int myOrder, int[] numbers) {
		int cnt = 0;
		
		for(int j = myOrder - 1; j >= 0; j--) {
			if(numbers[j] > myNumber) cnt++;
		}
		
		return ordered[myNumber] == cnt ? true : false;
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		ordered = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			ordered[i] = Integer.parseInt(st.nextToken());
		}
	}
}