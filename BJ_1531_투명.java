package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 단순한 구현문제
 * 
 * Memory:14224KB / Time:132ms
 */

public class BJ_1531_투명 {
	
	static int N, M;
	
	static int[][] numOfPaperOnTop;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numOfPaperOnTop = new int[101][101]; // 0: dummy
				
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			
			putPaperUp(sx, sy, ex, ey);
		}
		
		System.out.println( countNumOfInvisiblePicture() );
		
		in.close();
	}

	private static void putPaperUp(int sx, int sy, int ex, int ey) {
		for(int y = sy; y <= ey; y++) {
			for(int x = sx; x <= ex; x++) {
				numOfPaperOnTop[y][x]++;
			}
		}
	}
	
	private static int countNumOfInvisiblePicture() {
		int numOfInvisiblePicture = 0;
		
		for(int y = 1; y <= 100; y++) {
			for(int x = 1; x <= 100; x++) {
				if(numOfPaperOnTop[y][x] > M) {
					numOfInvisiblePicture++;
				}
			}
		}
		
		return numOfInvisiblePicture;
	}

}
