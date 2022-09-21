package solved;

import java.io.*;
import java.util.*;

/*
 * Input
 * n (1 <= n(odd number) <= 500,000)
 * n-line number ( Math.abs(number) <= 4,000 )
 * 
 * Output
 * 1. Arithmetical average ( Math.round( arrSum / n ) )
 * 2. Middle value
 * 3. Most Frequent Value(mfv)
 *    (If multiple, the second smallest of the mfv)
 * 4. Range(MaxNum - MinNum)
 */

public class Baekjoon2108 {

	public static void main(String[] args) throws IOException {
		double artAvg = 0;
		int midNum, mfv, range;
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ));
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( System.out ));
		
		// Input
		int n = Integer.parseInt( br.readLine().trim() );
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt( br.readLine().trim() );
			artAvg += arr[i];
		}
		
		Arrays.sort(arr);
		
		// Process
		artAvg = Math.round(artAvg / n); // 1
		midNum = arr[n/2]; // 2
		range = arr[n-1] - arr[0]; // 4
		
		// 3. TreeMap -> ArrayList
		// key : Ascending order / value descending order
		Map<Integer, Integer> map = new TreeMap<>();
		
		for(int i = 0; i < n; i++) {
			if( map.containsKey(arr[i]) )
				map.put(arr[i], map.get(arr[i]) + 1);
			else map.put(arr[i], 1);
		}
		
		List<Integer> keySet = new ArrayList<>(map.keySet());
		
		if(map.size() < 2) { // size == 1
			mfv = keySet.get(0);
		}
		else { // size > 1
			keySet.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return map.get(o2) - map.get(o1);
				}
			});
			
			// the second smallest of the mfv
			mfv = map.get(keySet.get(0)) > map.get(keySet.get(1)) 
					? keySet.get(0) : keySet.get(1);
		}
		
		bw.write((int)artAvg + "\n");
		bw.write(midNum + "\n");
		bw.write(mfv + "\n");
		bw.write(range + "\n");
		
		br.close();
		bw.close();
	}
}
