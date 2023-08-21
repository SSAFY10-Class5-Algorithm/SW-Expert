package day0810;

import java.util.Scanner;

public class SWEA_11315_오목판정 {

	// 우상, 우, 우하, 하
	static int[] dr = {-1, 0, 1, 1};
	static int[] dc = {1, 1, 1, 0};
	static int N; // 2차원 배열의 크기
	static char[][] arr; // 2차원 배열
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			arr = new char[N][N];
			for(int r=0; r<N; r++) {
				// 문자열로 입력 받는다면
//				arr[r] = sc.next().split("");
				// 문자로 끊어서 2차원 배열에 저장
				String str = sc.next();
				for(int c=0; c<N; c++) {
					arr[r][c] = str.charAt(c);
				}
			}
			
			boolean flag = false; // 오목이 있는지 여부
			// 일단 없다고 가정하고 시작
			// 2차원 배열을 행우선순회 하면서
			// 오목을 발견하면 flag = true로 바꾸고 
			// 반복을 모두 종료.
			
			out: for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					// 만약 r,c가 돌이라면 그 지점부터 8방 탐색
					if(arr[r][c] == 'o' && isOmok(r, c)) {
						// 오목이 있는 경우
						flag = true;
						break out;
					}
				}
			}
			
			if(flag)
				System.out.println("#"+tc+" YES");
			else
				System.out.println("#"+tc+" NO");
			
			
		}
	}

	private static boolean isOmok(int r, int c) {
		// (r, c)기점으로 해서 4방 탐색
		out: for(int d=0; d<4; d++) { // 4가지 방향 탐색
			for(int k=1; k<=4; k++) { // 옆으로 4칸을 이어서 본다.
				int nr = r + dr[d] * k;
				int nc = c + dc[d] * k;
				
				// 경계조건 체크
				if(nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue out; // 경계조건이 맞지 않는다면 그 다음 방향으로
				
				if(arr[nr][nc] != 'o')
					continue out;
			}
			return true; // 오목이 있다.
			
		} // 4방향을 다 봤는데도 true가 안됐으면...
		return false; // 오목이 없다.
	}
}
