package solved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 입력
 * N
 * N개의 점수
 * 
 * 처리
 * M = N개의 점수중 최고 점수
 * 점수 = 점수 / M * 100
 * 
 * 출력
 * 새로운 점수의 평균값
 * (출력값의 절대오차 또는 상대오차가 10^-2 이하이면 정답)
 */

public class Baekjoon1546 {

	public static void main(String[] args) {
		float tot = 0;
		float max = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		try {
			int N = Integer.parseInt( br.readLine().trim() );
			
			// 오차값이 더 낮다면 Double 써야 함
			float [] score = new float [N];
			
			String [] line = br.readLine().split(" ");
			
			for (int i = 0; i < N; i++) {				
				score[i] = Float.parseFloat( line[i] );
				tot += score[i];
				if(score[i] > max) max = score[i];
			}
			
			/* 다른 사람의 정렬을 이용한 방법
			for (int i = 0; i < N; i++) {				
				score[i] = Float.parseFloat( line[i] );
				tot += score[i];
			}
			Arrays.sort(score);
			tot = tot / score[N - 1] * 100 / N;
			*/
			
			tot = tot / max * 100 / N;
			
			bw.write(tot + ""); // "" String으로 변환
			bw.flush();
			br.close();
			bw.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
