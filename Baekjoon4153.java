package solved;
import java.io.*;

/*
 * 직각삼각형인지 판단
 * 
 * 입력
 * 세변 (int <= 30_000)
 * 마지막 0 0 0
 * 
 * 출력
 * 맞으면 right
 * 틀리면 wrong
 */

public class Baekjoon4153 {

	public static void main(String[] args) throws IOException {
		int max;
		int[] tri = new int[3];
		int[] rest = new int[2];
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );

		while(true) {
			String[] line = br.readLine().trim().split(" ");
			
			tri[0] = Integer.parseInt(line[0]);
			tri[1] = Integer.parseInt(line[1]);
			tri[2] = Integer.parseInt(line[2]);
			
			if(tri[0] == 0 && 
				tri[1] == 0 && 
				tri[2] == 0) break;
			
			max = tri[0];
			rest[0] = tri[1];
			rest[1] = tri[2];
			
			if(max < tri[1]) {
				max = tri[1];
				rest[0] = tri[0];
				rest[1] = tri[2];
			}
			if(max < tri[2]) {
				max = tri[2];
				rest[0] = tri[0];
				rest[1] = tri[1];
			}
			
			if( Math.pow(max, 2) == Math.pow(rest[0], 2) + Math.pow(rest[1], 2) ) {
				bw.write( "right\n" );
			}
			else bw.write( "wrong\n" );
		}
		
		br.close();
		bw.close();
	}

}
