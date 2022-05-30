import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		int cnt = 0, pos;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String line = br.readLine().trim();
		
		if(line.length() == 0) {
			pos = -1;
		}
		else {
			pos = line.indexOf(" ");
			cnt++;
		}
		
		while( pos != -1 ) {
			pos = line.indexOf(" ", pos + 1);
			cnt++;
		}
		
		bw.write( cnt + "" );
		bw.flush();
		br.close();
		bw.close();
		
	}
}