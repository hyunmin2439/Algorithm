import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int[] count = new int[26];
		
		int N = Integer.parseInt(in.readLine());
		
		for(int i = 0; i < N; i++) {
			count[in.readLine().charAt(0) - 'a']++;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 26; i++) {
			if(count[i] >= 5) sb.append((char)('a' + i));
		}
		
		if(sb.length() == 0)
			System.out.print("PREDAJA");
		else
			System.out.print(sb);
	}
}