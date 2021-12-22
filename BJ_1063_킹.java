package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 주어진 조건대로 구현하는 시뮬레이션 문제
 * 
 * Memory:16268KB / Time:152ms
 */

public class BJ_1063_킹 {
	
	static int N;
	static boolean[][] isExistStone;
	
	static int[] kingPos, stonePos;
	
	static String[] direct = { "T", "B", "L", "R", "LT", "RT", "LB", "RB" };
	static int[] dy = { 1, -1, 0, 0, 1, 1, -1, -1 };
	static int[] dx = { 0, 0, -1, 1, -1, 1, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		inputAndInit(in);
		
		for(int i = 0; i < N; i++) {
			int moveDir = getDirectIndex(in.readLine());
			
			moveKing(moveDir);
		}
		
		System.out.println( String.valueOf((char)(kingPos[1] + 'A')) + (kingPos[0] + 1) );
		System.out.print( String.valueOf((char)(stonePos[1] + 'A')) + (stonePos[0] + 1) );
		
		in.close();
	}

	private static void inputAndInit(BufferedReader in) throws Exception {
		StringTokenizer st = new StringTokenizer(in.readLine());
		isExistStone = new boolean[8][8];
		kingPos = new int[2];
		stonePos = new int[2];
		
		char[] kingPosStr = st.nextToken().toCharArray();
		kingPos[1] = kingPosStr[0] - 'A'; // 알파벳이 열
		kingPos[0] = kingPosStr[1] - '1'; // 숫자가 행, 입력은 1 ~ 8, 배열 0 ~ 7 사용
		
		char[] stonePosStr = st.nextToken().toCharArray();
		stonePos[1] = stonePosStr[0] - 'A';
		stonePos[0] = stonePosStr[1] - '1';
		
		// KING을 체스판에 표시할 필요는 없다.
		// 체스판을 돌이 있는지 없는지 체크하기 위한 boolean 배열로 변경
		// KING이 이동한 자리에 돌이 있는지 없는지만 확인하면 된다.
		isExistStone[ stonePos[0] ][ stonePos[1] ] = true;
		
		N = Integer.parseInt(st.nextToken());
	}
	
	// 문자열로 된 방향 값을 direct 배열에서 index 값으로 변환
	private static int getDirectIndex(String moveDirStr) {
		int moveDir = moveDirStr.length() == 1 ? 0 : 4;
		
		while( !direct[moveDir].equals(moveDirStr) ) moveDir++;
		
		return moveDir;
	}
	
	private static void moveKing(int moveDir) {
		int kingNy = kingPos[0] + dy[moveDir];
		int kingNx = kingPos[1] + dx[moveDir];
		
		// 범위 벗어날 시 이동 건너뜀
		if( !(0 <= kingNy && kingNy < 8 && 0 <= kingNx && kingNx < 8) ) return;
		
		// 킹이 이동한 자리에 돌이 존재하면
		if( isExistStone[kingNy][kingNx] ) {
			int stoneNy = stonePos[0] + dy[moveDir];
			int stoneNx = stonePos[1] + dx[moveDir];
			
			// 범위 벗어날 시 이동 건너뜀
			if( !(0 <= stoneNy && stoneNy < 8 && 0 <= stoneNx && stoneNx < 8) ) return;
			
			isExistStone[ stonePos[0] ][ stonePos[1] ] = false;
			isExistStone[ stoneNy ][ stoneNx ] = true;
			stonePos[0] = stoneNy;
			stonePos[1] = stoneNx;
			
		}
		
		// 돌이 이동할 경우 돌이 범위를 벗어나도 이동을 건너뛰기 때문에 돌의 이동 범위 체크 후 킹 최종 이동
		kingPos[0] = kingNy;
		kingPos[1] = kingNx;
	}

}
