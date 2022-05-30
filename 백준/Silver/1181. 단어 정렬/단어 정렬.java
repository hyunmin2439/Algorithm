import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Set<String> wordSet = new HashSet<>();
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int n = Integer.parseInt(br.readLine().trim());
		
		while(n > 0) {
			wordSet.add( br.readLine().trim() );
			n--;
		}
		
		List<String> sortList = new ArrayList<>(wordSet);
		
		sortList.sort( new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length() < o2.length())
					return -1;
				else if(o1.length() > o2.length())
					return 1;
				else
					return o1.compareTo(o2);
			}
		});
		
		StringBuilder sb = new StringBuilder();
		
		for(String s : sortList) {
			sb.append(s + "\n");			
		}
		
		bw.write(sb.toString());
		
		br.close();
		bw.close();
	}

}