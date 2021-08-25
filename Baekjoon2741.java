package uploaded;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/* Backjoon2741
 * 입력 (1 <= N <= 100,000)
 * 
 * 출력
 * 첫째 줄부터 N번째 줄 까지 차례대로 출력한다.
 */

public class Baekjoon2741 {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			int N = Integer.parseInt(br.readLine());
			
			for(int i = 1; i <= N; i++) {
				bw.write(i + "\n");//출력
			}
			bw.flush();
			br.close();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
