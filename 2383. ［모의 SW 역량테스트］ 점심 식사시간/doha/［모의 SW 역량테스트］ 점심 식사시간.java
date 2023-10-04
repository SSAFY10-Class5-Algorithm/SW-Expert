import java.util.*;

/*
두 계단 중에 어디를 이용해야 빠른지 모르니까
양쪽 다 넣어봐야 함
리스트 2개 만들어서 거리순으로 사람 주기

이용할 계단이 정해졌을 때
현재 원소가 완전히 내려가는 시간은 거리 + 대기시간 + 계단 길이
거리랑 계단 길이는 고정

리스트 < 3 이면 대기 0이고
리스트 >=3 이면 나보다 세 칸 앞에 있는 사람이 언제 도착하는지만 알면 구할 수 있으니까
반복문 안 돌리고 계산으로 구할까....???
 */
public class Solution {
    static class Person {
        // 계단까지 걸리는 시간
        int distanceToStair1;
        int distanceToStair2;
        // 좌표
        int y;
        int x;
        // 계단에서 기다리는 시간
        int stair1Waiting;
        int stair2Waiting;

        public Person(int distanceToStair1, int distanceToStair2, int y, int x) {
            // 가서 1분 기다려야 하니까 미리 더해주기
            this.distanceToStair1 = distanceToStair1 + 1;
            this.distanceToStair2 = distanceToStair2 + 1;
            this.y = y;
            this.x = x;
            this.stair1Waiting = 0;
            this.stair2Waiting = 0;
        }
    }

    static class Stair {
        int length;
        int y;
        int x;

        public Stair(int length, int y, int x) {
            this.length = length;
            this.y = y;
            this.x = x;
        }
    }

    static int N;
    static int[][] room;
    static Stair stair1;
    static Stair stair2;

    // 사람 수
    static int personCnt;

    // 사람 좌표
    static ArrayList<int[]> personLocation;

    // 각 계단으로 통하는 사람(걸리는 시간순으로 정렬할 거임)
    static List<Person> personList1;
    static List<Person> personList2;

    // 걸리는 최소 시간
    static int minTime;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();

        for (int tc = 0; tc < T; tc++) {
            System.out.print("#" + (tc + 1) + " ");
            // 초기화
            N = scan.nextInt();
            room = new int[N][N];
            personCnt = 0;
            minTime = Integer.MAX_VALUE;

            // 1은 사람, 2 이상은 계단 길이
            int stairturn = 1;
            personLocation = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    room[i][j] = scan.nextInt();

                    // 사람이면 사람 수랑 좌표 구하기
                    if (room[i][j] == 1) {
                        personCnt++;
                        personLocation.add(new int[]{i, j});

                        // 아니면 계단 좌표 구하기
                    } else if (room[i][j] >= 2) {
                        if (stairturn == 1) {
                            stair1 = new Stair(room[i][j], i, j);
                            stairturn++;
                        } else {
                            stair2 = new Stair(room[i][j], i, j);
                        }
                    }
                }
            }

            // 두 계단 대기 라인 만들기
            personList1 = new ArrayList<>();
            personList2 = new ArrayList<>();

            // Person 넣어주기
            for (int i = 0; i < personLocation.size(); i++) {
                int[] x = personLocation.get(i);
                int dis1 = Math.abs(stair1.y - x[0]) + Math.abs(stair1.x - x[1]);
                int dis2 = Math.abs(stair2.y - x[0]) + Math.abs(stair2.x - x[1]);
                Person p = new Person(dis1, dis2, x[0], x[1]);
                personList1.add(p);
                personList2.add(p);
            }

            // 탐색할 때 더 빠르게
            // 두 리스트를 계단에 가까운 순으로(거리가 같으면 다른 계단에서 먼 순으로) 정렬하기
            Collections.sort(personList1, new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    return
                            o1.distanceToStair1 == o2.distanceToStair1 ? o2.distanceToStair2 - o1.distanceToStair2 : o1.distanceToStair1 - o2.distanceToStair1;
                }
            });

            // 너도 정렬~
            Collections.sort(personList2, new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    return o1.distanceToStair2 == o2.distanceToStair2 ? o2.distanceToStair1 - o1.distanceToStair1 : o1.distanceToStair2 - o2.distanceToStair2;
                }
            });


            // 시간 구하러 가요
            countTime(0, 0, new ArrayList<Person>(), new ArrayList<Person>(), 0, 0);
            System.out.println(minTime);
        }
    }

    static void countTime(int idx1, int idx2, List<Person> list1, List<Person> list2, int time, int cnt) {
        // 인원 다 세면 종료
        if (cnt == personCnt) {
            // 걸리는 최소 시간 갱신
            minTime = Math.min(minTime, time);
            return;
        }

        // 크면 짤라주기
        if (time >= minTime) {
            return;
        }

        // 어느 계단에 보내야 좋을지 모르겠으니
        // 둘 다 보냄
        addPersonToList1(idx1, idx2, list1, list2, time, cnt);
        addPersonToList2(idx1, idx2, list1, list2, time, cnt);
    }

    static void addPersonToList1(int idx1, int idx2, List<Person> list1, List<Person> list2, int time, int cnt) {
        // 정렬된 거 앞에서부터 가져오기~~
        Person p = personList1.get(idx1);
        if (list2.contains(p)) {
            addPersonToList1(idx1 + 1, idx2, list1, list2, time, cnt);
            return;
        }

        // 탐색 끝나고 되돌려줘야 해~
        int temp = p.stair1Waiting;

        // 리스트가 3보다 크면~~ 웨이팅 해야해
        if (list1.size() >= 3) {
            // 대기 최대 3명이니까 나보다 세번째 앞에 애가 끝까지 갔나만 확인하면 됨
            Person before = list1.get(list1.size() - 3);
            p.stair1Waiting = (before.distanceToStair1 + before.stair1Waiting + stair1.length) - (p.distanceToStair1);
            // 대기 없음
            p.stair1Waiting = p.stair1Waiting <= 0 ? 0 : p.stair1Waiting;
        }

        list1.add(p);
        // 지금까지 밥 먹으러 간 시간 갱신
        int lastTakenTime = list1.isEmpty() ? 0 : list1.get(list1.size() - 1).distanceToStair1 + list1.get(list1.size() - 1).stair1Waiting + stair1.length;
        // 다음 사람 뽑기
        countTime(idx1 + 1, idx2, list1, list2, Math.max(time, lastTakenTime), cnt + 1);
        // 갔다 와서 원상복구
        p.stair1Waiting = temp;
        list1.remove(p);
    }

    static void addPersonToList2(int idx1, int idx2, List<Person> list1, List<Person> list2, int time, int cnt) {
        Person p = personList2.get(idx2);
        if (list1.contains(p)) {
            addPersonToList2(idx1, idx2 + 1, list1, list2, time, cnt);
            return;
        }

        int temp = p.stair2Waiting;

        if (list2.size() >= 3) {
            Person before = list2.get(list2.size() - 3);
            p.stair2Waiting = (before.distanceToStair2 + before.stair2Waiting + stair2.length) - (p.distanceToStair2);
            p.stair2Waiting = p.stair2Waiting <= 0 ? 0 : p.stair2Waiting;
        }

        list2.add(p);
        int lastTakenTime = list2.isEmpty() ? 0 : list2.get(list2.size() - 1).distanceToStair2 + list2.get(list2.size() - 1).stair2Waiting + stair2.length;
        countTime(idx1, idx2 + 1, list1, list2, Math.max(time, lastTakenTime), cnt + 1);
        p.stair2Waiting = temp;
        list2.remove(p);
    }
}