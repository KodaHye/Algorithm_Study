package dahye.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
특정한 최단 경로
 */

public class boj1504 {
    static int INF = 200000000;
    static class Node implements Comparable<Node> {
        int e, w;

        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(this.w, node.w);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, E, v1, v2, d1, d2, d3, result, result1, result2;
    static ArrayList<Node> adj[];

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];

        for (int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());

        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        d1 = dijkstra(1, v1);
        d2 = dijkstra(v1, v2);
        d3 = dijkstra(v2, N);

        result1 = d1 + d2 + d3;
        d1 = dijkstra(1, v2);
        d3 = dijkstra(v1, N);
        result2 = d1 + d2 + d3;

        result = (result1 >= INF && result2 >= INF) == true ? -1 : Math.min(result1, result2);
        System.out.println(result);
    }

    private static int dijkstra(int start, int end) {
        int d[] = new int[N + 1];
        boolean v[] = new boolean[N + 1];

        Arrays.fill(d, INF);

        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.add(new Node(start, 0));
        v[start] = true;
        d[start] = 0;

        while(!queue.isEmpty()) {
            Node current = queue.poll();

            for(Node next: adj[current.e]) {
                if(d[current.e] + next.w < d[next.e]) {
                    d[next.e] = d[current.e] + next.w;
                    v[next.e] = true;

                    queue.add(new Node(next.e, d[current.e] + next.w));
                }
            }
        }

        return d[end];
    }
}
