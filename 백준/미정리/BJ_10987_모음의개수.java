import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_10987_모음의개수 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		char[] moum = { 'a', 'e', 'i', 'o', 'u' };
		char[] str = in.readLine().toCharArray();
		
		int cnt = 0;
		for(int i = 0; i < str.length; i++) {
			for(int j = 0; j < moum.length; j++) {
				if(str[i] == moum[j]) {
					cnt++;
					break;
				}
			}
		}
		
		System.out.println(cnt);
		
		in.close();
	}

}
