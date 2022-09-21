package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14,216KB / Time:12ms
public class BJ_3048_개미 {
	
	private static final int LEFT = 1, RIGHT = 2;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N1 = Integer.parseInt(st.nextToken());
		int N2 = Integer.parseInt(st.nextToken());

		Ant[] ants = new Ant[N1 + N2];
		
		String input = in.readLine();
		
		for(int i = N1 - 1; i >= 0; i--) {
			ants[N1 - i - 1] = new Ant(input.charAt(i), RIGHT);
		}
		
		input = in.readLine();
		
		for(int i = 0; i < N2; i++) {
			ants[N1 + i] = new Ant(input.charAt(i), LEFT);
		}
		
		int T = Integer.parseInt(in.readLine());
		
		int end = N1 + N2;
		
		Ant tmp;
		
		while(T-- > 0) {
			for(int i = 0; i < end - 1; i++) {
				if(ants[i].direc > ants[i + 1].direc) {
					tmp = ants[i];
					ants[i] = ants[i + 1];
					ants[i + 1] = tmp;
					i++;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < end; i++) {
			sb.append(ants[i].alpha);
		}
		
		System.out.print(sb);
		
		in.close();
	}

	static class Ant {
		char alpha;
		int direc;
		
		public Ant(char alpha, int direc) {
			this.alpha = alpha;
			this.direc = direc;
		}
	}
}
