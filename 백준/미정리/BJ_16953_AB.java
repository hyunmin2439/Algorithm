package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 17,616KB / Time:144ms
public class BJ_16953_AB {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		long start = Long.parseLong(st.nextToken());
		long dest = Long.parseLong(st.nextToken());
		
		int res = bfs(start, dest);
		
		System.out.print(res);
		
		in.close();
	}
	
	private static int bfs(long start, long dest) {
		Queue<Long> queue = new LinkedList<>();
		
		queue.offer(start);
		
		int res = 1, size;
		long curr, nextNum;
		while( !queue.isEmpty() ) {
			size = queue.size();
			
			for(int i = 0; i < size; i++) {
				curr = queue.poll();
				
				if(curr == dest) return res;
				
				nextNum = curr * 2;
				if(nextNum <= dest)
					queue.offer(nextNum);
				
				nextNum = curr * 10 + 1;
				if(nextNum <= dest)
					queue.offer(nextNum);
			}
			
			res++;
		}
		
		return -1;
	}

}
