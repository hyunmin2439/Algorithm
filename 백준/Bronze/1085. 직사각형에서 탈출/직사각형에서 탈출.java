import java.io.*;

public class Main {

	public static void main(String[] args) {
		int x, y, w, h, result;
		
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		try {
			String[] line = br.readLine().trim().split(" ");
			
			x = Integer.parseInt(line[0]);
			y = Integer.parseInt(line[1]);
			w = Integer.parseInt(line[2]);
			h = Integer.parseInt(line[3]);
			
			result = x;
			
			if( result > y ) result = y;
			if( result > w - x ) result = w - x;
			if( result > h - y ) result = h - y;
			
			bw.write( String.valueOf(result) );
			
		} catch (IOException e) {
		} finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
			}
		}
	}

}
