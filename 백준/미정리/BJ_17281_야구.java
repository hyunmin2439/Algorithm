import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:20,384KB / Time:456ms
public class BJ_17281_야구 {

	private static int N;
	
	public static void main(String[] args) throws Exception {
		int[][] originHitter = input();
		
		int[][] hitter = new int[9][];
		hitter[3] = originHitter[0]; // 4번 타자 1번 타자로 고정
		
		int idx = 0, maxScore = 0, order[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
		
		do {
			idx = 0;
			for(int i = 0; i < 9; i++) {
				if(i == 3) continue;
				hitter[i] = originHitter[ order[idx++] ];
			}
			
			maxScore = Math.max(maxScore, getScore(hitter) );
			
		} while( np(order) );
		
		System.out.println(maxScore);
	}
	
	private static int getScore(int[][] hitter) {
		int innings = 0, num = 0, outCnt = 0, base[] = new int[5];
		
		while(innings < N) {
			outCnt = 0;
			base[1] = base[2] = base[3] = 0;
			
			while(outCnt < 3) {
				if( hitter[num][innings] != 0 )
					hit(hitter[num][innings], base);
				else
					outCnt++;
				
				num++;
				if(num > 8) num = 0;
			}
			
			innings++;
		}
		
		return base[4];
	}
	
	private static void hit(int num, int[] base) {
		switch(num) {
		case 1:
			base[4] += base[3];
			base[3] = base[2];
			base[2] = base[1];
			base[1] = 1;
			break;
		case 2:
			base[4] += base[3];
			base[4] += base[2];
			base[3] = base[1];
			base[2] = 1;
			base[1] = 0;
			break;
		case 3:
			base[4] += base[3];
			base[4] += base[2];
			base[4] += base[1];
			base[3] = 1;
			base[2] = base[1] = 0;
			break;
		case 4:
			base[4] += base[3];
			base[4] += base[2];
			base[4] += base[1];
			base[4] += 1;
			base[3] = base[2] = base[1] = 0;
			break;
		}
	}
	
	public static boolean np(int[] arr) {
		int i = arr.length - 1;
		while(i > 0 && arr[i - 1] >= arr[i]) i--;
		
		if(i == 0) return false;
		
		int j = arr.length - 1;
		while(arr[i - 1] >= arr[j]) j--;
		orderSwap(arr, i - 1, j);
		
		int k = arr.length - 1;
		while(i < k)
			orderSwap(arr, i++, k--);
		
		return true;
	}
	
	private static void orderSwap(int[] order, int i, int j) {
		int tmp = 0;
		tmp = order[i];
		order[i] = order[j];
		order[j] = tmp;
	}
	
	private static int[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		
		int[][] hitter = new int[9][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < 9; j++) {
				hitter[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		in.close();
		
		return hitter;
	}

}
