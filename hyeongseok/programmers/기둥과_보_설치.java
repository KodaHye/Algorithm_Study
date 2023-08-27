package hyeongseok.programmers;

public class 기둥과_보_설치 {

	public int N;
	public int[][][] map;

	public int[][] solution(int n, int[][] build_frame) {
		int result = 0;

		N = n;

		map = new int[N+1][N+1][2];

		for (int i = 0; i < build_frame.length; i++) {
			int x = build_frame[i][0];
			int y = build_frame[i][1];
			int a = build_frame[i][2];
			int b = build_frame[i][3];

			if (b == 1) { // 설치
				if (checkBuildPossible(x, y, a)) {
					map[x][y][a] = 1;
					result++;
				}
			} else {
				boolean check = false;

				// 구조물 삭제
				map[x][y][a] = 0;

				if (a == 0) { // 기둥을 삭제한 뒤, 주변의 기둥이나 보에 영향을 끼치는지 확인
					// 없어진 기둥 위에 양 옆에 보가 있는지 확인
					if (x-1 >= 0 && map[x-1][y+1][1] == 1 && !checkBuildPossible(x-1, y+1, 1)) {
						check = true;
					} else if (y+2 <= N && map[x][y+1][0] == 1 && !checkBuildPossible(x, y+1, 0)) { // 바로 위에 기둥이 있는지 확인
						check = true;
					} else if (x+1 <= N && map[x][y+1][1] == 1 && !checkBuildPossible(x, y+1, 1)) {
						check = true;
					}
				} else {
					if (x-1 >= 0 && map[x-1][y][1] == 1 && !checkBuildPossible(x-1, y, 1)) { // 왼쪽에 보가 있으면 설치가능한 상태인지 체크
						check = true;
					} else if (x+2 <= N && map[x+1][y][1] == 1 && !checkBuildPossible(x+1, y, 1)) { // 오른쪽에 보가 있으면 설치가능한 상태인지 체크
						check = true;
					} else if (y+1 <= N && map[x][y][0] == 1 && !checkBuildPossible(x, y, 0)) { // 바로 밑에 기둥이 있다면 설치 가능한 상태인지 체크
						check = true;
					} else if (y+1 <= N && map[x+1][y][0] == 1 && !checkBuildPossible(x+1, y, 0)) { // 바로 위에 기둥이 있다면 설치 가능한 상태인지 체크
						check = true;
					}
				}

				if (check) { // 제거하면 안되는 구조물이었음 -> 복원
					map[x][y][a] = 1;
				} else { // 제거 가능한 상태
					result--;
				}
			}
		}

		int[][] answer = new int[result][3];

		int idx = 0;

		for (int i = 0 ; i <= N ; i++) {
			for (int j = 0 ; j <= N ; j++) {
				for (int q = 0 ; q < 2 ; q++) {
					if (map[i][j][q] == 1) {
						answer[idx][0] = i;
						answer[idx][1] = j;
						answer[idx][2] = q;
						idx++;
					}
				}
			}
		}

		return answer;
	}

	public boolean checkBuildPossible(int x, int y, int a) {
		boolean check = false;

		//기둥 설치
		if (a == 0) {
			// 바닥에 설치하는 경우
			if (y == 0) check = true;
			// 보의 한쪽 끝에 설치하는 경우
			if (x-1 >= 0 && map[x-1][y][1] == 1) check = true;
			if (x+1 <= N && map[x][y][1] == 1) check = true;
			// 다른 기둥 위에 설치하는 경우
			if (y-1 >= 0 && map[x][y-1][0] == 1) check = true;
		}
		// 보 설치
		else {
			// 한쪽 끝이 다른 기둥 위
			if (y-1 >= 0 && map[x][y-1][0] == 1) check = true;
			if (y-1 >= 0 && map[x+1][y-1][0] == 1) check = true;
			// 양쪽 끝이 다른 보
			if (x-1 >= 0 && x+2 <= N && map[x-1][y][1] == 1 && map[x+1][y][1] == 1) check = true;
		}

		return check;
	}
}