package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:25,488KB / Time:516ms
public class BJ_1365_꼬인전깃줄 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		int[] arr = new int[N], dp = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = arr[0];
		
		int idx = 0, lis = 1;
		for(int i = 1; i < N; i++) {
			// 최악의 경우 1
			if(dp[lis - 1] < arr[i]) {
				dp[lis++] = arr[i];
			}
			// 최악의 경우 2 -> 안하면 시간초과
			else if(dp[0] > arr[i]) {
				dp[0] = arr[i];
			}
			else {
				idx = binarySearch(dp, 0, lis, arr[i]);
				
				if(idx < 0) idx = 0;
				else if(idx >= lis) lis++;
				dp[idx] = arr[i];
			}
		}
		
		System.out.println(arr.length - lis);
		
		in.close();
	}
	
	private static int binarySearch(int[] arr, int low, int high, int find) {
		if(low >= high) 
			return low;
		
		int mid = (low + high) / 2;
		
		if(arr[mid] == find) return mid;
		
		if(arr[mid] < find)
			return binarySearch(arr, mid + 1, high, find);
		else 
			return binarySearch(arr, low, high - 1, find);
	}
	
}
