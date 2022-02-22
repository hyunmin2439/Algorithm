package no_upload;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:15088KB / Time:152ms
public class BJ_3035_스캐너 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int ZR = Integer.parseInt(st.nextToken());
		int ZC = Integer.parseInt(st.nextToken());
		
		char[][] paper = new char[R][];
		for(int r = 0; r < R; r++) {
			paper[r] = in.readLine().toCharArray();
		}
		
		StringBuilder temp = new StringBuilder(), 
				res = new StringBuilder();
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				for(int k = 0; k < ZC; k++) 
					temp.append(paper[r][c]); // 열 늘리기 -> ZC 값 만큼 추가
			}
			
			for(int k = 0; k < ZR; k++)
				res.append(temp).append("\n");
			
			temp.setLength(0); // temp 초기화
		}
		
		System.out.print(res);
		
		in.close();
	}

}
