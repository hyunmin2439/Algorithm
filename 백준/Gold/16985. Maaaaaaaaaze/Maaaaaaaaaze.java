import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int answer;
	private static final int N = 5;
	private static final int[] dz = { -1, 1,  0, 0,  0, 0 };
	private static final int[] dy = {  0, 0, -1, 1,  0, 0 };
	private static final int[] dx = {  0, 0,  0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		boolean[][][][] isBlocked = input();

		answer = Integer.MAX_VALUE;
		int[] rotated = null;
		int[] ordered = initOrdered();
		
		boolean[][][] setting = new boolean[N][][];
		
		do {
			rotated = new int[5];
			
			do {
				for(int i = 0; i < N; i++) {
					setting[i] = isBlocked[ rotated[i] ][ ordered[i] ];
				}
				
//				System.out.println( Arrays.toString(rotated));
//				System.out.println( Arrays.toString(ordered));
//				System.out.println( "-------------------------" );
				
				isPossibleExit(setting);
				
			} while( nr(rotated) );

		} while( np(ordered) );
		
		System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
	}
	
	private static boolean[][][][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		boolean[][][][] isBlocked = new boolean[4][N][N][N];
		for(int i = 0; i < N; i++) {
			for(int y = 0; y < N; y++) {
				st = new StringTokenizer(in.readLine());
				for(int x = 0; x < N; x++) {
					if("0".equals(st.nextToken()))
						isBlocked[0][i][y][x] = true;
				}
			}
			
			for(int j = 1; j < N - 1; j++) {
				rotate(isBlocked[j - 1][i], isBlocked[j][i]);
			}
		}
		
		in.close();
		
		return isBlocked;
	}

	private static void rotate(boolean[][] origin, boolean[][] rotation) {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				rotation[x][N - y - 1] = origin[y][x];
			}
		}
	}
	
	private static boolean nr(int[] rotated) {
		if(rotated[0] == 3 && rotated[1] == 3 && 
				rotated[2] == 3 &&  rotated[3] == 3 &&  rotated[4] == 3)
			return false;
		
		int idx = 4;
		
		rotated[idx]++;
		
		while( idx > 0 && rotated[idx] > 3 ) {
			rotated[idx--] = 0;
			rotated[idx]++;
		}
		
		return true;
	}
	
	private static int[] initOrdered() {
		int[] ordered = new int[N];
		for(int i = 0; i < N; i++) {
			ordered[i] = i;
		}
		
		return ordered;
	}
	
	private static boolean np(int[] ordered) {
		int i = N - 1;
		while(i > 0 && ordered[i - 1] >= ordered[i]) i--;
		
		if(i == 0) return false;
		
		int j = N - 1;
		while(ordered[i - 1] >= ordered[j]) j--;
		swap(ordered, i - 1, j);
		
		int k = N - 1;
		while(i < k)
			swap(ordered, i++, k--);
		
		return true;
	}
	
	private static void swap(int[] ordered, int i, int k) {
		int tmp = ordered[i];
		ordered[i] = ordered[k];
		ordered[k] = tmp;
	}
	
	private static void isPossibleExit(boolean[][][] isBlocked) {
		Queue<Node> queue = new LinkedList<>();
		boolean[][][] isVisited = new boolean[N][N][N];
		
		if( isBlocked[0][0][0] ) return;
		
		isVisited[0][0][0] = true;
		queue.offer(new Node(0, 0, 0, 0));
		
		int nz, ny, nx;
		Node curr = null;
		while( !queue.isEmpty() ) {
			curr = queue.poll();
			
			if(curr.move >= answer) return;
			
			for(int d = 0; d < dz.length; d++) {
				nz = curr.z + dz[d];
				ny = curr.y + dy[d];
				nx = curr.x + dx[d];
				
				if( !(0 <= nz && nz < N && 0 <= ny && ny < N && 0 <= nx && nx < N) )
					continue;
				
				if( isBlocked[nz][ny][nx] || isVisited[nz][ny][nx] ) continue;
				
				if( nz == N - 1 && ny == N - 1 && nx == N - 1) {
					answer = Math.min(answer, curr.move + 1);
					return;
				}
				
				isVisited[nz][ny][nx] = true;
				queue.offer(new Node(nz, ny, nx, curr.move + 1));
			}
		}
	}
	
	private static void print(boolean[][][][] isBlocked) {
		for(int i = 0; i < N - 1; i++) {
			for(int j = 0; j < N; j++) {
				for(int y = 0; y < N; y++) {
					for(int x = 0; x < N; x++) {
						System.out.print( isBlocked[i][j][y][x] ? "0" : "1" );
					}
					System.out.println();
				}
				System.out.println("--------------------------------------");
			}
			
			System.out.println("=========================================");
		}
	}
	
	static class Node {
		int z, y, x, move;
		
		public Node(int z, int y, int x, int move) {
			this.z = z;
			this.y = y;
			this.x = x;
			this.move = move;
		}
	}
}
