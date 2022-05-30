import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		char[][] strArr = new char[N][];
		
		for(int i = 0; i < N; i++) {
			strArr[i] = in.readLine().toCharArray();
		}
		
		char[] answer = new char[ strArr[0].length ];
		for(int j = 0; j < strArr[0].length; j++) {
			boolean isSameChar = true;
			
			for(int i = 0; i < N - 1; i++) {
				if(strArr[i][j] != strArr[i + 1][j]) {
					isSameChar = false;
					break;
				}
			}
			
			answer[j] = isSameChar ? strArr[0][j] : '?';
		}
		
		System.out.print(answer);
		in.close();
	}
}