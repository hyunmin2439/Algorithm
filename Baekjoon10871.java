package uploaded;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/* 입력데이터
   N  X
   10 5
   1 10 4 9 2 3 8 5 7 6
   
   출력
   X보다 작은 수를 입력받은 순서대로 공백으로 구분해 출력
*/

public class Baekjoon10871 {

	public static void main(String[] args) {
		int 	  N;
		int		  X;
		String [] line;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		try {
			line = br.readLine().split(" "); // 10 5	
			
			N = Integer.parseInt(line[0].trim());
			X = Integer.parseInt(line[1].trim());
		
			// line(stack 메모리) ->  split이 heap메모리에 새로운 배열을 생성
			// 이전의 10 5의 배열은 JVM의 garvage collection이 수거하여 제거
			line = br.readLine().split(" "); // 1 10 4 9 2 3 8 5 7 6
			
			for(int i = 0; i < N; i++) {	
				if( Integer.parseInt(line[i]) < X ) 
					bw.write(line[i] + " ");
			}
			
			bw.flush();
			
		} catch (Exception e) {
			
		}
	}
}
