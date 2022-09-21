package solved.submit;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_1244_스위치켜고끄기 {
	static int n, nos; // nos : numberOfStudents
	static boolean[] state;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// init
		n = Integer.parseInt(br.readLine());
		state = new boolean[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			if(st.nextToken().equals("1")) state[i] = !state[i];
		}
		nos = Integer.parseInt(br.readLine());
		
		// process
		int t = 0;
		while(t++ < nos) {
			String[] line = br.readLine().split(" ");
			changeState(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		}
		
		// output
		bw.write(output());
		br.close();
		bw.close();
	}

	private static String output() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= n; i++) {
			if(i % 20 == 1) sb.append("\n");
			
			if(state[i]) sb.append('1');
			else sb.append('0');
			sb.append(" ");			
		}
		
		return sb.subSequence(1, sb.length()).toString();
	}

	private static void changeState(int gender, int num) {
		switch(gender) {
		case 1:
			for (int i = num; i <= n; i += num) 
				state[i] = !state[i];
			break;
		case 2:
			int i = 1;
			state[num] = !state[num];
			
			while(true) {
				if(num - i < 1 || num + i > n 
						|| state[num - i] != state[num + i] ) break;
				state[num - i] = state[num + i] = !state[num + i];
				i++;
			}
		}
	}

}
