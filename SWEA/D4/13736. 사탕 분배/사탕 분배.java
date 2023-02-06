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

		int t = 0, T, K;
		long A, B, sum;

		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			st = new StringTokenizer(in.readLine());
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			sum = A + B;
			A = A * pow(K, sum) % sum;
			B = sum - A;

			sb.append("#").append(t).append(" ").append(Math.min(A, B)).append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
	
	private static long pow(int K, long sum) {
		long res = 1, p = 2;
		
		while(K > 0) {
			if((K & 1) != 0) // 1의 자리가 있으면 홀수
				res = (res * p) % sum;
			p = (p * p) % sum;
			K >>= 1;
		}
		
		return res;
	}
}