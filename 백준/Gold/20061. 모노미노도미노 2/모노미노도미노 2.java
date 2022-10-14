import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N, x, y, t, score = 0, cnt = 0, green[][] = new int[6][4], blue[][] = new int[6][4];
		
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			dropBlock(green, y, t);
			dropBlock(blue, x, t == 1 ? 1 : (t == 2 ? 3 : 2) );
			
			score += getScore(green);
			score += getScore(blue);
			
			deleteLine(green);
			deleteLine(blue);
		}
		
		cnt += countBlock(green);
		cnt += countBlock(blue);
		
		System.out.println(score);
		System.out.println(cnt);
	}
	
	private static int countBlock(int[][] board) {
		int cnt = 0;
		
		for(int r = 5; r >= 0; r--) {
			for(int c = 0; c < 4; c++) {
				if(board[r][c] == 1) cnt++;
			}
		}
		
		return cnt;
	}
	
	
	private static void dropBlock(int[][] board, int c, int t) {
		int r = 0, c2 = c + 1;
		
		switch(t) {
		case 1:
			while(r < 6 && board[r][c] == 0) r++;
			board[r - 1][c] = 1;
			break;
		case 2:
			while(r < 6 && board[r][c] == 0 && board[r][c2] == 0) r++;
			board[r - 1][c] = board[r - 1][c2] = 1;
			break;
		case 3:
			while(r < 6 && board[r][c] == 0) r++;
			board[r - 2][c] = board[r - 1][c] = 1;
			break;
		}
	}
	
	private static int getScore(int[][] board) {
		int score = 0;
		boolean flag = true;
		
		for(int r = 5; r >= 0; r--) {
			flag = true;
			for(int c = 0; c < 4; c++) {
				if(board[r][c] == 0) {
					flag = false;
					break;
				}
			}
			
			if(!flag) continue;
			
			score++;
			
			gravity(board, r);
			
			r++;
		}
		
		return score;
	}
	
	private static void deleteLine(int[][] board) {
		int deleteCnt = 0;
		
		for(int r = 0; r <= 1; r++) {
			for(int c = 0; c < 4; c++) {
				if(board[r][c] == 1) {
					deleteCnt++;
					break;
				}
			}
		}
		
		while(deleteCnt-- > 0) {
			gravity(board, 5);
		}
	}
	
	private static void gravity(int[][] board, int startR) {
		for(int r = startR; r > 0; r--) {
			for(int c = 0; c < 4; c++) {
				board[r][c] = board[r - 1][c];
			}
		}
		
		for(int c = 0; c < 4; c++) {
			board[0][c] = 0;
		}
	}

}