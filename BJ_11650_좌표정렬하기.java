package solved;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * Input
 * n (1 <= n <= 100,000)
 * x1 y2 (-100,000 <= xi yi <= 100,000)
 * x2 y2 (No two points with the same location)
 * ...
 * xn yn
 * 
 * Output
 * Sort points in increasing x-coordinates, 
 * and y-coordinates in increasing order if x-coordinates are the same.
 */

class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return x + " " + y;
	}
}

public class BJ_11650_좌표정렬하기 {

	public static void main(String[] args) throws IOException {
		List<Point> pList = new ArrayList<>();
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int n = Integer.parseInt(br.readLine().trim());
		
		for(int i = 0; i < n; i++) {
			String[] line = br.readLine().trim().split(" ");
			pList.add( new Point( Integer.parseInt(line[0]), Integer.parseInt(line[1]) ) );
		}
		
		Collections.sort(pList, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.getX() != o2.getX())
					return o1.getX() - o2.getX();
				else 
					return o1.getY() - o2.getY();
			}
		});
		
		for(Point p : pList) {
			bw.write(p + "\n");
		}
		
		br.close();
		bw.close();
	}

}
