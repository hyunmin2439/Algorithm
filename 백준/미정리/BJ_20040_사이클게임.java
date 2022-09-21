import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20040_사이클게임 {

	static int n, m;
	
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		set();
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if( union(a, b) ) {
				System.out.print(i + 1);
				return;
			}
		}
		
		System.out.print(0);
		
		in.close();
	}
	
	private static void set() {
		parent = new int[n];
		
		for(int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}
	
	private static int find(int x) {
		if(x == parent[x]) return x;
		
		return parent[x] = find(parent[x]);
	}

	private static boolean union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		
		if(aParent == bParent) return true;
		
		if(aParent > bParent)
			parent[bParent] = aParent;
		else
			parent[aParent] = bParent;
		
		return false;
	}
}
