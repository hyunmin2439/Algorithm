import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int maxLen= 0;
		char[][] arr = new char[5][];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = in.readLine().toCharArray();
			maxLen = Math.max(arr[i].length, maxLen);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < maxLen; i++) {
			for(int j = 0; j < 5; j++) {
				if(arr[j].length <= i) continue;
				sb.append(arr[j][i]);
			}
		}
		
		System.out.print(sb);
		
		in.close();
	}
}