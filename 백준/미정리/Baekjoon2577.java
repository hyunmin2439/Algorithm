package solved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/* 입력
 * A, B, C (100 <= A, B, C < 1000)
 * 
 * A x B x C = ABC
 * 
 * 출력
 * 0 ~ 9 숫자 몇번씩 쓰였는지 한줄로 출력
 */

public class Baekjoon2577 {
	
	public static void main(String[] args) {
		int A, B, C, result;
		int[] numCnt = new int[10];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
				
		try {
			A = Integer.parseInt(br.readLine().trim());
			B = Integer.parseInt(br.readLine().trim());
			C = Integer.parseInt(br.readLine().trim());
			
			result = A * B * C;
			
			while(result != 0) {
				numCnt[result % 10]++;
				result /= 10;
			}
			
			for(int num : numCnt)
				bw.write(num + "\n");
			
			bw.flush();
			br.close();
			bw.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
