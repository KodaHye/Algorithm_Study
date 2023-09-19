package hyeongseok.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 특정한_최단_경로 {

	static int N, E;
	static List<List<Node>> list;

	static class Node implements Comparable<Node> {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost - n.cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(bf.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			list.get(a).add(new Node(b, c));
			list.get(b).add(new Node(a, c));
		}

		st = new StringTokenizer(bf.readLine());

		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		boolean distancePossible_1 = true;

		boolean distancePossible_2 = true;

		int distance_1 = Dijk(1, v1);
		int distance_2 = Dijk(v1, v2);
		int distance_3 = Dijk(v2, N);

		if (distance_1 == Integer.MAX_VALUE || distance_2 == Integer.MAX_VALUE || distance_3 == Integer.MAX_VALUE) {
			distancePossible_1 = false;
		}

		int distance_4 = Dijk(1, v2);
		int distance_5 = Dijk(v2, v1);
		int distance_6 = Dijk(v1, N);

		if (distance_4 == Integer.MAX_VALUE || distance_5 == Integer.MAX_VALUE || distance_6 == Integer.MAX_VALUE) {
			distancePossible_2 = false;
		}

		int answer = 0;

		if (!distancePossible_1 && !distancePossible_2) {
			answer = -1;
		} else if (!distancePossible_1) {
			answer = distance_4 + distance_5 + distance_6;
		} else if (!distancePossible_2) {
			answer = distance_1 + distance_2 + distance_3;
		} else {
			answer = Math.min(distance_1 + distance_2 + distance_3, distance_4 + distance_5 + distance_6);
		}

		System.out.println(answer);
	}

	static int Dijk(int start, int end) {
		Queue<Node> PQ = new PriorityQueue<>();

		PQ.add(new Node(start, 0));

        int[] dist = new int[N + 1];

		Arrays.fill(dist, Integer.MAX_VALUE);

		boolean[] visited = new boolean[N + 1];

		dist[start] = 0;

		while (!PQ.isEmpty()) {
			Node n = PQ.poll();

			int now = n.idx;
			int distance = n.cost; // now 까지 오는데 지나온 거리

            if (visited[now]) continue;
            visited[now] = true;

            for (Node nextNode : list.get(now)) {
                if (!visited[nextNode.idx] && dist[nextNode.idx] > dist[now] + nextNode.cost) {
					dist[nextNode.idx] = dist[now] + nextNode.cost;
					PQ.add(new Node(nextNode.idx, dist[nextNode.idx]));
				}
            }
		}

		return dist[end];
	}
}
