package solved;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/* 
 * 입력
 * 대소문자 + 띄워쓰기 이루어진 문자열 ( <= 1,000,000 )
 * 
 * 출력
 * 띄워쓰기로 구분된 문자 개수 출력 (같은 단어도 모두 포함)
 * 
 * String[] line = br.readLine().trim().split(" ");
 * 사용하니 틀렸음. 아마 메모리 문제에서 걸리는 것 같음.
 */

public class BJ_1152_단어의개수 {

	public static void main(String[] args) throws IOException {
		// 내가 푼 방법
		// 메모리 21036 / 시간 252ms
		int cnt = 0, pos;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String line = br.readLine().trim();
		
		// 문자가 아예 없거나, 한글자만 있을 경우도 대비
		if(line.length() == 0) {
			pos = -1;
		}
		else {
			pos = line.indexOf(" ");
			cnt++;
		}
		
		// 2글자 이상
		while( pos != -1 ) { // -1 : not found
			pos = line.indexOf(" ", pos + 1); // pos + 1 위치에서 찾아라.
			//System.out.println(pos);
			cnt++;
		}
		
		bw.write( cnt + "" );
		bw.flush();
		br.close();
		bw.close();
		
		
		// StringTokenizer( String str )
		// 절달된 매개변수 str을 기본(default) delim
		// 기본 delimiter는 공백 문자들인 " \t\n\r\t" 

		// 다른사람의 간단한 코드
		// 메모리 30532KB / 시간 484ms
		Scanner sc = new Scanner(System.in);
		StringTokenizer s = new StringTokenizer(sc.nextLine());
		System.out.println(s.countTokens());
		
	}
}
