package myAlgo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1259 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
//		// first Solve
//		while(true) {
//			boolean check = true;
//			char[] nums = br.readLine().toCharArray();
//			
//			if(nums[0] == '0') break;
//			
//			for (int s = 0, n = nums.length - 1; s <= n; s++, n--) {
//				if(nums[s] != nums[n]) { check = false; break; }
//			}
//			
//			if(check) sb.append("yes").append("\n");
//			else sb.append("no").append("\n");
//		}
		
		
		// other Solve
		while(true) {
			String line = br.readLine();
			
			if(line.charAt(0) == '0') break;
			
			line = line.equals( new StringBuilder(line).reverse().toString() ) ? "yes" : "no";
			sb.append(line).append("\n");
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
}
