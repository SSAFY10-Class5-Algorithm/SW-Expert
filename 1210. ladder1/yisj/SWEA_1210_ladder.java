package day0809;

import java.util.Scanner;

public class SWEA_1210_ladder {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int tc =1; tc<=10; tc++) {
			sc.next();
			
			int[][] map = new int[100][100]; // 2차원 배열
			
			// 도착 지점의 (r, c) 좌표
			int endR = -1;
			int endC = -1;
			
			// 입력 받기
			for(int r=0; r<100; r++) {
				for(int c=0; c<100; c++) {
					int num = sc.nextInt();
					if(num == 1) {
						map[r][c] = 1;
					} else if(num == 2) { // 도착 지점
						// 도착 지점의 (r, c) 좌표를 기억해둔다.
						endR = r; // 99
						endC = c;
					}
				}
			}
			
			// 아이디어
			// 도착지점에서 거슬러 올라간다.
			int r = endR; // 현재 행 좌표
			int c = endC; // 현재 열 좌표
			
			while(r > 0) {
			// 반복 조건 : r > 0 => r = 3, 2, 1
				//1. 양 옆으로 갈 수 있는지 조사
				//  -> 갈 수 있다면 가기
				// 1.1. 왼쪽 으로 갈 수 있다면
				if(c-1>=0 && map[r][c-1] == 1) {
					// 왼쪽으로 끝까지 가야함
					while(c-1>=0 && map[r][c-1] == 1) {
						c--;
					}
					
				} else if(c+1 < 100 && map[r][c+1] ==1 ) { 
					// 1.2. 오른쪽으로 갈 수 있다면
					// 오른쪽으로 끝까지 가야함
					while(c+1 < 100 && map[r][c+1] ==1) {
						c++;
					}
					
				}
				
				//2. 한 칸 위로 올라가기
				r--;
			}
			// 종료 조건 : r == 0 (while문을 빠져나왔을 떄)
			System.out.println("#"+tc+" "+c);
		}
	}
}
