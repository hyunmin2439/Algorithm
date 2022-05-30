import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int x = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();
		list.add(64);
		int sum = 64;
        
		while ( sum != x ) {
			sum = 0;
			int half = list.remove(0) / 2;
			list.add(0, half);
			
			for(Integer i : list) {
				sum += i;
			}
			
			if(sum < x) {
				list.add(0, half);
				sum += half;
			}
		}
		
		bw.write(String.valueOf(list.size()));
		
		br.close();
		bw.close();
	}

}