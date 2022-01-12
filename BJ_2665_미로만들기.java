package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 1261 알고스팟이랑 동일한 문제
 *
 * Memoi 배열을 하나 만들어 놓고 Memoi 배열보다 크거나 같으면
 *
 * 가지 치기를 해나가는 Backtracking으로 풀 수 있는 문제이다.
 *
 * Memory:14880KB / Time:244ms
 */

public class BJ_2665_미로만들기 {

    private static int N;

    private static int[][] memoi;
    private static boolean[][] isBlackRoom;

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception {
        input();

        initMemoi();

        dfs(0, 0, 0);

        System.out.print(memoi[N - 1][N - 1]);
    }

    private static void dfs(int y, int x, int changeCnt) {
        if (memoi[y][x] <= changeCnt) return;

        memoi[y][x] = changeCnt;

        if (y == N - 1 && x == N - 1) return;

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (!(0 <= ny && ny < N && 0 <= nx && nx < N)) continue;

            dfs(ny, nx, changeCnt + (isBlackRoom[ny][nx] ? 1 : 0));
        }
    }

    private static void initMemoi() {
        memoi = new int[N][N];

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                memoi[y][x] = 98; // 최대 나올 수 있는 값
            }
        }
    }

    private static void input() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());

        isBlackRoom = new boolean[N][N];

        char[] line;
        for (int y = 0; y < N; y++) {
            line = in.readLine().toCharArray();
            for (int x = 0; x < N; x++) {
                if (line[x] == '0')
                    isBlackRoom[y][x] = true;
            }
        }

        in.close();
    }

}
