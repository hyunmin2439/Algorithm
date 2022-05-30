import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
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