package solved.notsubmit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Two Pointer 알고리즘
 * 
 * Memory:14352KB / Time:136ms
 */

public class BJ_14719_빗물 {

	private static int W;
			
	public static void main(String[] args) throws Exception {
		int[] block = input();
		
		int sum = twoPoint(block);
		
		System.out.print(sum);
	}
	
	private static int twoPoint(int[] block) {
		int sum = 0, curr, leftMax, rightMax;
		
		for(int i = 1; i < W - 1; i++) {
			curr = leftMax = rightMax = block[i];
			
			// 왼쪽에서 최대 높이
			for(int j = i - 1; j >= 0; j--) {
				leftMax = Math.max(leftMax, block[j]);
			}
			
			// 오른쪽에서 최대 높이
			for(int j = i + 1; j < W; j++) {
				rightMax = Math.max(rightMax, block[j]);
			}
			
			// 둘 중 작은 높이에서 현재 높이 빼고 더하기
			sum += Math.min(leftMax, rightMax) - curr;
		}
		
		return sum;
	}

	private static int[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		int[] block = new int[W + 1];

		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < W; i++) {
			block[i] = Integer.parseInt(st.nextToken());
		}
				
		in.close();
		
		return block;
	}

}
