import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
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