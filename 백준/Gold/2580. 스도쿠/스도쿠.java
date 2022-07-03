import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

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

				if(matrix[y][x] == 0) {
					emptyList.add(new Node(y, x));
					numOfEmpty++;
					continue;
				}
				
				rowUsed[y][ matrix[y][x] ] = true;
				columnUsed[x][ matrix[y][x] ] = true;
				sectorUsed[ findSector(y, x) ][ matrix[y][x] ] = true;
			}
		}
		
		backtracking(0);
		
		in.close();
	}
	

	private static void backtracking(int idx) {
		if(findAllNumber) return;
		
		if(idx == numOfEmpty) {
			findAllNumber = true;
			printSudoku();
			return;
		}
		
		Node node = emptyList.get(idx);
		int y = node.y;
		int x = node.x;
		
		for (int i = 1; i <= N; i++) {
			int s = findSector(y, x);

			if( rowUsed[y][i] || columnUsed[x][i] || sectorUsed[s][i] ) continue;
			
			matrix[y][x] = i;
			rowUsed[y][i] = true;
			columnUsed[x][i] = true;
			sectorUsed[s][i] = true;
			
			backtracking(idx + 1);
			
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