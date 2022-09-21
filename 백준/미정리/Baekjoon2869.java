package solved;

import java.io.*;

/*
 * 달팽이는 낮에 A미터 올라가고 밤에 B미터 미끄러진다.
 * 정상에 올라가고 나서는 미끄러지지 않는다
 * V미터 막대를 올라가는데 얼마나 걸리는가?
 * 시간초 제한 0.15초
 * 
 * 입력
 * A B V (1 ≤ B < A ≤ V ≤ 1,000,000,000)
 * 
 * ex)
 * 2 1 5  => 4
 * 5 1 6  => 2
 */

public class Baekjoon2869 {

	public static void main(String[] args) throws IOException {
		long A, B, V, res = 1;
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		String[] line = br.readLine().trim().split(" ");
		
		A = Integer.parseInt( line[0].trim() );
		B = Integer.parseInt( line[1].trim() );
		V = Integer.parseInt( line[2].trim() );
		
		/*
		if( V - A > 0) {
			res = (V - A) / (A - B);
			res = res == 0 ? 1 : res;
			res++;
		}
		V : 8 / A : 3 / B : 1 => 4일 나와야 함
		(8 - 3 = 5) / (3 - 1 = 2)
		5 / 2 => 2 + 1 => 3 에러
		소수점을 버려 버리면 문제 생김
		*/
		
		if( V - A > 0) {
			V -= A;
			res = (int)( (double)V / (A - B) + 0.999999 );
			res++;
		}
		
		bw.write( String.valueOf( res ) );
		
		br.close();
		bw.close();
	}

}
