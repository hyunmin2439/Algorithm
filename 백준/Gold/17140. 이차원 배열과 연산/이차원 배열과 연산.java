import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static int row, col;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Number> pqueue = new PriorityQueue<>();
		int r = Integer.parseInt(st.nextToken()),
			c = Integer.parseInt(st.nextToken()),
			k = Integer.parseInt(st.nextToken()),
			A[][] = new int[101][101],
			cnt	= 0;
		
		for(int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		row = col = 3;
		
		while(cnt <= 100 && A[r][c] != k) {
			if(row >= col) OperationR(pqueue, A);
			else OperationC(pqueue, A);
			
			cnt++;
		}
		
		System.out.println( cnt > 100 ? -1 : cnt );
	}
	
	private static void OperationR(PriorityQueue<Number> pqueue, int[][] A) {
		int[] acc = new int[101];
		Number num;
		int idx;
		
		for(int r = 1; r <= row; r++) {
			for(int c = 1; c <= col; c++) {
				acc[ A[r][c] ]++;
			}
			
			for(int i = 1; i < acc.length; i++) {
				if(acc[i] > 0)
					pqueue.offer(new Number(i, acc[i]));
			}
			
			idx = 1;
			while( idx < 100 && !pqueue.isEmpty() ) {
				num = pqueue.poll();
				
				A[r][idx] = num.num;
				A[r][idx + 1] = num.cnt;
				
				idx += 2;
			}
			
			col = Math.max(col, idx - 1);
			
			while(idx <= col) A[r][idx++] = 0;
			
			Arrays.fill(acc, 0);
		}
	}
	
	private static void OperationC(PriorityQueue<Number> pqueue, int[][] A) {
		int[] acc = new int[101];
		Number num;
		int idx;
		
		for(int c = 1; c <= col; c++) {
			for(int r = 1; r <= row; r++) {
				acc[ A[r][c] ]++;
			}
			
			for(int i = 1; i < acc.length; i++) {
				if(acc[i] > 0)
					pqueue.offer(new Number(i, acc[i]));
			}
			
			idx = 1;
			while( idx < 100 && !pqueue.isEmpty() ) {
				num = pqueue.poll();
				
				A[idx][c] = num.num;
				A[idx + 1][c] = num.cnt;
				
				idx += 2;
			}
			
			row = Math.max(row, idx - 1);
			
			while(idx <= row) A[idx++][c] = 0;
			
			Arrays.fill(acc, 0);
		}
	}
}

class Number implements Comparable<Number>{
	int num, cnt;
	
	public Number(int num, int cnt) {
		this.num = num;
		this.cnt = cnt;
	}
	
	@Override
	public int compareTo(Number other) {
		if(this.cnt != other.cnt)
			return this.cnt - other.cnt;
		
		return this.num - other.num;
	}
}