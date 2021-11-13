package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 거듭 제곱 (logN) 시간 복잡도로 처리.
 * 
 * 10으로 나눈 나머지를 구하는 문제.
 * 
 * 다만, 10으로 나누면 나머지가 0이되는 경우가 있기 때문에
 * 
 * 이 경우는 10번째 컴퓨터가 마지막 데이터 처리한 것이다.
 * 
 * Memory:14444KB / Time:156ms
 */

public class BJ_1009_분산처리_1_pow {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		StringTokenizer st;
		while(T-- > 0) {
			st = new StringTokenizer(in.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			int res = pow(num, e);
			
			System.out.println( res == 0 ? 10 : res );
		}
		
		in.close();
	}

	private static int pow(int num, int e) {
		int ans = 1;
		
		while(e > 0) {
			if(e % 2 == 1)
				ans = (ans * num) % 10;
			
			e >>= 1;
			num = (num * num) % 10;
		}
		
		return ans % 10;
	}

}
