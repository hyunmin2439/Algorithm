import java.io.*;

/*
 * 한수는 지금 (x, y)에 있다. 
 * 직사각형의 왼쪽 아래 꼭짓점은 (0, 0)에 있고, 
 * 오른쪽 위 꼭짓점은 (w, h)에 있다. 직사각형의 
 * 경계선까지 가는 거리의 최솟값을 구하는 프로그램을 작성
 * 
 * 입력
 * x y w h (int)
 * 1 ≤ w, h ≤ 1,000
 * 1 ≤ x ≤ w-1
 * 1 ≤ y ≤ h-1
 * 
 * 정답 출력
 */

public class Baekjoon1085 {

	public static void main(String[] args) {
		int x, y, w, h, result;
		
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		try {
			String[] line = br.readLine().trim().split(" ");
			
			x = Integer.parseInt(line[0]);
			y = Integer.parseInt(line[1]);
			w = Integer.parseInt(line[2]);
			h = Integer.parseInt(line[3]);
			
			result = x;
			
			if( result > y ) result = y;
			if( result > w - x ) result = w - x;
			if( result > h - y ) result = h - y;
			
			bw.write( String.valueOf(result) );
			
		} catch (IOException e) {
		} finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
			}
		}
	}

}
