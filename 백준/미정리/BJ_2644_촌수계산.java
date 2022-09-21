package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 예시1을 예를 들어서 설명
 * 
 * 1. 각각 자신을 포함한 부모 리스트를 가져옴
 * ex) 7 2 1 | 3 1
 * 
 * 2. 중복되는 부모 제거
 * ex) 7 2 | 3
 * 
 * 3. 남은 숫자 개수가 촌수
 * ex) 2 + 1 = 3
 * 
 * Memory: 14092KB / Time: 124ms
 */

public class BJ_2644_촌수계산 {

	private static int N, M, A, B, answer, parent[];
	
	public static void main(String[] args) throws Exception {
		input();
		
		int res = A == B ? 0 : solve();
		
		System.out.print(res);
	}
	
	private static int solve() {
		List<Integer> parentListA = findParent(new ArrayList<Integer>(), A);
		List<Integer> parentListB = findParent(new ArrayList<Integer>(), B);
		
		int idxA = parentListA.size() - 1, idxB = parentListB.size() - 1;
		
		// 조상이 같지 않으면 친적 관계가 아님
		if(parentListA.get(idxA) != parentListB.get(idxB))
			return -1; 
		
		// 같은 부모 제거
		while(--idxA >= 0 & --idxB >= 0 && parentListA.get(idxA) == parentListB.get(idxB));
		
		// 남은 숫자가 촌수, index 0부터 시작 +2
		return idxA + idxB + 2;
	}
	
	private static List<Integer> findParent(List<Integer> list, int num) {
		list.add(num);
		
		if(num == parent[num]) return list;
		
		return findParent(list, parent[num]);
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		parent = new int[N + 1];
		for(int i = 1; i <= N; i++)
			parent[i] = i;
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(in.readLine());
		
		int x, y;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			parent[y] = x;
		}
		
		in.close();
	}

}
