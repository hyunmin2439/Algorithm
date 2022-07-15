import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char tmp, moum[] = { 'a', 'e', 'i', 'o', 'u' }, str[] = null;;
		int cnt = 0;
		
		while(true) {
			cnt = 0;
			str = in.readLine().toCharArray();
			
			if(str[0] == '#') break;
			
			for(int i = 0; i < str.length; i++) {
				tmp = Character.toLowerCase(str[i]);
				for(int j = 0; j < moum.length; j++) {
					if(tmp == moum[j])
						cnt++;
				}
			}
			
			sb.append(cnt).append('\n');
		}
		
		System.out.print(sb);
	}
}