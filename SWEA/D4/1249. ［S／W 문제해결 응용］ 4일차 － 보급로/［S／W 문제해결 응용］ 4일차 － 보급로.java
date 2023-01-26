import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	
	/*
	 * 직접 구현한 heap으로 PriorityQueue보다 속도가 빠름
	 * 
	 * x, y 좌표에 따른 우선순위를 정해줄 필요가 없음
	 * 
	 * 결국은 heap에 있는 모든 노드들을 사용할 것이기 때문에 먼저 나오나 후에 나오나 차이 없다
	 * 
	 * 또한, swap 메서드를 구현해서 호출시 시간복잡도가 굉장히 증가하는 것을 확인
	 */
	
	private static int len;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int[] dy = { -1, 0, 1, 0 }, dx = { 0, -1, 0, 1 };
		
		char[] line;

		int t = 0, T, N, ny, nx,
			curr[],
			map[][] = new int[100][100],
			heap[][] = new int[10001][3],
			visited[][] = new int[100][100];
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			N = Integer.parseInt(in.readLine()) - 1;
			len = 0;
			
			for(int y = 0; y <= N; y++) {
				line = in.readLine().toCharArray();
				for(int x = 0; x <= N; x++) {
					map[y][x] = line[x] - '0';
					visited[y][x] = Integer.MAX_VALUE;
				}
			}
			
			visited[0][0] = 0;
			curr = new int[] {0, 0, 0};
			inHeap(heap, curr);
			
			while(len > 0) {
				curr = outHeap(heap);
				
				for(int d = 0; d < dy.length; d++) {
					ny = curr[1] + dy[d];
					nx = curr[2] + dx[d];
					
					if( !(0 <= ny && ny <= N && 0 <= nx && nx <= N) ||
							visited[ny][nx] <= curr[0] + map[ny][nx] ) continue;
					
					visited[ny][nx] = curr[0] + map[ny][nx];
					
					if(!(ny == N && nx == N))
						inHeap(heap, new int[]{curr[0] + map[ny][nx], ny, nx});
				}
			}
			
			sb.append("#").append(t).append(" ").append(visited[N][N]).append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}

	private static void inHeap(int[][] heap, int[] data) {
		int child = ++len, parent = child >> 1, tmp[];
	
		heap[child] = data;
			
		while(parent >= 1) {
			if(heap[child][0] > heap[parent][0]) return;
			
			tmp = heap[child];
			heap[child] = heap[parent];
			heap[parent] = tmp;
			
			child = parent;
			parent >>= 1;
		}
	}
	
	private static int[] outHeap(int[][] heap) {
		int parent = 1, left = 2, right = 3, tmp[];
		
		heap[0][0] = heap[1][0];
		heap[0][1] = heap[1][1];
		heap[0][2] = heap[1][2];
			
		heap[1][0] = heap[len][0];
		heap[1][1] = heap[len][1];
		heap[1][2] = heap[len--][2];
		
		while(left <= len) {
			right = right <= len ? right : left;
			left = heap[left][0] <= heap[right][0] ? left : right;

			if(heap[left][0] > heap[parent][0]) break;
			
			tmp = heap[left];
			heap[left] = heap[parent];
			heap[parent] = tmp;
			
			parent = left;
			left <<= 1;
			right = left + 1;
		}
		
		return heap[0];
	}
}