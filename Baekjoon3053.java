package solved;

import java.io.*;

/*
 * Hermann Minkowski Taxi Geometry
 * Distance between two points T1(x1, y1), T2(x2, y2) 
 * D(T1, T2) = Math.abs(x1 - x2) + Math.abs(y1 - y2)
 * 
 * Input
 * r (r is radius <= 10,000)
 * 
 * Output
 * The first line  : r * r * Math.PI
 * The second line : r * r * 2
 */


public class Baekjoon3053 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out));
		
		int r = Integer.parseInt( br.readLine().trim() );
		
		bw.write(String.valueOf(r * r * Math.PI) + "\n");
		bw.write(String.valueOf(r * r * 2));
		
		br.close();
		bw.close();
	}
	
}
