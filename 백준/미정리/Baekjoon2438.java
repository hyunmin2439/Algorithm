package uploaded;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//Backjoon2438
//N개의 별찍기

public class Baekjoon2438 {

	public static void main(String[] args) {
		int N;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		try {
			N = Integer.parseInt(br.readLine());
			
			for(int i = 1; i <= N; i++) {			
				for(int j = 1; j <= i; j++) {
					bw.write("*");
				}
				bw.write("\n");
			}
			
			bw.flush(); // 마지막에 버퍼에 들어있는 데이터 출력
		} catch (Exception e) {
			
		}
	}
}
