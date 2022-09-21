package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_10039_평균점수 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int totScore = 0;
		
		for(int i = 0; i < 5; i++) {
			int score = Integer.parseInt(in.readLine());
			totScore += score >= 40 ? score : 40;			
		}
		
		System.out.println(totScore / 5);
	}

}
