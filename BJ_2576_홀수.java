package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BJ_2576_홀수 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> list = new ArrayList<>();
		
		int num;
		for(int i = 0; i < 7; i++) {
			num = Integer.parseInt(in.readLine());
			
			if(num % 2 != 0) list.add(num);
		}
		
		in.close();
		
		if(list.size() == 0) {
			System.out.print(-1);
			return;
		}
		
		list.sort(Comparator.naturalOrder());
		num = 0;
		
		for(int i = 0; i < list.size(); i++) {
			num += list.get(i);
		}
		
		System.out.print(num + "\n" + list.get(0));
		
		
	}

}
