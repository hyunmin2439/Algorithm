import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder(50);
		StringTokenizer st;
		
		char[] btree;
		int t = 0, N;
		
		while(t++ < 10) {
			N = Integer.parseInt(in.readLine());
			btree = new char[N + 1];
			
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(in.readLine());
				st.nextToken();
				btree[i] = st.nextToken().charAt(0);
			}
			sb.append("#").append(t).append(" ")
			.append(postOrder(btree, N, 1) == '1' ? '1' : '0')
			.append("\n");
		}
		out.write(sb.toString());
		out.flush();
	}
	
	private static char postOrder(char[] btree, int N, int idx) {
		if(idx * 2 > N) return btree[idx];
		
		if(
				postOrder(btree, N, idx * 2) >= '0' &&		// 왼쪽 숫자
				postOrder(btree, N, idx * 2 + 1) >= '0' &&  // 오른쪽 숫자
				btree[idx] < '0' // 연산자
		) return '1'; // 계산은 필요X

		return 0; // 0은 null
	}
}