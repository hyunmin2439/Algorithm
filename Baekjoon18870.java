package solved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * Input
 * n (1 <= n <= 1,000,000)
 * X1 X2 ... Xi ... Xn (-10^9 <= Xi <= 10^9)
 * 
 * Process
 * As a result of compressing Xi coordinates,
 * the value of Xi is the number of different
 * coordinates that satisfy Xi > Xj
 * 
 * Output
 * Apply coordinate compression
 * 
 * ex)
 * 5
 * 2 4 -10 4 -9  
 * 
 * =>  2 3 0 3 1
 * 
 * 
 * 6
 * 1000 999 1000 999 1000 999  
 * 
 * => 1 0 1 0 1 0
 */

public class Baekjoon18870 {

	static class Coord {
		int idx; // index
		int coord; // value
		
		public Coord(int idx, int coord) {
			this.idx = idx; 
			this.coord = coord; 
		}

		@Override
		public String toString() {
			return "Coord [idx=" + idx + ", coord=" + coord + "]";
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int n = Integer.parseInt(br.readLine());
		Coord[] arr = new Coord[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			arr[i] = new Coord(i, Integer.parseInt(st.nextToken()));
		}
		
		// value sort
		Arrays.sort(arr, new Comparator<Coord>() {
			@Override
			public int compare(Coord o1, Coord o2) {
				return o1.coord - o2.coord;
			}
		});
		
		// previous value, acc : Apply Coordinate Compression
		int prevNum = arr[0].coord, acc = 0;
		
		for(int i = 0; i < n - 1; i++) {
			arr[i].coord = acc;
			if(prevNum != arr[i + 1].coord) {
				acc++;
			}
			prevNum = arr[i + 1].coord;
		}
		
		// last value
		arr[n - 1].coord = acc;
		
		// index sort
		Arrays.sort(arr, new Comparator<Coord>() {
			@Override
			public int compare(Coord o1, Coord o2) {
				return o1.idx - o2.idx;
			}
		});
		
		StringBuilder sb = new StringBuilder();
		
		for(Coord c : arr) {
			sb.append(c.coord + " ");
		}
		
		bw.write(sb.toString());	
		br.close();
		bw.close();
	}

}
