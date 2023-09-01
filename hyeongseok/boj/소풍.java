package hyeongseok.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 소풍 {

	static int K, N, F;
	static List<List<Integer>> list;
	static boolean possibleFriends;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();

		for (int i = 0; i < N + 1; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < F; i++) {
			st = new StringTokenizer(bf.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			list.get(from).add(to);
			list.get(to).add(from);
		}

		for (int i = 0; i < N + 1; i++) {
			Collections.sort(list.get(i));
		}

		possibleFriends = false;

		for (int i = 1; i < N + 1; i++) {
			if (list.get(i).size() >= K - 1) {
				int[] input = new int[K];
				input[0] = i;
				boolean[] visited = new boolean[N + 1];
				visited[i] = true;
				dfs(i, 1, input, visited);
			}
		}

		if (!possibleFriends) {
			System.out.println(-1);
		}
	}

	static void dfs(int num, int idx, int[] input, boolean[] visited) {
		if (possibleFriends)
			return;

		if (idx == K) {
			possibleFriends = true;
			Arrays.sort(input);
			for (int i = 0; i < K; i++) {
				System.out.println(input[i]);
			}
			return;
		}

		for (int i = 0; i < list.get(num).size(); i++) {
			int to = list.get(num).get(i);
			if (!visited[to] && checkFriends(input, idx, to)) {
				visited[to] = true;
				input[idx] = to;
				dfs(to, idx + 1, input, visited);
				visited[to] = false;
			}
		}
	}

	static boolean checkFriends(int[] input, int idx, int num) {
		for (int i = 0; i < idx; i++) {
			if (!list.get(num).contains(input[i]))
				return false;
		}

		return true;
	}
}