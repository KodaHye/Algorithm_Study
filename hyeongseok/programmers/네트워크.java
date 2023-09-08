package hyeongseok.programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 네트워크 {

	static public boolean[] visited;
	static int N;
	static int[][] C;

	public static void main(String[] args) {
		int[][] arr = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};

		// [1, 1, 0], [1, 1, 0], [0, 0, 1]
		solution(3, arr);
	}

	// 노드뭉치 갯수 찾기
	static public int solution(int n, int[][] computers) {
		int answer = 0;

		N = n;

		visited = new boolean[n];
		C = new int[n][n];

		for (int i = 0 ; i < N ; i++) {
			for (int j = 0 ; j < N ; j++) {
				C[i][j] = computers[i][j];
			}
		}

		for (int i = 0 ; i < n ; i++) {
			if (!visited[i]) {
				dfs(i);
				answer++;
			}
		}

		return answer;
	}

	static public void dfs(int i) {
		visited[i] = true;

		for (int j = 0; j < C.length; j++) {
			if (i != j && C[i][j] == 1 && !visited[j]) {
				dfs(j);
			}
		}
	}
}
