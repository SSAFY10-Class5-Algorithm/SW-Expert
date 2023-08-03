import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Solution { // 정사각형 방
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int move = Integer.MIN_VALUE;
    static boolean[][] visit;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(bf.readLine());
            int[][] map = new int[N][N];
            visit = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                String[] str = bf.readLine().split(" ");
                for (int c = 0; c < str.length; c++) {
                    map[r][c] = Integer.parseInt(str[c]);
                }
            }
            int maxMove = 0;
            int minRoom = N*N -1;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    move = Integer.MIN_VALUE;
                    move(map, r, c, N, 1);
                    if (maxMove < move) {
                        maxMove = move;
                        minRoom = map[r][c];
                        continue;
                    }
                    if (maxMove == move && map[r][c] < minRoom) {
                        minRoom = map[r][c];
                    }

                    visit = new boolean[N][N];
                }
            }
            System.out.println("#"+tc +" "+minRoom + " " + maxMove);
            move = Integer.MIN_VALUE;
        }
    }

    static void move(int[][] map, int r, int c, int N, int count) {
		visit[r][c] = true;
        for (int d = 0; d < 4; d++) {
            if (r + dy[d] >= 0 && r + dy[d] < N && c + dx[d] >= 0 && c + dx[d] < N) {
                if ((map[r][c] + 1) == map[r + dy[d]][c + dx[d]]) {
                    if (!visit[r + dy[d]][c + dx[d]]) move(map, r + dy[d], c + dx[d], N, count + 1);
                }
            }
        }
        visit[r][c] = false;
        if (move < count) move = count;
    }
}