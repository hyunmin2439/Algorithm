import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		char[][][] painting = new char[N][5][];
		
		for(int i = 0; i < N; i++) {
			for(int y = 0; y < 5; y++) {
				painting[i][y] = in.readLine().toCharArray();
			}
		}
		
		int diffNum = Integer.MAX_VALUE, minNum = Integer.MAX_VALUE, answer[] = new int[2];
		for(int i = 0; i < N - 1; i++) {
			for(int j = i + 1; j < N; j++) {
				diffNum = countDiffNum(painting[i], painting[j]);
				
				if(minNum > diffNum) {
					minNum = diffNum;
					answer[0] = i + 1;
					answer[1] = j + 1;
				}
			}
		}
		
		System.out.print(answer[0] + " " + answer[1]);
	}
	
	private static int countDiffNum(char[][] one, char[][] two) {
		int cnt = 0;
		
		for(int y = 0; y < 5; y++)
			for(int x = 0; x < 7; x++)
				if(one[y][x] != two[y][x]) cnt++;
		
		return cnt;
	}
}