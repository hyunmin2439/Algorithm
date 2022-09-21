package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1331_나이트투어 {

	public static void main(String[] args) throws Exception {
		String[] paths = input();
		boolean[][] visited = new boolean[7][7];
		
		int startY = paths[0].charAt(0) - 'A', 
				startX = paths[0].charAt(1) - '0', 
				currY = startY, currX = startX, nextY, nextX;
		
		for(int i = 1; i < 36; i++) {
			nextY = paths[i].charAt(0) - 'A';
			nextX = paths[i].charAt(1) - '0';
			
			if( visited[nextY][nextX] || !isValid(currY, currX, nextY, nextX) ) {
				System.out.print("Invalid");
				return;
			}
			
			visited[nextY][nextX] = true;
			currY = nextY;
			currX = nextX;
		}
		
		// 시작점으로 돌아 올 수 있는지 체크
		if( visited[startY][startX] || !isValid(currY, currX, startY, startX) ) {
			System.out.print("Invalid");
			return;
		}
		
		
		System.out.print("Valid");
	}
	
	private static String[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] paths = new String[36];
		
		for(int i = 0; i < 36; i++) {
			paths[i] = in.readLine();
		}
		
		return paths;
	}
	
	private static boolean isValid(int currY, int currX, int nextY, int nextX) {
		int diffY = Math.abs(currY - nextY), diffX = Math.abs(currX - nextX);
		
		if( (diffY == 1 || diffY == 2) && (diffX == 1 || diffX == 2) && (diffY + diffX) == 3 ) return true;
		
		return false;
	}
	


}
