package myAlgo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1992 {
	
	static int N;
	static boolean[][] bitmap;
	
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		bitmap = new boolean[N][N];
		
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				if(line.charAt(j) == '1') {
					bitmap[i][j] = true;
					cnt++; // 입력 받으면서 cnt세고 입력 후 바로 체크
				}
			}
		}
		
		// 전체가 1, 0이면 바로 출력, 아니라면 재귀 메소드 호출
		if(cnt == N * N) 
			sb.append(output(bitmap[0][0]));
		else 
			recur(0, 0, N / 2);
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

	private static void recur(int y, int x, int n) {
		sb.append("(");
		
		// 각 구간 체크, 다 같으면 출력 아니면 재귀
		
		// 1구간 
		if(check( y, y + n, x, x + n, n) )
			sb.append(output(bitmap[y][x]));
		else recur(y, x, n / 2);
		
		// 2구간
		if(check( y, y + n, x + n, x + n * 2, n) ) 
			sb.append(output(bitmap[y][x + n]));
		else recur(y, x + n, n / 2);
		
		// 3구간
		if(check( y + n, y + n * 2, x, x + n, n) )
			sb.append(output(bitmap[y + n][x]));
		else recur(y + n, x, n / 2);
		
		// 4구간
		if(check( y + n, y + n * 2, x + n, x + n * 2, n) )
			sb.append(output(bitmap[y + n][x + n]));
		else recur(y + n, x + n, n / 2);
		
		sb.append(")");
	}
	
	private static char output(boolean b) {
		return b ? '1' : '0';
	}

	private static boolean check(int sy, int ey, int sx, int ex, int n) {
		int cnt = 0;
		
		for (int y = sy; y < ey; y++) {
			for (int x = sx; x < ex; x++) {
				if(bitmap[y][x]) cnt++; // true(1)이면 cnt 증가
			}
		}
		
		// 개수가 0 개면 전체 0 / 개수가 n * n이랑 같으면 전체 1 / 0 1 섞여 있음
		return cnt == 0 ? true : (cnt == n * n ? true : false);
	}

}
