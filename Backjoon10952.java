package solved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 입력
 * A B (0 < A, B < 10)
 * 0 0 마지막
 * 
 * 출력
 * A + B (0 0 출력X)
 */

public class Backjoon10952 {

	public static void main(String[] args) {
		int 	  A; 
		int		  B;
		String [] line;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		try {
			// do - while문이라 초기화 불필요
			do {
				line = br.readLine().split(" ");
				A = Integer.parseInt(line[0].trim());
				B = Integer.parseInt(line[1].trim());
				
				if(A != 0 && B != 0) bw.write(A + B + "\n");
				else break;
				
			}while(true);
			
			
			bw.flush();
			
		} catch (Exception e) {
			
		}

	}

}
