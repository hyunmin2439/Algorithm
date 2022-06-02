import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 그리디 방식으로 매번 계산 했을 때 큰 값으로
 * 
 * 값이 같다면 숫자가 작은 것을 먼저 택하는 방식을 했으나
 * 
 * 답이 틀림. 그리디 방식이 아닌 dfs를 사용해야 한다.
 */

public class Main {
	
	private static int max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		List<Integer> list = new LinkedList<>();
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		dfs(list, N - 1, 0);
		
		System.out.print(max);
	}
	
	private static void dfs(List<Integer> list, int N, int sum) {
		if(N < 2) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int i = 1; i < N; i++) {
			sum += list.get(i - 1) * list.get(i + 1);
			int energy = list.remove(i);
			dfs(list, N - 1, sum);
			list.add(i, energy);
			sum -= list.get(i - 1) * list.get(i + 1);
		}
	}
}