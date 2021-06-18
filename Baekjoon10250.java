package solved;
import java.io.*;

/*
 * problem
 * program that assigns rooms to the shortest walking distance 
 * from the main entrance of the hotel.
 * between all two adjacent rooms is the same distance (distance 1)
 * We also assume that the distance between all two adjacent rooms is 
 * the same distance (distance 1) and that only the front side of 
 * the hotel has rooms.
 * 
 * input
 * T (number of test case)
 * h w n (number of floors, number of room, number of customers)
 * ...	 ( 1 <= h, w <= 99 , 1 <= n <= h * w)
 * 
 * output
 * The room number to which the guest should be assigned. 
 */

public class Baekjoon10250 {

	public static void main(String[] args) throws IOException {
		int t, h, w, n, cnt = 0;
		String[] line;
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		t = Integer.parseInt( br.readLine().trim() );
		
		while(cnt < t) {
			line = br.readLine().trim().split(" ");
			
			h = Integer.parseInt(line[0].trim());
			w = Integer.parseInt(line[1].trim());
			n = Integer.parseInt(line[2].trim());
			
			if( (n % h) == 0 ) {
				w = n / h;
			}
			else {
				w = n / h + 1;
				h = n % h; 
			}
			
			String msg = String.format("%d%02d\n", h, w );
			
			bw.write( msg );
			
			cnt++;
		}
		
		br.close();
		bw.close();
	}

}