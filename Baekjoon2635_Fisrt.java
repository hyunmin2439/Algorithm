package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 수 이어가기
// Second file에 내용 정리

//memory : 15416KB / time:168ms
public class Baekjoon2635_Fisrt {
	static int N, max;
	static int[] num = new int[3];
	
	static List<Integer> list = new ArrayList<>();
	static List<Integer> tlist = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		for (int i = N / 2; i <= N; i++) {
			tlist.clear();
			
			num[0] = N;
			num[1] = i;
			num[2] = N - i;
			tlist.add(num[0]);
			tlist.add(num[1]);
			tlist.add(num[2]);
			
			while( num[2] >= 0 ) {
				num[0] = num[1];
				num[1] = num[2];
				
				num[2] = num[0] - num[1];
				
				tlist.add(num[2]);
			}
			
			tlist.remove(tlist.size() - 1);
			
			if(max < tlist.size()) {
				max = tlist.size();
				list = new ArrayList<>(tlist);
			}
			
		}
		
		System.out.println(max);
		for (int i = 0; i < max; i++) {
			System.out.print(list.get(i) + " ");
		}
	}
}
