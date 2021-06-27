package solved;

import java.io.*;

/*
 * Input
 * t
 * x y (0 <= x < y <= 2^31)
 * 
 * x -> y move
 * 
 * First move   O, 1
 * After move : k - 1, k, k + 1 (Previous move : k)
 * Last  move : 1
 * 
 * distance = y - x
 * 
 * distance  1|2|3 4|5 6|7 8 9|10 11 12|13 14 15 16|17 18 19 20|21 ...
 *     move  1|2|3 3|4 4|5 5 5| 6  6  6| 7  7  7  7| 8  8  8  8| 9
 *    count  1|1| 2 | 2 |  3  |    3   |     4     |     4     | 5
 * 
 * 2 < distance than Math.sqrt(distance) => rounding => count
 * distance <= count * count + count
 */

public class Baekjoon1011 {

	public static void main(String[] args) throws IOException {
		int cnt = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int t = Integer.parseInt(br.readLine().trim());

		while (cnt < t) {
			String[] line = br.readLine().trim().split(" ");
			long x = Integer.parseInt(line[0]);
			long y = Integer.parseInt(line[1]);

			// bw.write( firstSolved( y - x ) ); // Complicated solution
			
			bw.write( secondSolved( y - x) ); // Simple solution
			
			cnt++;
		} // while end

		br.close();
		bw.close();
	} // main end
	
	static String firstSolved(long dis) {
		long sumVal = 0, result = 0;
		long count = (long) Math.sqrt(dis) - 1;
		if (dis < 3)
			result = dis;
		else {
			while (sumVal < dis) {
				count++;
				sumVal = count * count + count;
			} // while end
			
			if (sumVal - count < dis)
				result = count * 2;
			else
				result = count * 2 - 1;
			
		} // else end
		
		return String.valueOf(result) + "\n";
	}
	
	static String secondSolved(long dis) {
		long count = Math.round( Math.sqrt(dis) );
		long result = count * 2;
		
		//System.out.println(dis + ":count:" + count);
		
		if(dis < 3)
			result = dis;
		else if(count * count >= dis) result--;
	
		return String.valueOf(result) + "\n";
	}
	
} // class end
