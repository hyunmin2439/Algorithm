import java.io.*;

/*
 * 세 점이 주어졌을 때 직사각형 만들기 위해 
 * 필요한 네번째 점 찾는 프로그램
 * 
 * 입력
 * x1 y1 (1 <= x1~3 , y1~3 좌표 <= 1000)
 * x2 y2
 * x3 y3
 * 
 * 출력
 * x4 y4
 * 
 */

public class Baekjoon3009 {

	public static void main(String[] args) throws IOException {
		int[] x = new int[3];
		int[] y = new int[3];
		int resX, resY;
		
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		// input
		for(int i = 0; i < 3; i++) {
			String[] line = br.readLine().trim().split(" ");
			
			x[i] = Integer.parseInt(line[0].trim());
			y[i] = Integer.parseInt(line[1].trim());
		}
		
		resX = x[0]; resY = y[0];
		
		if( resX == x[1] ) resX = x[2];
		else if( resX == x[2] ) resX = x[1];
		
		if( resY == y[1] ) resY = y[2];
		else if( resY == y[2] ) resY = y[1];

		bw.write( resX + " " + resY );
		
		br.close();
		bw.close();
	}
}
