package solved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 입력
 * 첫째 줄부터 아홉 번째 줄까지 한 줄에 하나의 자연수가 주어진다. 
 * 주어지는 자연수는 100 보다 작다.
 * 
 * 출력
 * 최대값
 * 몇번째
 */

public class Baekjoon2562 {

	public static void main(String[] args) {
		int num, cnt = 0;
		int max = 0, resCnt = 0;
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		try {
			
			/* 횟수 정해져 있는 방식
			for(int i = 1; i <= 9; i++) {
				line = br.readLine();
				num = Integer.parseInt( line.trim() );
				
				if(num > max) {
					max = num; resCnt = i;
				}
			}
			*/
			
			
			// 횟수가 정해져 있지 않을 때 하는 방식
			/*
			while( ( line = br.readLine() ) != null ) {
				cnt++;
				num = Integer.parseInt( line.trim() );
				
				if(num > max) {
					max = num; resCnt = cnt;
				}
			}
			*/
			

			// for문으로 표현, while이 더 깔끔해 보인다.
			for(int i = 1; ( line = br.readLine() ) != null; i++) {
				num = Integer.parseInt(line);
				
				if(num > max) {
					max = num; resCnt = i;
				}
			}

			
			bw.write(max + "\n" + resCnt);
			bw.flush();
			
			br.close();
			bw.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
