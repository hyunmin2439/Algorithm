package solved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

/*
 * 입력
 * 10 줄의 숫자 입력 받음
 * 
 * 출력
 * 각 숫자를 42로 나누었을 때 서로 다른 나머지가 몇개 있는지 출력 
 */

public class Baekjoon3052 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// HashSet 사용
		// Generic 1.6부터 지원 <T>
		// 1.7 부터는 앞에 선언된 타입으로 컴파일러에서 
		// 추측이 가능 하기 때문에 뒤에 생성자에서 제네릭 생략 가능하다.
		HashSet<Integer> nums = new HashSet<>(); // 생성자 제네릭 생략.

		try {
			for (int i = 0; i < 10; i++) {
				nums.add( Integer.parseInt( br.readLine().trim() ) % 42 );
			}
			
			bw.write( String.valueOf(nums.size()) );
			bw.flush();
			br.close();
			bw.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		/*
		int cnt = 0;
		int[] num = new int[10];
		
		try {
			for (int i = 0; i < num.length; i++) {
				num[i] = Integer.parseInt( br.readLine().trim() );
				
				num[i] %= 42;
				
				cnt++;
				for(int j = 0; j < i; j++) {
					if(num[i] == num[j]) {
						cnt--; break;
					}
				}
			}
			
			bw.write(String.valueOf(cnt));
			bw.flush();
			br.close();
			bw.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		*/

	}
}
