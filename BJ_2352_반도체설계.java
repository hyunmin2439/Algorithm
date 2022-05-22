import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2352_반도체설계 {
	
	private static int N;

	public static void main(String[] args) throws Exception {
		int[] arr = input();
		int[] dp = new int[N];
		
		dp[0] = arr[0];
		
		int idx = 0, lis = 0;
		for(int i = 0; i < N; i++) {
			idx = binarySearch(dp, arr[i], 0, lis); // 들어갈 위치 찾기
			dp[idx] = arr[i]; // 작은 값 기록
			if(idx > lis) lis++;
		}
		
		System.out.print(lis + 1);
	}
	
	private static int binarySearch(int[] arr, int findNum, int min, int max) {
		int mid = (min + max) / 2;
		
		if(min > max)
			return min;
		
		if(arr[mid] < findNum)
			return binarySearch(arr, findNum, mid + 1, max);
		else
			return binarySearch(arr, findNum, min, mid - 1);
	}

	private static int[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		in.close();
		
		return arr;
	}
	
}
