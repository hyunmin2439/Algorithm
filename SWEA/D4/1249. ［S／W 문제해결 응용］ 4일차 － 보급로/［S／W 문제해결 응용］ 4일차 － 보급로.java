import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	
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
					inHeap(heap, new int[]{curr[0] + map[ny][nx], ny, nx});
				}
			}
			
			sb.append("#").append(t).append(" ").append(visited[N][N]).append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
	
	private static void swap(int[] a, int[] b) {
		int[] tmp = a;
		a = b;
		b = tmp;
	}
	
	private static void inHeap(int[][] heap, int[] data) {
		int child = ++len, parent = child >> 1;
	
		heap[child] = data;
			
		while(parent >= 1) {
			if(heap[child][0] >= heap[parent][0]) {
				if(heap[child][0] > heap[parent][0]) return;
				
				if(heap[child][1] <= heap[parent][1]) {
					if(heap[child][1] < heap[parent][1] ||
						heap[child][2] < heap[parent][2]) return;
				}
			}
			
			swap(heap[child], heap[parent]);
			child = parent;
			parent >>= 1;
		}
	}
	
	private static int[] outHeap(int[][] heap) {
		int parent = 1, left = 2, right = 3,
			outData[] = new int[]{ heap[1][0], heap[1][1], heap[1][2] };
		
		heap[1][0] = heap[len][0];
		heap[1][1] = heap[len][1];
		heap[1][2] = heap[len--][2];
		
		while(left <= len) {
			right = right <= len ? right : left;
			
			if(heap[left][0] >= heap[right][0]) {
				if(heap[left][0] > heap[right][0]) 
					left = right;
				else if(heap[left][1] <= heap[right][1]) {
					if(heap[left][1] < heap[right][1] || 
							heap[left][2] < heap[right][2]) 
						left = right;
				}
			}
			
			if(heap[left][0] >= heap[parent][0]) {
				if(heap[left][0] > heap[parent][0]) break;
				
				if(heap[left][1] <= heap[parent][1]) {
					if(heap[left][1] < heap[parent][1] ||
						heap[left][2] < heap[parent][2]) break;
				}
			}
			
			swap(heap[left], heap[parent]);
			parent = left;
			left <<= 1;
			right = left + 1;
		}
		
		return outData;
	}
}