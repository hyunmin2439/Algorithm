package solved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 입력
 * A B (0 < A, B < 10)
 * ... 끝 표시 없음
 * 
 * 출력
 * A + B
 */

public class BJ_10951_AaddB4 {

	public static void main(String[] args) {
		int   A; 
		int	  B;
		String line;
		String [] splitLine;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		try {
			// while(sc.hasNextInt()) { } // 스캐너 이용시
			while( ( line = br.readLine() ) != null ) {
				splitLine = line.split(" ");
				A = Integer.parseInt(splitLine[0].trim());
				B = Integer.parseInt(splitLine[1].trim());
				bw.write(A + B + "\n");
			}
			
			bw.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
