import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	final static int alen = 26;
	static int[][] alphaNum = new int[2][26];

	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine().trim().toUpperCase(); // 공백 없애고 다 대문자로 받음
		
		init();
		alphaCnt( s );
		sort();
		
		bw.write( output() );
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static char output() {
		if(alphaNum[1][0] == alphaNum[1][1]) return '?';
		else return (char)alphaNum[0][0];
	}

	private static void init() {
		for(char c = 'A'; c <= 'Z'; c++) {
			alphaNum[0][c - 65] = c;
		}
	}

	private static void alphaCnt(String s) {
		int length = s.length();
		
		for(int i = 0; i < length; i++) {
			alphaNum[1][s.charAt(i) - 65]++;
		}
	}

	private static void sort() {
		int[][] tmp = new int[2][1];
		
		for(int i = 0; i < alen - 1; i++) {
			for(int j = 0; j < alen - 1 - i; j++) {
				if(alphaNum[1][j] < alphaNum[1][j + 1]) {
					tmp[0][0] 			= alphaNum[0][j];
					tmp[1][0] 			= alphaNum[1][j];
					
					alphaNum[0][j] 		= alphaNum[0][j + 1];	
					alphaNum[1][j]  	= alphaNum[1][j + 1];
					
					alphaNum[0][j + 1] 	= tmp[0][0];	
					alphaNum[1][j + 1] 	= tmp[1][0];
				}
			}
		}
	}
}