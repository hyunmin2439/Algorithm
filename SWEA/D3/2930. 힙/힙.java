import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = 0, T, N,
			parent, left, right, len, tmp,
			heap[] = new int[100_001];
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			N = Integer.parseInt(in.readLine());
			sb.append("#").append(t).append(" ");
			len = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				if( "1".equals(st.nextToken()) ){
					heap[++len] = Integer.parseInt(st.nextToken());
					
					parent = len >> 1;
					right = len;
					while(parent >= 1 && heap[parent] < heap[right]) {
						tmp = heap[right];
						heap[right] = heap[parent];
						heap[parent] = tmp;
						
						right = parent;
						parent >>= 1;
					}
					
					continue;
				}
				
				if(len == 0) {
					sb.append(-1).append(" ");
					continue;
				}
				
				sb.append(heap[1]).append(" ");
				heap[1] = heap[len--];
				
				parent = 1;
				left = parent << 1;
				right = left + 1;
				right = right <= len ? right : left;
				
				while(left <= len) {
					if(heap[left] >= heap[right] && heap[parent] < heap[left]){
						tmp = heap[left];
						heap[left] = heap[parent];
						heap[parent] = tmp;
						
						parent= left;
					}
					else if(heap[parent] < heap[right]) {
						tmp = heap[right];
						heap[right] = heap[parent];
						heap[parent] = tmp;
						
						parent = right;
					}
					else
						break;
					
					left = parent << 1;
					right = left + 1;
					right = right <= len ? right : left;
				}
			}
			
			sb.append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}

}