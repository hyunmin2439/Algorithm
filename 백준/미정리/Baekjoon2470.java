package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 두 용액
 * 
 * 문제명만 봐도 알 수 있듯 Two Pointer 기법을 사용하여 해결하는 문제
 * 
 * 왼쪽 포인트를 0로 오른쪽 포인트를 N - 1 위치에 두고 값을 더한 뒤
 * 
 * 0에 가까운지 확인을 위하여 절대값을 취하고 min변수와 비교 더 작은 값으로 대체
 * 
 * 이후, left 위치, right 위치 절대값 중 큰쪽의 포인터를 옮김
 *
 * Memory:28616KB / Time:428ms
 */

public class Baekjoon2470 {

	static int N, min;
	static int[] arr, answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		// 답 찾기 위한 변수들
		min = Integer.MAX_VALUE;
		answer = new int[2];
		
		// 투 포인트 기법으로 정답 찾기
		twoPointer();
		
		System.out.println(answer[0] + " " + answer[1]);
		
		in.close();
	}

	private static void twoPointer() {
		int left = 0, right = N - 1, sum = 0;
		
		while(left < right) {
			// 두 용액 합치기
			sum = arr[left] + arr[right];
			
			// 0에 가까운 값을 찾는 것이니 절대값
			sum = Math.abs(sum);
			
			// 0에 더 가까운 값을 찾았다면 답 바꾸기
			if(min > sum) {
				min = sum;
				answer[0] = arr[left];
				answer[1] = arr[right];
			}
			
			// 두 값 절대값 중 큰쪽 point 옮기기
			if(Math.abs(arr[left]) > Math.abs(arr[right])) left++;
			else right--;
		}
	}

}
