package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon10819_NP {

	static int N, max;
	static int[] nums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// Input
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// 정렬
		Arrays.sort(nums);
		
		// Next Permitation
		do {
			calcSum();
		}while(np());
		
		System.out.println(max);
		br.close();
	}

	private static void calcSum() {
		int sum = 0;
		for (int i = 1; i < N; i++) {
			sum += Math.abs(nums[i - 1] - nums[i]);
		}
		
		max = Math.max(max, sum);
	}

	private static boolean np() {
		
		// 꼭대기 찾기
		int i = N - 1;
		while(i > 0 && nums[i - 1] >= nums[i]) i--;
		
		if(i == 0) return false; // 다음 순열 없음
		
		// 꼭대기 전 숫자보다 큰 처음 만나는 숫자와 위치 교환
		int j = N - 1;
		while(nums[i - 1] >= nums[j]) j--;
		swap(i - 1, j);
		
		// 꼭대기 포함 뒤의 숫자들 정렬
		int k = N - 1;
		while(i < k) {
			swap(i++, k--);
		}
		
		return true;
	}

	private static void swap(int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}


}
