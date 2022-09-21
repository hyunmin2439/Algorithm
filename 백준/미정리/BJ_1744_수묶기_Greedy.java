package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/*
 * Greedy
 * 
 * 1. 큰수끼리 곱하는게 유리, 음수와 음수끼리 곱하면 양수
 * 
 * 2. 음수와 양수로 분리
 * 
 * 3. 음수는 오름차순, 양수는 오름차순으로 정렬
 * 
 * 4. 음수 * 0 > 양수 * 0 -> 0은 음수쪽 리스트로
 * 
 * 5. 어느 한 쪽이라도 1이면 곱하는 것보다 더하는 것이 유리
 * 
 * Memory:14216KB / Time:124ms
 */

public class BJ_1744_수묶기_Greedy {
	
	private static List<Integer> negNumList, posNumList;
	
	public static void main(String[] args) throws Exception {
		input();
		
		int sum = solve();
		
		System.out.print(sum);
	}
	
	private static int solve() {
		int sum = 0;
		
		negNumList.sort(Comparator.naturalOrder());		
		sum += listMulAndAdd(negNumList);
		
		posNumList.sort(Comparator.reverseOrder());
		sum += listMulAndAdd(posNumList);
		
		return sum;
	}
	
	private static int listMulAndAdd(List<Integer> list) {
		boolean isOdd = false;
		int sum = 0, num1, num2, size;
		
		size = list.size();
		
		if(size % 2 != 0) {
			isOdd = true;
			size--;
		}
		
		for(int i = 0; i < size; i += 2) {
			num1 = list.get(i);
			num2 = list.get(i + 1);
			
			if(num1 != 1 && num2 != 1)
				sum += num1 * num2;
			else 
				sum += num1 + num2;
		}
		
		if( isOdd )
			sum += list.get(size);
		
		return sum;
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		negNumList = new LinkedList<>();
		posNumList = new LinkedList<>();
		
		int N = Integer.parseInt(in.readLine());
		
		int num = 0;
		for(int i = 0; i < N; i++) {
			num = Integer.parseInt(in.readLine());
			
			if(num <= 0) negNumList.add(num);
			else posNumList.add(num);
		}
		
		in.close();
	}

}
