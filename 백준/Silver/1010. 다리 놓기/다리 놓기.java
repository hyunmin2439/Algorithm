import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int t = Integer.parseInt(br.readLine());
		
		while(t > 0) {
			long num = 1;
			String[] line = br.readLine().split(" ");
			
			int n = Integer.parseInt(line[0]);
			int m = Integer.parseInt(line[1]);
			
			int pNum = m - n;
			
			for(int i = 1; i <= n; i++) {
				num *= i + pNum;
				num /= i;
			}

			t--;
			
			sb.append(num + "\n");
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

}