package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon10819 {

	static int N, max;
	static int[] nums;
	static int[] pNums;
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// Input
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		pNums = new int[N];
		selected = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		permu(0, 0);
		
		System.out.println(max);
		br.close();
	}

	private static void permu(int cnt, int sum) {
		// 기저 조건
		if(cnt == N) {
			max = Math.max(max, sum);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(selected[i]) continue;
			
			selected[i] = true; // 선택 처리
			pNums[cnt] = nums[i]; // 선택 배열에 선택한 숫자 담기
			if(cnt == 0) permu(cnt + 1, sum); // 처음 선택한 숫자일 경우 sum 그대로 넘김
			// 두번째 선택 숫자부터 sum 계산 값을 파라미터로 넘김
			else permu(cnt + 1, sum + Math.abs(pNums[cnt - 1] - pNums[cnt]));
			selected[i] = false; // 선택해제 처리
		}
	}

}
