import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_14891_톱니바퀴 {
	
	private static int gear[], K, inst[][];
	private static List<Node> rotateList;
	
	public static void main(String[] args) throws Exception {
		input();
		
		solve();
	}
	
	private static void solve() {
		Node tmp;
		
		// 회전 요청 담기
		for(int i = 0; i < K; i++) {
			rotateList.add( new Node(inst[i][0], inst[i][1]) );
			
			if( inst[i][0] > 0)
				rotate(inst[i][0] - 1, gear[ inst[i][0] ] & 1 << 6, inst[i][1] * -1, -1); // 좌측 톱니로
			if( inst[i][0] < 3)
				rotate(inst[i][0] + 1, gear[ inst[i][0] ] & 1 << 2, inst[i][1] * -1, 1); // 우측 톱니로
			
			for(int j = 0; j < rotateList.size(); j++) {
				tmp = rotateList.get(j);
				
				System.out.println(tmp.num + " " + tmp.dir);
				if(tmp.dir == -1) {
					gear[tmp.num] <<= 1; // 1000_0000 -> 1_0000_0000
					if( gear[tmp.num] > 255 ) 
						gear[tmp.num] -= 255; // // 1_0000_0000 -> 0000_0001
				}
				else {
					if( gear[tmp.num] % 2 == 1 ) 
						gear[tmp.num] += 256; // 0100_0001 -> 1_0100_0001
					gear[tmp.num] >>= 1; // 1_0100_0001 -> 1010_0000
				}
				
			}
			
			printGear();
			rotateList.clear();
		}
		
		// 점수 계산
		
		int score = 0;
		for(int i = 0; i < 4; i++) {
			if( (gear[i] & 1) == 1 )
				score += Math.pow(2, i);
		}
		
		System.out.print(score);
	}
	
	private static void printGear() {
		
		for(int k = 0; k < 4; k++) {
			for(int i = 0; i < 8; i++) {
				if( (gear[k] & 1 << i) == 1 << i )
					System.out.print(1);
				else
					System.out.print(0);
			}
			System.out.println();
		}

		System.out.println("----------------------------");
	}
	
	private static void rotate(int num, int prevNS, int dir, int sw) {
		if( !(0 <= num && num <= 3) ) return;
		
		if( ( gear[num] & (1 << (sw == -1 ? 2 : 6)) ) != prevNS) return;
		
		rotateList.add( new Node(num, dir) );
		
		rotate(num + sw, gear[num] & 1 << (sw == -1 ? 6 : 2), dir * -1, sw);
	}

	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		gear = new int[4];
		for(int i = 0; i < 4; i++) {
			gear[i] = Integer.parseInt(in.readLine(), 2); // 2진수로 입력받기
		}
		
		K = Integer.parseInt(in.readLine());
		inst = new int[K][2];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			
			inst[i][0] = Integer.parseInt(st.nextToken()) - 1;
			inst[i][1] = Integer.parseInt(st.nextToken());
		}
		
		rotateList = new ArrayList<>();
		
		in.close();
	}
	
	static class Node {
		int num, dir;
		
		public Node(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}
	}
}
