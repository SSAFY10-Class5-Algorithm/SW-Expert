import java.util.Scanner;

public class Solution {

	static int[][] map;
	static boolean[][] visit;

	static int N;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		StringBuilder sb = new StringBuilder();

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {

			N = sc.nextInt();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			int ans = 0;
			for (int y = 0; y <= 100; y++) {
				visit = new boolean[N][N];
				int cnt = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (!visit[i][j] && map[i][j] > y) {
							cnt++;
							DFS(i, j, y);
						}

					}
				}

				if (cnt > ans) {
					ans = cnt;
				}
			}

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
			
		}
		System.out.println(sb);
	}

	private static void DFS(int r, int c, int y) {
		// TODO Auto-generated method stub
		visit[r][c] = true;

		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		for (int d = 0; d < 4; d++) {
			int nowR = r + dr[d];
			int nowC = c + dc[d];

			if (nowR >= 0 && nowC >= 0 && nowR < N && nowC < N && !visit[nowR][nowC] && map[nowR][nowC] > y) {
				visit[nowR][nowC] = true;
				DFS(nowR, nowC, y);
			}

		}

	}
}