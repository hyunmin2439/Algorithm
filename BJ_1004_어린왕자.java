import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제를 간단히 정리하자면, 출발지에서 목적지로 갈 때 행성의 경계를 최소한으로 통과해야 한다는 것.
 * 
 * 간단하게 행성을 원으로 생각하자면, 원 반경을 최소한으로 지나는 횟수를 구하는 문제.
 * 
 * 예시 그림이 가장 문제를 잘 설명하고 있다. 예시 그림만 이해하면 간단한 수학 공식을 적용해 풀 수 있는 문제.
 * 
 * 피타고라스의 정리 : a^2 + b^2 = c^2 
 * => (x1-x2)^2 + (y1-y2)^2 = r^2 (r : 반지름)
 * => 원의 중심좌표(x1, y1)부터 임의의 원의 경계좌표(x2, y2) 까지의 거리 공식
 * 
 * (x1-x2)^2 + (y1-y2)^2 < r^2 (r : 반지름)
 * => 즉, 임의의 좌표를 (x2, y2)라고 바꾼다면 r^2보다 작으면 원 안에 포함 => 경계를 지나야 함
 * r^2 크면 원 안에 포함되어 있지 않은 좌표임으로 경계를 지날 필요가 없다.
 * 
 * 이를 토대로 각 주어지는 좌표마다 횟수를 구하면 된다.
 * 
 * 단, 이때 출발지로 부터 목적지로 가는 경우이기 때문에 출발지, 목적지 좌표가 한 원안에 포함된다면,
 * 이는 경계를 지날 필요가 없는 것임으로 이 경우와 둘다 원안에 포함이 안되는 경우를 제외하고 체크.
 * 
 * Memory:15612KB / Time:188ms
 */

public class BJ_1004_어린왕자 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		StringTokenizer st;
		while(T-- > 0) {
			int enterOrLeaveCnt = 0;
			
			st = new StringTokenizer(in.readLine());
			
			Node start = new Node( Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) );
			Node end = new Node( Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) );
			
			int N = Integer.parseInt(in.readLine());
			
			int x, y, r;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				r = Integer.parseInt(st.nextToken());
				
				boolean startCheck = isInsideThePlanet(start, x, y, r);
				boolean endCheck = isInsideThePlanet(end, x, y, r);
				
				if( isEnterOrLeave(startCheck, endCheck) )
					enterOrLeaveCnt++;
			}
			
			System.out.println(enterOrLeaveCnt);
		}
		
		in.close();
	}
	
	private static boolean isEnterOrLeave(boolean startCheck, boolean endCheck) {
		if( startCheck == endCheck ) return false;
		
		return true;
	}

	private static boolean isInsideThePlanet(Node node, int x, int y, int r) {
		// 출발점이나 도착점이 행성계 경계에 걸쳐진 경우 역시 입력으로 주어지지 않는다. = 표기 할 필요X
		if( Math.pow(node.x - x, 2) + Math.pow(node.y - y, 2) < Math.pow(r, 2) ) return true;
		
		return false;
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
}
