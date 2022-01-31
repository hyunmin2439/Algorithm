package no_upload;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1297_TV크기 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int D = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		double X = Math.sqrt( Math.pow(D, 2) / ( Math.pow(H, 2) + Math.pow(W, 2) ) );
		
		H = (int)(H * X);
		W = (int)(W * X);
		
		System.out.print(H + " " + W);
		
		in.close();
	}

}
