package solved.submit;

import java.io.*;

public class BJ_11719_그대로출력하기2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
				
		for (int i = n; i > 0; i--) {
			for (int j = 0; j < n - i; j++) {
				sb.append(" ");
			}
			for (int j = 0; j < i * 2 - 1; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
}
