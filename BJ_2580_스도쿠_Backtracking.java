import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* Backtracking
 * 
 * 스도쿠 문제 두번째 풀이
 * 
 * 이전 풀이는 Backtracking 도중 해당 번호가 사용 가능한지 체크했으나,
 * 
 * 이번 풀이는 입력을 받으면서 사용가능 여부를 따진다.
 * 
 * 각 행, 열, 구역별로 boolean used 배열을 만들어놓고
 * 
 * 입력을 받으면서 해당 번호를 사용중이라고 표시.
 * 
 * 이후 backtracking으로 해당 숫자가 사용 중이면 건너뛰고 값을 입력

 * 즉, 가능하지 않은 가지들을 미리 쳐놓고 backtracking을 함.
 * 
 * Memory:28880KB / Time:588ms
 */

public class BJ_2580_스도쿠_Backtracking {

	private static final int N = 9;

	private static boolean findAllNumber;
	private static boolean[][] rowUsed, columnUsed, sectorUsed;
		
	private static int numOfEmpty;
	private static int[][] matrix;
	
	private static List<Node> emptyList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		emptyList = new ArrayList<>();
		rowUsed = new boolean[N + 1][N + 1];
		columnUsed = new boolean[N + 1][N + 1];
		sectorUsed = new boolean[N + 1][N + 1];
		
		matrix = new int[N][N];
		for(int y = 0; y < N; y++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int x = 0; x < N; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());

				// 빈칸 좌표 리스트에 추가
				if(matrix[y][x] == 0) {
					emptyList.add(new Node(y, x));
					numOfEmpty++;
					continue;
				}
				
				// 해당 번호 사용중 처리
				rowUsed[y][ matrix[y][x] ] = true;
				columnUsed[x][ matrix[y][x] ] = true;
				sectorUsed[ findSector(y, x) ][ matrix[y][x] ] = true;
			}
		}
		
		backtracking(0);
		
		in.close();
	}
	

	private static void backtracking(int idx) {
		// 모든 숫자를 찾았으면 멈춤
		if(findAllNumber) return;
		
		// 모든 칸을 채웠으면 출력후 멈춤
		if(idx == numOfEmpty) {
			findAllNumber = true;
			printSudoku();
			return;
		}
		
		// 빈칸 좌표
		Node node = emptyList.get(idx);
		int y = node.y;
		int x = node.x;
		
		// 1 ~ 9까지
		for (int i = 1; i <= N; i++) {
			int s = findSector(y, x); // 섹터 값 미리 구해놓기
			
			// 가로, 세로, 구역에서 쓰인 번호는 건너 뜀
			if( rowUsed[y][i] || columnUsed[x][i] || sectorUsed[s][i] ) continue;
			
			matrix[y][x] = i;
			rowUsed[y][i] = true;
			columnUsed[x][i] = true;
			sectorUsed[s][i] = true;
			
			backtracking(idx + 1);
			
			// 이리로 넘어왔다는 건 앞에 넣은 숫자가 맞지 않음 전부 해제.
			matrix[y][x] = 0;
			rowUsed[y][i] = false;
			columnUsed[x][i] = false;
			sectorUsed[s][i] = false;
		}
		
	}
	
	private static void printSudoku() {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				System.out.print(matrix[y][x] + " ");
			}
			System.out.println();
		}
	}


	// 좌표 값을 통해 해당 구역을 찾는 메서드
	private static int findSector(int y, int x) {
		int sectorNum;
		
		if(y >= 6) sectorNum = 7;
		else if( y >= 3) sectorNum = 4;
		else sectorNum = 1;
		
		if(x >= 6) sectorNum += 2;
		else if(x >= 3) sectorNum += 1;
		
		return sectorNum;
	}

	static class Node {
		int y, x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
				
	}
}
