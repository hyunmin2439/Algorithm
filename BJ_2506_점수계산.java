package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2506_점수계산 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		
		int N = Integer.parseInt(in.readLine());
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int tot = 0, score = 1;
		for(int i = 0; i < N; i++) {
			if("0".equals(st.nextToken())) {
				score = 1;
				continue;
			}
			
			tot += score;
			score++;
		}
		
		System.out.print(tot);
	}

}
