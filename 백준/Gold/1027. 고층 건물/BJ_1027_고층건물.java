import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * 기울기 : 높이 / 밑변
 * 
 * 차례대로 두 건물 간의 기울기를 계산
 * 
 * 이전 최대 기울기 보다 크면 그 건물을 보이는 건물
 * 
 * 이중 for문으로 차례대로 갱신 O(N^2)
 * 
 * Memory: 14,308KB / Time:124ms
 */

public class BJ_1027_고층건물 {
	
	private static int N;

	public static void main(String[] args) throws Exception {
		int[] height = input();
		int[] cnt = new int[N];
		int maxCnt = 0;
		double nowSlope, maxSlope = -1_000_000_001;
		
		for(int i = 0; i < N; i++) {
			maxSlope = -1_000_000_001;
			
			for(int j = i + 1; j < N; j++) {
				nowSlope = (double)(height[i] - height[j]) / (i - j);
				
				// 최대 기울기 보다 높지 않으면 건물에 가려짐
				if(maxSlope >= nowSlope) continue;
				
				maxSlope = nowSlope;
				cnt[i]++;
				cnt[j]++;
			}
			
			maxCnt = Math.max(maxCnt, cnt[i]);
		}
		
		System.out.println(maxCnt);
	}
	
	private static int[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		int[] height = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		in.close();
		
		return height;
	}

}
