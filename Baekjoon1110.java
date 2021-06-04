package solved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 입력 : N (0 <= N <= 99 )
 * 
 * 조건
 * 1. N 10보다 작다면 앞자리에 0을 붙인다.
 * 2. 10의 자리 + 1의 자리 = SumDit
 * 3. N의 1의 자리(10)  SumDit의 1의 자리(1) = resDit
 * 4. Y가 N과 같아질 때까지의 사이클 횟수
 * 
 * 출력 : 사이클 횟수
 */

public class Baekjoon1110 {

	public static void main(String[] args) {
		int N; // 입력
		int tenDit, oneDit, sumDit, resDit;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		try {
			N = Integer.parseInt(br.readLine());
			resDit = N;
			int cnt = 0;
			
			do {
				// 조건1 ~ 2
				tenDit = resDit / 10;
				oneDit = resDit % 10;
				sumDit = tenDit + oneDit;
				//System.out.printf("ten:%d  one:%d  sum:%d\n", tenDit, oneDit, sumDit);
				
				// 조건 3
				tenDit = oneDit * 10;
				oneDit = sumDit % 10;
				resDit = tenDit + oneDit;
				//System.out.printf("ten:%d  one:%d  sum:%d\n", tenDit, oneDit, resDit);
				
				cnt++;
				//System.out.println("회전수: " + i + "\n");
				
			}while(N != resDit);
			
			bw.write( String.valueOf(cnt) );
			bw.flush();
			
			br.close();
			bw.close();
			
//			String.valueOf() VS toString() 
//			차이점 : null값에 따른 NPE의 발생 유무
			
//			String.valueOf() 
//			- 파라미터가 null이면 문자열 "null"을 만들어서 반환
//			- null체크 방법은 "null".equals(string) 형태로 체크
			
//			toString() 
//			- null이면 NPE를 발생, Object에 담긴 값이 String이 아니여도 출력
			
//			null로 인해 발생된 에러는 시간이 지나고, 타인의 소스인경우 디버깅하기 어렵고 
//			어떤의미를 내포하고 있는지 판단하기 어렵다. 
//			때문에. NPE를 방지하기 위해 toString보다는 valueOf를 사용하는 것을 추천
						
		} catch (Exception e) {
			
		}

	}

}
