package solved;

import java.io.*;

/*
 * 손익분기점
 * 
 * 입력
 * a b c  ( a, b, c <= 21억 )
 * 
 * 조건
 * 1. if(b >= c) -1
 * 2. a + b * n < c * n
 * 
 * 
 * 출력
 * -1 || 2조건 만족 제일 작은 n
 * 
 * n갑을 증가하면서 풀면 시간초 0.35초 제한때문에 시간 초과 걸림.
 * c - b로 한대 당 이득으로 a값을 나누는게 더 빠름.
 */

public class Baekjoon1712 {

	public static void main(String[] args) throws IOException {
		int a, b, c, n, profit;
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		String[] line = br.readLine().trim().split(" ");
		
		a = Integer.parseInt( line[0].trim() );
		b = Integer.parseInt( line[1].trim() );
		c = Integer.parseInt( line[2].trim() );
		
		if( b >= c) bw.write("-1");
		else {
			profit = c - b; // 한대 팔때 이익
			
			n = a / profit + 1; // int라서 소수점 다버림. +1하면 손익분기점 판매대수.
			
			bw.write( String.valueOf(n) );
		}
		
		br.close();
		bw.close();
	}
}
