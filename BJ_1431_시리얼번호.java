package no_upload;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// Memory:14380KB / Time:132ms
public class BJ_1431_시리얼번호 {

	public static void main(String[] args) throws Exception {
		List<Guitar> guitarList = input();

		Collections.sort(guitarList);
		
		guitarList.forEach(System.out::println);
	}
	
	private static List<Guitar> input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		List<Guitar> guitarList = new LinkedList<>();
		
		int N = Integer.parseInt(in.readLine());
		
		for(int i = 0; i < N; i++)
			guitarList.add(new Guitar(in.readLine()));
		
		in.close();
		
		return guitarList;
	}
	
	static class Guitar implements Comparable<Guitar> {
		String serialNum;
		int length;
		int numTot;
		
		public Guitar(String serialNum) {
			this.serialNum = serialNum;
			this.length = serialNum.length();
			
			int tmp = 0;
			for(char c : serialNum.toCharArray()) {
				if(c - '0' <= 9)
					tmp += c - '0';
			}
			
			numTot = tmp;
		}
		
		@Override
		public int compareTo(Guitar other) {
			if(this.length != other.length)
				return this.length - other.length;
			
			if(this.numTot != other.numTot)
				return this.numTot - other.numTot;
			
			return this.serialNum.compareTo(other.serialNum);
		}
		
		@Override
		public String toString() {
			return serialNum;
		}
	}

}
