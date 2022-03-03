package no_upload;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * two pointer 알고리즘
 * 
 * 1. N / L 숫자를 시작으로 left, right 각각 감소, 증가
 * 
 * 2. left ~ left + L - 1, right ~ right + L - 1 합이 N과 같은지 판단
 * 
 * 3. 만약 다를 때 left < N && right > N 이라면 L++
 * 
 * 4. 아니면 left--, right++
 * 
 * 5. 1번부터 반복
 * 
 * 6. left가 0보다 작거나, L이 100을 넘어가면 -1을 출력
 * 
 * Memory:14220KB / Time:132ms
 */

public class BJ_1024_수열의합 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		String res = twoPointer(N, L);
		
		System.out.print(res);
		
		in.close();
	}
	
	private static String twoPointer(int N, int L) {
		int leftNum = N / L, rightNum = N / L, leftSum, rightSum;
		
		while(leftNum >= 0 && L <= 100) {
			leftSum = 0; 
			rightSum = 0;
			
			for(int i = 0; i < L; i++) {
				leftSum += leftNum + i;
				rightSum += rightNum + i;
			}
			
			// System.out.println("leftNum:" + leftNum + " leftSum: " + leftSum + " rightNum:" + rightNum + " rightSum:" + rightSum);
			
			if(leftSum == N) return getResult(leftNum, L);
			if(rightSum == N) return getResult(leftNum, L);
			
			if( leftSum < N && rightSum > N ) {
				L++;
				leftNum = N / L - 1;
				rightNum = N / L + 1;
			}
			else {
				leftNum--;
				rightNum++;
			}
		}
		
		return "-1";
	}
	
	private static String getResult(int startNum, int L) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < L; i++) {
			sb.append(startNum + i).append(" ");
		}
		
		return sb.toString();
	}

}
