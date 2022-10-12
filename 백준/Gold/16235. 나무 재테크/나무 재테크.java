import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 };
	private static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N		   = Integer.parseInt(st.nextToken()), 
			M		   = Integer.parseInt(st.nextToken()), 
			K 	  	   = Integer.parseInt(st.nextToken()),
			A[][] 	   = new int[N + 1][N + 1],
			energy[][] = new int[N + 1][N + 1],
			x = 0, y = 0, z = 0;
		
		List<List<PriorityQueue<Tree>>> earth = initEarth(N);
		PriorityQueue<Tree> tmp = new PriorityQueue<>();
		List<Tree> breedingTrees = new ArrayList<>();
		
		for(int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
				energy[r][c] = 5; // 모든 땅 초기 양분 5
			}
		}
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			
			earth.get(x).get(y).add(new Tree(x, y, z));
		}
		
		for(int i = 0; i < K; i++) {
			springAndSummer(earth, tmp, breedingTrees, energy, N);
			fall(earth, breedingTrees, N);
			winter(energy, A, N);
		}
		
		System.out.println( countTrees(earth, N) );
	}
	
	private static int countTrees(List<List<PriorityQueue<Tree>>> earth, int N) {
		int cnt = 0;
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				cnt += earth.get(r).get(c).size();
			}
		}
		
		return cnt;
	}
	
	private static void springAndSummer(List<List<PriorityQueue<Tree>>> earth, PriorityQueue<Tree> tmp, List<Tree> breedingTrees, int[][] energy, int N) {
		PriorityQueue<Tree> trees;
		Tree tree;
		int nxEnergy = 0;
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				trees = earth.get(r).get(c);
				nxEnergy = 0;
				
				while( !trees.isEmpty() ) {
					tree = trees.poll();
					
					// Summer 나무 죽음 : 미리 처리
					if(tree.z > energy[r][c]) {
						nxEnergy += tree.z / 2; // 다음 나무에 영향가지 않게 따로 변수에 저장
						continue;
					}
					
					energy[r][c] -= tree.z;
					tmp.add(tree);
					// 나이 5의 배수이면 증식할 나무 리스트에 담기
					if(++tree.z % 5 == 0) 
						breedingTrees.add(tree);
				}
				
				energy[r][c] += nxEnergy; // 현재 위치 죽은 나무들의 양분 최종 더하기
				trees.addAll(tmp);
				tmp.clear();
			}
		}
	}
	
	private static void fall(List<List<PriorityQueue<Tree>>> earth, List<Tree> breedingTrees, int N) {
		Tree tree;
		int nr, nc;
		
		for(int i = 0; i < breedingTrees.size(); i++) {
			tree = breedingTrees.get(i);
			
			for(int j = 0; j < dy.length; j++) {
				nr = tree.r + dy[j];
				nc = tree.c + dx[j];
				
				if( !(1 <= nr && nr <= N && 1 <= nc && nc <= N) ) continue;
				
				earth.get(nr).get(nc).add(new Tree(nr, nc, 1));
			}
		}
		
		breedingTrees.clear();
	}
	
	private static void winter(int[][] energy, int[][] A, int N) {
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				energy[r][c] += A[r][c];
			}
		}
	}
	
	private static List<List<PriorityQueue<Tree>>> initEarth(int N) {
		List<List<PriorityQueue<Tree>>> earth = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) {
			earth.add(new ArrayList<>());
			for(int j = 0; j <= N; j++) {
				earth.get(i).add(new PriorityQueue<>());
			}
		}
		
		return earth;
	}
}

class Tree implements Comparable<Tree> {
	int r, c, z;
	
	public Tree(int x, int y, int z) {
		this.r = x;
		this.c = y;
		this.z = z;
	}
	
	@Override
	public int compareTo(Tree other) {
		return this.z - other.z;
	}
}