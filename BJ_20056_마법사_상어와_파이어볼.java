package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// Memory: 36,632KB / Time: 556ms

public class BJ_20056_마법사_상어와_파이어볼 {

	private static int N, M, K;
	
	//                           0   1  2  3  4   5   6   7
	private static int[] dy = { -1, -1, 0, 1, 1,  1,  0, -1 };
	private static int[] dx = {  0,  1, 1, 1, 0, -1, -1, -1 };
	
	public static void main(String[] args) throws Exception {
		List<Node> fireballs = input();
		
		List<List<List<Node>>> map = initMap();
		
		List<Node> tmp = null;
		while(K-- > 0) {
			// 이동 처리
			for(int i = 0; i < fireballs.size(); i++) {
				move(map, fireballs.get(i));
			}
			
			fireballs.clear(); // fireballs 리스트 클리어
			
			// 맵 탐색
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					tmp = map.get(r).get(c);
					
					if(tmp.size() == 1) // 파이어볼 하나
						fireballs.addAll(tmp);
					else if(tmp.size() > 1) // 중첩된 파이어볼
						fireballs.addAll( divide(tmp) );
					
					tmp.clear();
				}
			}
		}
		
		int answer = 0;
		
		for(int i = 0; i < fireballs.size(); i++) {
			answer += fireballs.get(i).m;
		}
		
		System.out.println(answer);
	}
	
	private static List<List<List<Node>>> initMap() {
		List<List<List<Node>>> map = new ArrayList<>();
		
		List<List<Node>> row = null;
		for(int r = 0; r < N; r++) {
			map.add(new ArrayList<>());
			
			row = map.get(r);
			for(int c = 0; c < N; c++) {
				row.add(new ArrayList<>());
			}
		}
		
		return map;
	}
	
	private static void move(List<List<List<Node>>> map, Node fireball) {
		fireball.r += (dy[fireball.d] * fireball.s) % N;
		fireball.c += (dx[fireball.d] * fireball.s) % N;
		
		if(fireball.r < 0)
			fireball.r = N + fireball.r;
		else if(fireball.r >= N)
			fireball.r = fireball.r - N;
		
		if(fireball.c < 0)
			fireball.c = N + fireball.c;
		else if(fireball.c >= N)
			fireball.c = fireball.c - N;
		
		map.get(fireball.r).get(fireball.c).add(fireball);
	}
	
	private static List<Node> divide(List<Node> fireballs) {
		int sumM = 0, sumS = 0, d = 0;
		boolean isEven = false, isOdd = false;
		
		Node fireball = null;
		for(int i = 0; i < fireballs.size(); i++) {
			fireball = fireballs.get(i);
			
			sumM += fireball.m;
			sumS += fireball.s;
			
			if(fireball.d % 2 == 0)
				isEven = true;
			if(fireball.d % 2 == 1)
				isOdd = true;
		}
		
		if(isEven && isOdd) d = 1;
		
		sumM = Math.floorDiv(sumM, 5);
		sumS = Math.floorDiv(sumS, fireballs.size());
		
		List<Node> divideFireballs = new ArrayList<>();
		
		if(sumM == 0)
			return divideFireballs;
		
		for(int i = 0; i < 4; i++) {
			divideFireballs.add(new Node(fireball.r, fireball.c, sumM, sumS, i * 2 + d));
		}
		
		return divideFireballs;
	}
	
	private static List<Node> input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		List<Node> fireballs = new ArrayList<>();
		
		int r, c, m, s, d;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			m = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			
			fireballs.add( new Node(r, c, m, s, d) );
		}
		
		in.close();
		
		return fireballs;
	}

	static class Node {
		int r, c, m, s, d;
		
		public Node(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}
		
	}
}
