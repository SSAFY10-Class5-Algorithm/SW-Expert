import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int count = 0;
	static int nowTime = 0;
	static boolean[][] posible;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		int N; // 행
		int M; // 열
		int R; // 맨홀 행
		int C; // 맨홀 열
		int L; // 소요시간
		int[][] map;
		boolean[][] visit;
		for (int tc = 1; tc <= T; tc++) {
			String[] input = bf.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			R = Integer.parseInt(input[2]);
			C = Integer.parseInt(input[3]);
			L = Integer.parseInt(input[4]);
			map = new int[N][M];
			visit = new boolean[N][M];
			posible = new boolean[N][M];

			visit[R][C] = true;
			posible[R][C] = true;
			for (int r = 0; r < N; r++) {
				String[] row = bf.readLine().split(" ");
				for (int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(row[c]);
				}
			}

			move(N, M, R, C, L, map, visit, 1);

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (posible[r][c])
						count++;
				}
			}
			System.out.println("#" + tc + " " + count);
			count = 0;
			nowTime = 1;
			posible = null;
		}
	}

	static void move(int N, int M, int r, int c, int L, int[][] map, boolean[][] visit, int time) {
		if (time == L)
			return;
		for (int d = 0; d < 4; d++) {
			if (r + dy[d] >= 0 && r + dy[d] < N && c + dx[d] >= 0 && c + dx[d] < M) {
				if (visit[r + dy[d]][c + dx[d]])
					continue;
				// 상
				if (d == 0) {
					if (map[r][c] == 1 || map[r][c] == 2 || map[r][c] == 4 || map[r][c] == 7) {
						if (map[r + dy[d]][c + dx[d]] == 1 || map[r + dy[d]][c + dx[d]] == 2
								|| map[r + dy[d]][c + dx[d]] == 5 || map[r + dy[d]][c + dx[d]] == 6) {
							visit[r + dy[d]][c + dx[d]] = true;
							posible[r + dy[d]][c + dx[d]] = true;
							move(N, M, r + dy[d], c + dx[d], L, map, visit, time + 1);
							visit[r + dy[d]][c + dx[d]] = false;
						}
					}
				}
				// 하
				if (d == 1) {
					if (map[r][c] == 1 || map[r][c] == 2 || map[r][c] == 5 || map[r][c] == 6) {
						if (map[r + dy[d]][c + dx[d]] == 1 || map[r + dy[d]][c + dx[d]] == 2
								|| map[r + dy[d]][c + dx[d]] == 4 || map[r + dy[d]][c + dx[d]] == 7) {
							visit[r + dy[d]][c + dx[d]] = true;
							posible[r + dy[d]][c + dx[d]] = true;
							move(N, M, r + dy[d], c + dx[d], L, map, visit, time + 1);
							visit[r + dy[d]][c + dx[d]] = false;
						}
					}
				}
				// 좌
				if (d == 2) {
					if (map[r][c] == 1 || map[r][c] == 3 || map[r][c] == 6 || map[r][c] == 7) {
						if (map[r + dy[d]][c + dx[d]] == 1 || map[r + dy[d]][c + dx[d]] == 3
								|| map[r + dy[d]][c + dx[d]] == 4 || map[r + dy[d]][c + dx[d]] == 5) {
							visit[r + dy[d]][c + dx[d]] = true;
							posible[r + dy[d]][c + dx[d]] = true;
							move(N, M, r + dy[d], c + dx[d], L, map, visit, time + 1);
							visit[r + dy[d]][c + dx[d]] = false;
						}
					}
				}
				// 우
				if (d == 3) {
					if (map[r][c] == 1 || map[r][c] == 3 || map[r][c] == 4 || map[r][c] == 5) {
						if (map[r + dy[d]][c + dx[d]] == 1 || map[r + dy[d]][c + dx[d]] == 3
								|| map[r + dy[d]][c + dx[d]] == 6 || map[r + dy[d]][c + dx[d]] == 7) {
							visit[r + dy[d]][c + dx[d]] = true;
							posible[r + dy[d]][c + dx[d]] = true;
							move(N, M, r + dy[d], c + dx[d], L, map, visit, time + 1);
							visit[r + dy[d]][c + dx[d]] = false;
						}
					}
				}
			}
		}
		nowTime++;
	}
}
