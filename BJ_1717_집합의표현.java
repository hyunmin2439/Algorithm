
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1717_집합의표현 {

	static int N, M;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		makeSet();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cal = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(cal == 0) union(a, b);
			else System.out.println(find(a) == find(b) ? "yes" : "no" );
		}
		
		br.close();
	}

	private static void makeSet() {
		parents = new int[N + 1];
		
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int x) {
		if(x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}

	private static void union(int a, int b) {
		int ap = find(a);
		int bp = find(b);
		
		if(ap > bp) parents[bp] = ap;
		else parents[ap] = bp;
	}
}
