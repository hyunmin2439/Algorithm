package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1500_최대곱 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int S = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] nums = new int[K];
		
		int tmp = S / K;
		for(int i = 0; i < K; i++) {
			nums[i] = tmp;
		}
		
		tmp = S - ((S / K) * K);
		for(int i = 0; i < tmp; i++) {
			nums[i]++;
		}
		
		long res = 1;
		
		for(int i = 0; i < K; i++) {
			res *= nums[i];
		}
		
		System.out.print(res);
	}

}
