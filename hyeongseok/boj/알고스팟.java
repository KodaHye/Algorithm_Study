package hyeongseok.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 알고스팟 {

	static int N, M;
	static int[][] map, dist;

	static int[] ny = {-1, 0, 1, 0};
	static int[] nx = {0, 1, 0, -1};

	static class Node implements Comparable<Node>{
		int x, y, broken;

		public Node(int x, int y, int broken) {
			this.x = x;
			this.y = y;
			this.broken = broken;
		}

		@Override
		public int compareTo(Node n) {
			return this.broken - n.broken;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dist = new int[N][M];

		for (int i = 0 ; i < N ; i++) {
			String s = bf.readLine();

			Arrays.fill(dist[i], Integer.MAX_VALUE);

			for (int j = 0 ; j < M ; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		Dijk();

		System.out.println(dist[N-1][M-1]);
	}

	static void Dijk() {
		Queue<Node> PQ = new PriorityQueue<>();

		boolean[][] visited = new boolean[N][M];

		PQ.add(new Node(0, 0, 0));

		dist[0][0] = 0;
		visited[0][0] = true;

		while (!PQ.isEmpty()) {
			Node n = PQ.poll();

			int x = n.x;
			int y = n.y;
			int broken = n.broken;

			if (y == N-1 && x == M-1) break;

			if (visited[y][x]) continue;
			visited[y][x] = true;

			for (int i = 0 ; i < 4 ; i++) {
				int dx = x + nx[i];
				int dy = y + ny[i];

				if (dx >= 0 && dx < M && dy >= 0 && dy < N) {
					if (map[dy][dx] == 0 && dist[dy][dx] > dist[y][x]) {
						dist[dy][dx] = dist[y][x];
						PQ.add(new Node(dx, dy, broken));
					} else {
						if (dist[dy][dx] > dist[y][x] + 1) {
							dist[dy][dx] = broken + 1;
							PQ.add(new Node(dx, dy, broken + 1));
						}
					}
				}
			}
		}
	}
}
