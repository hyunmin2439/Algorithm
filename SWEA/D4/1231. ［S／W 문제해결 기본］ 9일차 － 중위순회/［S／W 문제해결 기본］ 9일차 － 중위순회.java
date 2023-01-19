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
		StringBuilder sb = new StringBuilder(1100), word;
		StringTokenizer st;
		
		char[] btree;
		int t = 0, N;
		
		while(t++ < 10) {
			N = Integer.parseInt(in.readLine());
			btree = new char[N + 1];
			word = new StringBuilder(N + 3); // 한 케이스당 최대 100글자 + 끝 공백 1 + 여유 2
			
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(in.readLine());
				st.nextToken();
				btree[i] = st.nextToken().charAt(0);
				// 완전 이진 트리 - 자식이 누구인지 알 필요 없음
			}
			
			inOrder(word, btree, N, 1);
			
			sb.append("#").append(t).append(" ").append(word.toString()).append("\n");
		}
		out.write(sb.toString());
		out.flush();
	}
	
	private static void inOrder(StringBuilder word, char[] btree, int N, int idx) {
		if(idx > N) return;
		
		// 왼쪽 중앙 오른쪽
		inOrder(word, btree, N, idx * 2); // 왼쪽 자식
		word.append(btree[idx]); // 자기 자신
		inOrder(word, btree, N, idx * 2 + 1); // 오른쪽 자식
	}
}