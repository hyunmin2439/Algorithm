import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
		StringBuilder sb = new StringBuilder();
		StringTokenizer st1 = new StringTokenizer(input, ".");
		StringTokenizer st2 = new StringTokenizer(input, "X");
		
		if( input.charAt(0) == '.' ) {
			sb.append(st2.nextToken());
		}
		
		int len, remain;
		String X, dot;
		while( st1.hasMoreTokens() || st2.hasMoreTokens() ) {
		
			if( st1.hasMoreTokens() ) {
				X = st1.nextToken();
				len = X.length();
				
				if(len % 2 != 0) {
					sb.setLength(0);
					sb.append(-1);
					break;
				}
				
				remain = len % 4;
				len = len / 4;
				
				for(int i = 0; i < len; i++) {
					sb.append("AAAA");
				}
				
				if( remain != 0)
					sb.append("BB");
			}
			
			if( st2.hasMoreTokens() )
				sb.append( st2.nextToken() );
		}
		
		System.out.print(sb);
	}

}