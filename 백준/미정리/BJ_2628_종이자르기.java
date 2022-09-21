package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// Memory:14172KB / Time:132ms
public class BJ_2628_종이자르기 {

	private static List<Integer> cutY, cutX;
	
	public static void main(String[] args) throws Exception {
		input();
		
		int max = solve();
		
		System.out.print(max);
	}
	
	private static int solve() {
		int tmp, max = 0;
		
		cutY.sort(Comparator.naturalOrder());
		cutX.sort(Comparator.naturalOrder());
		
		int ey = cutY.size(), ex = cutX.size();
		for(int y = 1; y < ey; y++) {
			for(int x = 1; x < ex; x++) {
				int ycm = cutY.get(y) - cutY.get(y - 1);
				int xcm = cutX.get(x) - cutX.get(x - 1);
				
				tmp = ycm * xcm;
				max = tmp > max ? tmp : max;
			}
		}
		
		return max;
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		cutY = new LinkedList<>();
		cutX = new LinkedList<>();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		cutX.add(0);
		cutX.add(Integer.parseInt(st.nextToken()));
		cutY.add(0);
		cutY.add(Integer.parseInt(st.nextToken()));
		
		int N = Integer.parseInt(in.readLine());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			
			int pos = Integer.parseInt(st.nextToken());
			int cutNum = Integer.parseInt(st.nextToken());
			
			if(pos == 0)
				cutY.add(cutNum);
			else
				cutX.add(cutNum);
		}
		
		in.close();
	}

}
