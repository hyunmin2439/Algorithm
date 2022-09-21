package uploaded;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// Backjoon2439
// N개의 별찍기

public class Baekjoon2439 {

	public static void main(String[] args) {
		int N;
		String star = "";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		try {
			N = Integer.parseInt(br.readLine());
			
			for(int i = 1; i <= N; i++) {			
				for(int j = N - i; j > 0; j--) {
					bw.write(" ");
				}
				
				star += "*";
				bw.write(star);
				bw.write("\n");
			}
			
			bw.flush(); // 마지막에 버퍼에 들어있는 데이터 출력
		} catch (Exception e) {
			
		}
	}
}
