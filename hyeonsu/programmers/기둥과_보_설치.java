package hyeonsu.programmers;

import java.util.*;

/*
문제의 좌표 구조와 2차원 배열의 좌표 구조는 다르니까 적절히 계산하면서 사용.
*/

class 기둥과_보_설치 {

    static int[][] map, ans;

    static final int X = 0;
    static final int Y = 1;
    static final int A = 2;
    static final int B = 3;
    static final int EMPTY = 0;
    static final int PILLAR = 1;
    static final int BEAM = 2;
    static final int DOUBLE = 3;

    static int n, cnt;


    public int[][] solution(int n, int[][] buildFrame) {
        //초기화
        map = new int[n + 1][n + 1];
        ans = new int[buildFrame.length][3];
        this.n = n;

        //로직
        for (int i = 0; i < buildFrame.length; i++) {
            int x = buildFrame[i][X];
            int y = buildFrame[i][Y];
            int a = buildFrame[i][A];
            int b = buildFrame[i][B];

            //설치 명령
            if (b == 1) {
                //기둥일 때 설치 검증
                if (a == 0) {
                    if (isValid(x, y, a, b)) {
                        if (map[x][y] == BEAM) {
                            map[x][y] = DOUBLE;
                        } else {
                            map[x][y] = PILLAR;
                        }
                        cnt++;
                    }
                }

                //보일 때 설치 검증
                if (a == 1) {
                    if (isValid(x, y, a, b)) {
                        if (map[x][y] == PILLAR) {
                            map[x][y] = DOUBLE;
                        } else {
                            map[x][y] = BEAM;
                        }
                        cnt++;
                    }
                }
            }
            //삭제 명령
            else {
                //기둥일 때 삭제 검증
                if (a == 0) {
                    int tmp = PILLAR;
                    map[x][y] = map[x][y] == DOUBLE ? BEAM : EMPTY;
                    if (!isValid(x - 1, y + 1, a, b) || !isValid(x, y + 1, a, b)) {
                        map[x][y] = map[x][y] == BEAM ? DOUBLE : PILLAR;
                    } else {
                        cnt--;
                    }
                }

                //보일 때 삭제 검증
                if (a == 1) {
                    int tmp = BEAM;
                    map[x][y] = map[x][y] == DOUBLE ? PILLAR : EMPTY;
                    if (!isValid(x - 1, y, a, b) || !isValid(x + 1, y, a, b)) {
                        map[x][y] = map[x][y] == PILLAR ? DOUBLE : BEAM;
                    } else {
                        cnt--;
                    }
                }
            }
        }

        //답 추출
        ans = new int[cnt][3];
        int idx = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (map[i][j] != EMPTY) {
                    ans[idx][0] = i;
                    ans[idx][1] = j;
                    if (map[i][j] == DOUBLE) {
                        ans[idx][2] = 0;
                        map[i][j] = BEAM;
                        --i;
                    }
                    idx++;
                }
            }
        }


        //답 정렬
        Arrays.sort(ans, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                if (o1[1] == o2[1]) {
                    return o1[2] - o2[2];
                }
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        return ans;
    }

    /*
    인자로 받은 좌표에 놓을 곳이 현재 유효한 상태인지를 판단한다.
   */
    public boolean isValid(int x, int y, int a, int b) {
        if (a == 0) {
            //바닥이면 설치
            if (y == 0) {
                return true;
            }
            //아래에 기둥이 있을 경우 true
            else if (map[x][y - 1] == PILLAR) {
                return true;
            }
            //교차지점에 보가 있을 경우 true
            else if (map[x][y] == BEAM) {
                return true;
            }
            //왼쪽 부분이 벗어나지 않을 때 보가 있을 경우
            else if (x - 1 >= 0) {
                if (map[x - 1][y] == BEAM) {
                    return true;
                }
            }
        } else {
            //왼쪽이든 오른쪽이든 기둥이 있으면 설치
            if ((y - 1 >= 0 && map[x][y - 1] == PILLAR) || (x + 1 <= n && y - 1 >= 0 && map[x + 1][y - 1] == PILLAR)) {
                return true;
            }
            //양쪽 모두 기둥이 없다면 넘기기
            else if ((y - 1 >= 0 && map[x][y - 1] != PILLAR) && (x + 1 <= n && y - 1 >= 0 && map[x + 1][y - 1] != PILLAR)) {
                return false;
            }
            //양쪽 모두 보가 존재한다면 설치
            else if ((x - 1 >= 0 && map[x - 1][y] == BEAM) && (x + 1 <= n && map[x + 1][y] == BEAM)) {
                return true;
            }
        }

        return false;
    }
}