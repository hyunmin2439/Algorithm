import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws Exception {
		String S = new BufferedReader(new InputStreamReader(System.in)).readLine();
		Set<String> set = new HashSet<>();
		
		for(int i = 0; i < S.length(); i++) {
			for(int j = i + 1; j <= S.length(); j++) {
				set.add(S.substring(i, j));
			}
		}
		
		System.out.println(set.size());
	}

}
