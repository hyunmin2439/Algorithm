import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * KMP 알고리즘을 사용하며, 해당 단어가 있는지를 찾기 위해 BinarySearch를 구현하여 사용
 * 
 * 최종 결과값 출력을 위해 Preorder 운행법으로 StringBuilder 클래스에 추가 후 최종 출력
 * 
 * Memory:15638KB / Time:172ms
 */

public class BJ_14725_개미굴_KMP_BinarySearch {

	static int N, K;
	
	static Node header;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		header = new Node("");
		
		N = Integer.parseInt(in.readLine());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			
			K = Integer.parseInt(st.nextToken());
			String[] feedArr = new String[K];
			for(int j = 0; j < K; j++) {
				feedArr[j] = st.nextToken();
			}
			
			header.add(0, feedArr);
		}
		
		System.out.print( header.printFeed(-1, new StringBuilder()) );
		
		in.close();
	}

	static class Node {
		private String feed;
		private List<Node> list;
		
		public Node(String feed) {
			super();
			this.feed = feed;
			this.list = new LinkedList<>();
		}
		
		private void add(int arrIdx, String[] feedArr) {
			if(arrIdx == feedArr.length) return;
			
			// 해당 먹이가 있는 위치를 찾음
			int addIdx = binarySearch(feedArr[arrIdx], 0, list.size() - 1);
			
			// 음수가 나오면 먹이가 없다는 것
			if( addIdx < 0 ) {
				addIdx = -(addIdx + 1); // 먹이를 넣을 위치로 변환
				list.add(addIdx, new Node(feedArr[arrIdx]));
			}
			
			list.get(addIdx).add(arrIdx + 1, feedArr);
		}
		
		// String BinarySearch - 오름차순
		private int binarySearch(String feed, int low, int high) {
			int mid = (low + high) / 2;

			if(low > high) return -(low + 1);
			
			int compareValue = feed.compareTo(list.get(mid).feed);
			
			if( compareValue == 0 ) return mid;
			else if( compareValue < 0 ) return binarySearch(feed, low, mid - 1);
			else return binarySearch(feed, mid + 1, high);
		}
		
		// Preorder : Root - Left - Right
		private StringBuilder printFeed(int floors, StringBuilder sb) {
			if(floors >= 0) {			
				for(int i = 0; i < floors; i++) 
					sb.append("--");
			
				sb.append(this.feed).append('\n');			
			}
			
			for (Node node : list) {
				node.printFeed(floors + 1, sb);
			}
			
			return sb;
		}
	}
}
