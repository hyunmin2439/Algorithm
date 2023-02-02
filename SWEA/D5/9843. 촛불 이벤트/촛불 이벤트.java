import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		long N, mid, low, high;
		int t = 0, T;
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			N = Long.parseLong(in.readLine());
			
			low = 1;
			high = Math.min(N, 1_414_213_562); // 10^18을 넘는 최소값
			
			while(low < high) {
				// 오버플로우의 위험 방지 및 low + high 음수 시 나누기 2 다르게 동작 방지
				mid = low + ((high - low) >> 1);
				
				if((mid * (mid + 1)) >> 1 < N) // 조건
					low = mid + 1;
				else
					high = mid;
			}
			
			mid = (low * (low + 1)) >> 1;
			sb.append("#").append(t).append(" ").append(mid == N ? low : -1).append("\n");
		}
		
		out.write(sb.toString());
		out.flush();		
	}
}