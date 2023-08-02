package D3;

import java.util.Scanner;

public class _6730_장애물경주난이도 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			
			int up = 0;
			int down = 0;
			
			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			
			for (int i = 0; i < N-1; i++) {
				if (arr[i] > arr[i+1]) {
					int temp = arr[i] - arr[i+1];
					down = Math.max(temp, down);
				} else if (arr[i] < arr[i+1]) {
					int temp = arr[i+1] - arr[i];
					up = Math.max(temp, up);
				}
			}	
			System.out.printf("#%d %d %d\n", tc, up, down);
		}
	}
}
