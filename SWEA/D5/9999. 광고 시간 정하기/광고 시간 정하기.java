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
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int[][] peak = new int[100_000][2];
		int[] time = new int[100_000];
		int t = 0, T, L, N, endTime, left, right, prevRight, rightValue, max = 0, ans = 0;
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			L = Integer.parseInt(in.readLine());
			N = Integer.parseInt(in.readLine());
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				peak[i][0] = Integer.parseInt(st.nextToken());
				peak[i][1] = Integer.parseInt(st.nextToken());
				time[i] = peak[i][1] - peak[i][0];
			}
			
			ans = 0;
			left = right = 0;
			endTime = peak[left][0] + L;
			while(right < N - 1 && peak[right][1] < endTime) right++;
			if(endTime < peak[right][0]) right--;
			
			for(int i = left; i < right; i++) {
				ans += time[i];
			}
			rightValue = (endTime < peak[right][1] ? endTime : peak[right][1]) - peak[right][0];
			max = ans + rightValue;
			prevRight = right;
			
			while(true) {
				left++;
				endTime = peak[left][0] + L;
				
				if(endTime > peak[N - 1][1]) break;
				
				while(right < N - 1 && peak[right][1] < endTime) right++;
				if(endTime < peak[right][0]) right--;
				
				ans -= time[left - 1];
				while(prevRight < right) ans += time[prevRight++];
				
				rightValue = (endTime < peak[right][1] ? endTime : peak[right][1]) - peak[right][0];
				max = Math.max(max, ans + rightValue);
			}
			
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
}