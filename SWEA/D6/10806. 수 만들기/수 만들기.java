import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	private static int len;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = 0, T, N, K, A[], curr[], heap[][] = new int[100_000][];
	
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			N = Integer.parseInt(in.readLine());
			A = new int[N];
			
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			K = Integer.parseInt(in.readLine());
			
			len = 0;
			curr = new int[] {0, K}; // initialize 에러 방지용
			addHeap(heap, 0, K);
			
			while(len > 0) {
				curr = removeHeap(heap);

				if(curr[1] == 0) break;

				for(int i = 0; i < N; i++) {
					addHeap(heap, curr[0] + curr[1] % A[i], curr[1] / A[i]);
				}
			}
			
			sb.append("#").append(t).append(" ").append(curr[0]).append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
	
	private static void addHeap(int[][] heap, int cnt, int left) {
		int child = ++len, parent = child >> 1, tmp[];
				
		heap[child] = new int[]{cnt, left};
		
		while(parent >= 1) {
			if(heap[child][0] >= heap[parent][0]) {
				if(heap[child][0] > heap[parent][0] || 
						heap[child][1] > heap[parent][1])
					return;
			}
			
			tmp = heap[child];
			heap[child] = heap[parent];
			heap[parent] = tmp;
			
			child = parent;
			parent = parent >> 1;
		}
	}
	
	private static int[] removeHeap(int[][] heap) {
		int parent = 1, left = 2, right = 3, tmp[], res[] = new int[] {heap[parent][0], heap[parent][1]};
		
		heap[parent] = heap[len--];
		
		while(left <= len) {
			right = right <= len ? right : left;
			
			if(heap[left][0] > heap[parent][0] && heap[parent][0] < heap[right][0]) break;
			
			if(heap[left][0] >= heap[right][0]) {
				if(heap[left][0] > heap[right][0] || 
						heap[left][1] > heap[right][1]) 
					left = right;
			}
			
			tmp = heap[left];
			heap[left] = heap[parent];
			heap[parent] = tmp;
			
			parent = left;
			left <<= 1;
			right = left + 1;
		}
		
		return res;
	}
}