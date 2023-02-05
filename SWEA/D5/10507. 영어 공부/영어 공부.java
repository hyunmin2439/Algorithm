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
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = 0, T, n, p, days[] = new int[200_001], acc[] = new int[200_002]; // 마지막 날짜 Overflow 방지
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(in.readLine());
			for(int i = 1; i <= n; i++) {
				days[i] = Integer.parseInt(st.nextToken());
				acc[i] = acc[i - 1] + days[i] - days[i - 1] - 1; // 누적으로 빈 날짜 체크
			}
			
			sb.append("#").append(t).append(" ").append(twoPointer(days, acc, n, p)).append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
	
	private static int twoPointer(int[] days, int[] acc, int n, int p) {
		int max = 0, left = 1, right = 1;
		
		while(right <= n) {
			while(right <= n && acc[right] - acc[left] <= p) right++;
			
			max = Math.max(max, (days[right - 1] - days[left] + 1) + (p - (acc[right - 1] - acc[left]))); // 날짜 차이 + 남은 해킹 횟수
			
			while(left < right && acc[right] - acc[left] > p) left++; // right가 200_001까지 가면 Overflow 발생할 수 있기 때문에 200_002로 설정
																	  // acc[200_001] - acc[left]는 음수 따라서 로직에 문제는 없음
		}
		
		return max;
	}
}