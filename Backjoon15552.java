package solved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/* Backjoon15552
 * 빠른 A + B java 8 : 1.5초 제한
 * 
 * 입력
 * 첫 줄에 테스트케이스의 개수 T가 주어진다. 
 * T는 최대 1,000,000이다. 다음 T줄에는 각각 두 정수 A와 B가 주어진다. 
 * A와 B는 1 이상, 1,000 이하이다.
 * 
 * 출력 = A + B
 */

/*
 * 버퍼(buffer)
 * 데이터를 한 곳에서 다른 한 곳으로 전송하는 동안 
 * 일시적으로 그 데이터를 보관하는 임시 메모리 영역
 * 
 * 버퍼 플러시(buffer flush)
 * 버퍼에 남아 있는 데이터를 출력시킴(버퍼를 비우는 동작)
 * 
 * 입력 : BufferedReader
 * 출력 : BufferedWriter
*/

/* 
 * 키보드 입력시 마다 한 문자씩 버퍼로 전송.
 * 버퍼가 가득 차거나, 개행 문자가 나타나면 버퍼의 내용을 한 번에 전송
 * 하드 / 외부 장치의 데이터 입출력은 엄청 느림.
 * 그래서 문자 입력시 마다 데이터를 전송하는
 * Scanner 보다 상대적으로 BufferedReader가 더 빠름.
*/

/*
 * BufferedWriter 의 경우 버퍼를 잡아 놓았기 때문에 
 * 반드시 flush() / close() 를 반드시 호출해 주어 뒤처리를 해줘야 한다.
 * 그리고 bw.write에는 System.out.println();과 같이 자동개행기능이 없기때문에 
 * 개행을 해주어야할 경우에는 \n를 통해 따로 처리해야 함
 */

public class Backjoon15552 {

	public static void main(String[] args) {
		int T, A, B;
		String[] lines;
		
		try { // 예외처리 필수 || throwsIOException
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			T = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < T; i++) {
				lines = br.readLine().split(" ");
				A = Integer.parseInt(lines[0].trim());
				B = Integer.parseInt(lines[1].trim());
				
				bw.write(A + B + "\n");//출력
				//bw.newLine(); //줄바꿈 => 사용시 이상한 문자가 나옴(  . ǹ d)
			}
			bw.flush();//남아있는 데이터를 모두 출력시킴
			
			//스트림을 닫음
			br.close();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}
