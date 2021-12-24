package com.algo;

public class BJ15596_정수N개의합 {

	public static void main(String[] args) {
		
	}
	
	long sum(int[] a) {
		int ans = 0;
		
		for(int num : a)
			ans += num;
		
		return ans;
	}
}
