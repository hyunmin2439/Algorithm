package solved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/* Backjoon2742
 * N부터 1까지 출력
 */

public class Backjoon2742 {

	public static void main(String[] args) {

		try { // 예외처리 필수 || throwsIOException
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			int N = Integer.parseInt(br.readLine());
			
			for(int i = N; i > 0; i--) {
				bw.write(i + "\n");//출력
			}
			
			bw.flush();//남아있는 데이터를 모두 출력시킴
			
			//스트림을 닫음
			br.close();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
