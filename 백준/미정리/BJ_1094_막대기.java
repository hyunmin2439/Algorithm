package solved;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Input
 * x (1 <= x <= 64)
 * 
 * Process
 * 1. At first, it only has a 64-length bar.
 * 2. Cut the shortest bar in half.
 * 3. If the sum of the remaining bars is greater than or equal to x-length, 
 *    except one of the above-cut bars, discard one of the above-cut bars.
 * 4. Repeat steps 2–3 if the sum of the remaining bars is greater than x-length.
 * 
 * Output
 * Number of bars required for x-length
 */

public class BJ_1094_막대기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int x = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();
		list.add(64);
		int sum = 64;
		
		while ( sum != x ) {
			sum = 0;
			int half = list.remove(0) / 2;
			list.add(0, half);
			
			for(Integer i : list) {
				sum += i;
			}
			
			if(sum < x) {
				list.add(0, half);
				sum += half;
			}
		}
		
		bw.write(String.valueOf(list.size()));
		
		br.close();
		bw.close();
	}

}
