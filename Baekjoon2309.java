package solved.submit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon2309 {

	static int sum;
	static int[] nine;
	static BufferedReader br; 
	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader( new InputStreamReader(System.in) );
		bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		nine = new int[9];
		
		for (int i = 0; i < 9; i++) {
			nine[i] = Integer.parseInt(br.readLine());
			sum += nine[i];
		}
		
		findDwarf();
		
		Arrays.sort(nine);
		
		for (int i = 2; i < 9; i++) {
			bw.write(nine[i] + "\n");
		}
		
		br.close();
		bw.close();
	}

	private static void findDwarf() {
		for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j < 9; j++) {
				int temp = sum;
				temp = temp - nine[i] - nine[j];
				if(temp == 100) {
					nine[i] = 0;
					nine[j] = 0;
					return;
				}
			}
		}
	}

}
