package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 가장 근래에 사용하지 않을 콘센트 빼기
 * 
 * Memory: 14160KB / Time: 120ms
 */

public class BJ_1700_멀티탭스케줄링_정답 {

	private static final int INF = 101; // K 100까지
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		
		int order[] = new int[K], plug[][] = new int[N][2]; // 0: productNum, 1:근래에 사용 한다는 우선순위
		
		// 입력
		for(int i = 0; i < K; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		
		int idx = 0, outIdx = 0, answer = 0;
		for(int i = 0; i < K; i++) {
			// 현재 사용중이라면
			if( isExistUsing(plug, order[i]) ) continue;
			
			if(idx < N) {
				plug[idx][0] = order[i];
				plug[idx][1] = getSameOfIdx(order, plug[idx][0], i);
				idx++;
			}
			else {				
				outIdx = getOutIdx(plug, order, i);
				
				plug[ outIdx ][0] = order[i];
				plug[ outIdx ][1] = getSameOfIdx(order, plug[ outIdx ][0], i);
				
				answer++;
			}
		}
		
		System.out.print(answer);
		
		in.close();
	}
	
	private static boolean isExistUsing(int[][] plug, int product) {
		for(int i = 0; i < plug.length; i++) {
			if(plug[i][0] == product) return true;
		}
		
		return false;
	}
	
	private static int getSameOfIdx(int[] order, int product, int currIdx) {
		for(int i = currIdx + 1; i < order.length; i++) {
			if(order[i] == product) return i;
		}
		
		return INF;
	}
	
	private static int getOutIdx(int[][] plug, int[] order, int currIdx) {
		int outIdx = 0, maxPriority = 0;
		
		for(int j = 0; j < plug.length; j++) {
			// 현재 i보다 우선순위 숫자가 작으면 다시 갱신
			if(plug[j][1] < currIdx) 
				plug[j][1] = getSameOfIdx(order, plug[j][0], currIdx);
			
			if(maxPriority < plug[j][1]) {
				maxPriority = plug[j][1];
				outIdx = j;
			}
		}
		
		return outIdx;
	}
}
