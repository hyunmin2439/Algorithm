package no_upload;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// Memory:15904KB / Time:140ms
public class BJ_1235_학생번호 {

	private static int N;
	private static String[] studentNum;
	
	public static void main(String[] args) throws Exception {
		input();
		
		int res = solve();
		
		System.out.print(res);
	}
	
	private static int solve() {
		Set<String> set = new HashSet<>();
		int K = 1;
		String tmp;
		boolean isFind;
		
		while(true) {
			isFind = true;
			set.clear();
			
			for(int i = 0; i < N; i++) {
				tmp = studentNum[i].substring(studentNum[i].length() - K, studentNum[i].length());
				
				if( set.contains(tmp) ) {
					isFind = false;
					break;
				}
				
				set.add(tmp);
			}
			
			if(isFind) break;
			
			K++;
		}
		
		return K;
	}
	
	private static void input() throws Exception {
		BufferedReader  in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		studentNum = new String [N];
		
		for(int i = 0; i < N; i++) {
			studentNum[i] = in.readLine();
		}
		
		in.close();
	}

}
