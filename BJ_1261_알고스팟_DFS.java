package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Memoization + DFS 또는 Backtracking 으로 간단히 풀 수 있는 문제
 *
 * N * M 크기의 Memoization 배열에다 현재 좌표까지 오는데 뚫고 와야하는 최소 벽개수를 기록해둔다.
 *
 * 이를 DFS를 돌면서 현재 벽개수가 Memoization 배열에 기록된 개수 보다 같거나 크면 가지치기.
 *
 * 최종 N - 1, M - 1 좌표에 도달할 때까지 DFS를 돌면 간단히 풀리는 문제이다.
 *
 * Memory:14880KB / Time:244ms
 */

public class BJ_1261_알고스팟_DFS {

    private static int N, M;
    private static int[][] memoi;
    private static boolean[][] isWall;

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        input();

        initMemoi();

        dfs(0, 0, 0);

        System.out.print(memoi[N - 1][M - 1]);
    }


    private static void dfs(int y, int x, int numOfBreakWall) {
        if (memoi[y][x] <= numOfBreakWall) return;

        memoi[y][x] = numOfBreakWall;

        if (y == N - 1 && x == M - 1) return;

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (!(0 <= ny && ny < N && 0 <= nx && nx < M)) continue;

            dfs(ny, nx, numOfBreakWall + (isWall[ny][nx] ? 1 : 0));
        }
    }

    private static void input() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        isWall = new boolean[N][M];

        char[] line;
        for (int y = 0; y < N; y++) {
            line = in.readLine().toCharArray();
            for (int x = 0; x < M; x++) {
                if (line[x] == '1')
                    isWall[y][x] = true;
            }
        }

        in.close();
    }

    private static void initMemoi() {
        memoi = new int[N][M];

        for (int i = 0; i < N; i++)
            Arrays.fill(memoi[i], 198);
    }
}
