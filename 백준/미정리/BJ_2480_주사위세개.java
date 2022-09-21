package no_upload;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2480_주사위세개 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int[] dice = new int[7];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int num, maxNum = 1, sameNum = 1, sameCnt = 1;
		for(int i = 0; i < 3; i++) {
			num = Integer.parseInt(st.nextToken());
			
			dice[num]++;
		}
		
		for(int i = 1; i <= 6; i++) {
			if(dice[i] > sameCnt) {
				sameCnt = dice[i];
				sameNum = i;
			}
			
			if(dice[i] > 0 && i > maxNum)
				maxNum = i;
		}

		int res = 0;
		
		switch(sameCnt) {
		case 1: res = maxNum * 100; break;
		case 2: res = 1000 + sameNum * 100; break;
		case 3: res = 10000 + sameNum * 1000; break;
		}
		
		System.out.print(res);
	}

}
