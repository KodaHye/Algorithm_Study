package hyeongseok.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 특정한_최단_경로 {

    static int N, E, requirement1, requirement2;
    static int[] dist;
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

        dist = new int[N+1];

        for (int i = 0 ; i <= N ; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0 ; i < E ; i++) {
            st = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b, c));
            list.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(bf.readLine());

        requirement1 = Integer.parseInt(st.nextToken());
        requirement2 = Integer.parseInt(st.nextToken());

        Dijk(requirement1);
        Dijk(requirement2);

        System.out.println(Arrays.toString(dist));
//        System.out.println(dist[N]);
    }

    static void Dijk(int requirement) {
        Queue<Node> PQ = new PriorityQueue<>();

        PQ.add(new Node(1, 0));

        Arrays.fill(dist, 10000);
        boolean[] visited = new boolean[N+1];

        dist[1] = 0;

        int visitedIdx = 0;

        while (!PQ.isEmpty()) {
            Node n = PQ.poll();

            int now = n.idx;
            int distance = n.cost; // now 까지 오는데 지나온 거리


        }
    }
}
