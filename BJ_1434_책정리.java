import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14272KB / Time:136ms
public class BJ_1434_책정리 {

	private static int N, M;
	private static int[] boxes, books;
	
	public static void main(String[] args) throws Exception {
		input();
		
		int res = solve();
		
		System.out.print(res);
	}
	
	private static int solve() {
		int i = 0, j = 0, res = 0;
		
		while(j < M) {
			if(boxes[i] >= books[j]) 
				boxes[i] -= books[j++];
			else 
				i++;
				
		}
		
		for(int k = 0; k < N; k++) {
			res += boxes[k];
		}
		
		return res;
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		boxes = new int[N];
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++)
			boxes[i] = Integer.parseInt(st.nextToken());
			
		books = new int[M];
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < M; i++)
			books[i] = Integer.parseInt(st.nextToken());
		
		in.close();
	}

}
