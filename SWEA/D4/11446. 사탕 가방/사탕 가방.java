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
		
		int t = 0, T, N;
		long M, max, candy[] = new long[100];
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Long.parseLong(st.nextToken());
			max = 0;
			
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < N; i++) {
				candy[i] = Long.parseLong(st.nextToken());
				max = Math.max(max, candy[i]);
			}
			
			sb.append("#").append(t).append(" ").append(binarySearch(candy, N, M, max)).append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
	
	private static long binarySearch(long[] candy, int N, long M, long max) {
		long cnt, low = 1L, high = max, mid = 0L;
		
		while(low <= high) {
			mid = (low + high) >> 1;
		
			cnt = 0L;
			for(int i = 0; i < N; i++)
				cnt += candy[i] / mid;
			
			if(M <= cnt)
				low = mid + 1;
			else
				high = mid - 1;
		}
		
		return high;
	}

}