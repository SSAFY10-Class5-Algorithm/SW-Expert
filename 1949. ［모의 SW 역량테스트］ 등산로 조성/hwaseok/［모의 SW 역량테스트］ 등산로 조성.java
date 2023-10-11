import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int[][] arr;
	static int high;
	static int max;
	static int K;
	static int N;
	static boolean[][] check;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			high = 0;
			max = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			check = new boolean[N][N];
			for (int i = 0; i < arr.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < arr[0].length; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					high = Math.max(arr[i][j], high);
				}
			}

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {

					if (arr[i][j] == high) {
						check[i][j] = true;
						dfs(i, j, 1, 1);
						check[i][j] = false;
					}
				}
			}
			System.out.println("#" + tc + " " + max);

		}
	}

	static void dfs(int x, int y, int count, int cut) {
		int bonguri = arr[x][y];
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
				int next = arr[x + dx[i]][y + dy[i]];
				if (next != 0 && !check[x + dx[i]][y + dy[i]]) {

					if (next < bonguri) {
						check[x + dx[i]][y + dy[i]] = true;
						dfs(x + dx[i], y + dy[i], count + 1, cut);
						check[x + dx[i]][y + dy[i]] = false;
					} else if (cut == 1) {
						for (int j = 1; j <= K; j++) {
							if ((next - j) < bonguri) {
								arr[x + dx[i]][y + dy[i]] -= j;
								dfs(x + dx[i], y + dy[i], count + 1, cut - 1);
								arr[x + dx[i]][y + dy[i]] += j;
							}
						}
					}
				}
			}
		}
		if (max < count) {
			max = count;
		}
	}

}