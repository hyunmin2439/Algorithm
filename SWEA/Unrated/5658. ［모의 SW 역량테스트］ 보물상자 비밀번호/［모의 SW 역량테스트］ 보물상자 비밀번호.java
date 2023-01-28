import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {

	static int T, N, K, numLen;
	static Set<Integer> set;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		set = new TreeSet<>( (a, b) -> b - a );
		
		T = Integer.parseInt(in.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			String hex = in.readLine();
			numLen = N / 4;
            set.clear();
			
			hex += hex.substring(0, numLen - 1);
			
			int strLen = hex.length() - (numLen - 1);
			
			for(int i = 0; i < strLen; i++) {
				String hexNum = hex.substring(i, numLen + i);
				
				int decNum = convertHexToDec(hexNum);
				
				set.add(decNum);
			}
			
			System.out.println( "#" + t + " " + getKthNumber() );
			
		}
		
		in.close();
	}

	private static int getKthNumber() {
		int k = 0, kthNum = 0;
		
		for (Iterator<Integer> iterator = set.iterator(); k < K; k++) {
			kthNum = iterator.next();
		}
		
		return  kthNum;
	}

	private static int convertHexToDec(String hexNum) {
		int decNum = 0;
		
		for(int j = 0; j < numLen; j++) {
			char hexChar = hexNum.charAt(j);
			
			hexChar -= hexChar >= 65 ? ('A' - 10) : '0';
			
			decNum += hexChar * Math.pow(16, numLen - 1 - j);
		}
		
		return decNum;
	}
}