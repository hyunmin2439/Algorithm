package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Memory:14180ms / Time:128ms
public class BJ_1075_나누기_1 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine()) / 100 * 100;
		int F = Integer.parseInt(in.readLine());
		
		while(N % F != 0) N++;
			
		int res = N % 100;
		
		if( res < 10 ) System.out.print(0); // 한자리 결과면 앞에 0 붙이기
		System.out.print(res);
		
		in.close();
	}

}
