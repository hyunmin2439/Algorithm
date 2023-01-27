import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	private static final int NODE_MAXLEN = 200_001;
	private static int minLen, maxLen;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		long ans;
		int t = 0, T, N, mid, tmp, x, y,
				mod = 20_171_109,
				minHeap[] = new int[NODE_MAXLEN],
				maxHeap[] = new int[NODE_MAXLEN];
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			mid = Integer.parseInt(st.nextToken());
			minLen = maxLen = 0;
			
			// heap에 값이 없는 상태에서 i 0 ~ N - 1까지 돌리면
			// 로직에 오류가 생김
			st = new StringTokenizer(in.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			if(x > y) {
				tmp = x;
				x = y;
				y = tmp;
			}
			if(x <= mid && mid <= y) {
				addMaxHeap(maxHeap, x);
				addMinHeap(minHeap, y);
			}
			else if(mid <= x) {
				addMaxHeap(maxHeap, mid);
				addMinHeap(minHeap, y);
				mid = x;
			}
			else { // y <= mid
				addMaxHeap(maxHeap, x);
				addMinHeap(minHeap, mid);
				mid = y;
			}
			
			ans = mid;
			
			for(int i = 1; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				
				if(x > y) {
					tmp = x;
					x = y;
					y = tmp;
				}
				
				if(x <= mid && mid <= y) {
					addMaxHeap(maxHeap, x);
					addMinHeap(minHeap, y);
				}
				else if(mid <= x) {
					addMaxHeap(maxHeap, mid);
					
					mid = removeMinHeap(minHeap);
					if(mid > x) {
						tmp = x;
						x = mid;
						mid = tmp;
					}
					
					addMinHeap(minHeap, x);
					addMinHeap(minHeap, y);
				}
				else { // y <= mid
					addMinHeap(minHeap, mid);
					
					mid = removeMaxHeap(maxHeap);
					if(mid < y) {
						tmp = y;
						y = mid;
						mid = tmp;
					}
					
					addMaxHeap(maxHeap, x);
					addMaxHeap(maxHeap, y);
				}
				
				ans = (ans + mid) % mod;
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
	
	private static void addMinHeap(int[] heap, int num) {
		int child = ++minLen, parent = child >> 1, tmp;
		heap[child] = num;
		
		while(parent >= 1) {
			if(heap[child] >= heap[parent]) return;
			
			tmp = heap[child];
			heap[child] = heap[parent];
			heap[parent] = tmp;
			
			child = parent;
			parent >>= 1;
		}
	}
	
	private static int removeMinHeap(int[] heap) {
		int num = heap[1], parent = 1, left = 2, right = 3, tmp;
		
		heap[1] = heap[minLen--];
		
		while(left <= minLen) {
			right = right <= minLen ? right : left;
			left = heap[left] <= heap[right] ? left : right;
			
			if(heap[left] >= heap[parent]) break;
			
			tmp = heap[left];
			heap[left] = heap[parent];
			heap[parent] = tmp;
			
			parent = left;
			left = parent << 1;
			right = left + 1;
		}
		
		return num;
	}
	
	private static void addMaxHeap(int[] heap, int num) {
		int child = ++maxLen, parent = child >> 1, tmp;
		heap[child] = num;
		
		while(parent >= 1) {
			if(heap[child] <= heap[parent]) return;
			
			tmp = heap[child];
			heap[child] = heap[parent];
			heap[parent] = tmp;
			
			child = parent;
			parent >>= 1;
		}
	}
	
	private static int removeMaxHeap(int[] heap) {
		int num = heap[1], parent = 1, left = 2, right = 3, tmp;
		
		heap[1] = heap[maxLen--];
		
		while(left <= maxLen) {
			right = right <= maxLen ? right : left;
			left = heap[left] >= heap[right] ? left : right;
			
			if(heap[left] <= heap[parent]) break;
			
			tmp = heap[left];
			heap[left] = heap[parent];
			heap[parent] = tmp;
			
			parent = left;
			left = parent << 1;
			right = left + 1;
		}
		
		return num;
	}

}