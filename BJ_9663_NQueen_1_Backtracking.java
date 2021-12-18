import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * N-Queen. Backtracking
 * 
 * 1. 같은 행 중복을 배제한체 열 1차원 배열 사용.
 * 
 * 2. 열의 사용 여부를 체크하는 boolean 배열을 사용.
 * 
 * 3. 두 좌표의 행과 열 차이가 같으면 같은 대각선.
 * 
 * 4. Depth First Search로 끝까지 탐색시 경우의 수 +1.
 * 
 * 5. boolean 배열과 대각선 체크 여부로 가지치기.
 * 
 * Memory:14832KB / Time:3684ms
 */

public class BJ_9663_NQueen_1_Backtracking {
	
	static int N, numOfCase;
	
	static int[] queenColPos;
	static boolean[] usedColumn;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		queenColPos = new int[N];
		usedColumn = new boolean[N];
		
		dfs(0);
		
		System.out.print(numOfCase);
		
		in.close();
	}

	private static void dfs(int row) {
		if(row == N) {
			numOfCase++;
			return;
		}
				
		for(int col = 0; col < N; col++) {
			// 사용중인 열이거나 대각선 체크후 사용 불가능하면
			if( usedColumn[col] || !isAvailColPos(row, col) ) continue;
			
			usedColumn[col] = true; // 열 사용중 표기
			queenColPos[row] = col; // 퀸 열 위치 표기
			
			dfs(row + 1);
			
			usedColumn[col] = false; // 열 사용중 해제
			// 퀸 열 위치 표기는 덮어쓰게 되어있으므로 따로 초기화 불 필요
		}
	}

	// 대각선 체크후 가능한 위치인지 체크
	private static boolean isAvailColPos(int row, int col) {
		for(int i = 0; i < row; i++) {
			
			// 행과 열의 차이가 같으면 같은 대각선에 위치한 것.
			if( Math.abs(row - i) == Math.abs(col - queenColPos[i])) return false;
		}
		
		return true;
	}
	
}
