import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
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
		kingPos[1] = kingPosStr[0] - 'A';
		kingPos[0] = kingPosStr[1] - '0' - 1;
		
		char[] stonePosStr = st.nextToken().toCharArray();
		stonePos[1] = stonePosStr[0] - 'A';
		stonePos[0] = stonePosStr[1] - '0' - 1;

		isExistStone[ stonePos[0] ][ stonePos[1] ] = true;
		
		N = Integer.parseInt(st.nextToken());
	}
	
	private static int getDirectIndex(String moveDirStr) {
		int moveDir = moveDirStr.length() == 1 ? 0 : 4;
		
		while( !direct[moveDir].equals(moveDirStr) ) moveDir++;
		
		return moveDir;
	}
	
	private static void moveKing(int moveDir) {
		int kingNy = kingPos[0] + dy[moveDir];
		int kingNx = kingPos[1] + dx[moveDir];
		
		if( !(0 <= kingNy && kingNy < 8 && 0 <= kingNx && kingNx < 8) ) return;
		
		if( isExistStone[kingNy][kingNx] ) {
			int stoneNy = stonePos[0] + dy[moveDir];
			int stoneNx = stonePos[1] + dx[moveDir];
			
			if( !(0 <= stoneNy && stoneNy < 8 && 0 <= stoneNx && stoneNx < 8) ) return;
			
			isExistStone[ stonePos[0] ][ stonePos[1] ] = false;
			isExistStone[ stoneNy ][ stoneNx ] = true;
			stonePos[0] = stoneNy;
			stonePos[1] = stoneNx;
		}
		
		kingPos[0] = kingNy;
		kingPos[1] = kingNx;
	}

}