package hyeongseok.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 이분_그래프 {

	static int V, E;
	static List<List<Integer>> list;
	static int[] colors;
	static boolean answer;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int K = Integer.parseInt(bf.readLine());

		for (int i = 0 ; i < K ; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			colors = new int[V+1];
			list = new ArrayList<>();
			answer = true;

			for (int j = 0 ; j < V+1 ; j++) {
				list.add(new ArrayList<>());
			}

			for (int j = 0 ; j < E ; j++) {
				st = new StringTokenizer(bf.readLine());

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				list.get(from).add(to);
				list.get(to).add(from);
			}

			for (int j = 1 ; j < V+1 ; j++) {
				if (!answer) {
					break;
				}

				if (colors[j] == 0) {
					dfs(j, 1);
				}
			}

			sb.append(answer ? "YES" : "NO").append("\n");
		}

		System.out.println(sb);
	}

	// 연결되어 있는 정점끼리는 색이 반드시 달라야함
	static public void dfs(int from, int color) {
		colors[from] = color;


		for (int i = 0 ; i < list.get(from).size() ; i++) {
			int to = list.get(from).get(i);

			if (colors[to] == color) {
				answer = false;
				return;
			}

			if (colors[to] == 0) {
				dfs(to, color == 1 ? 2 : 1);
			}
		}
	}
}
