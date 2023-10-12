package dahye.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
최단경로
약 10트만에 성공,,, 그냥 다익스트라인데,,,
다시 공부할 것,,,
 */
public class boj1753 {
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
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int V, E, K, d[];
    static ArrayList<Node> adj[];
    static boolean v[];

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(br.readLine());

        d = new int[V + 1];
        adj = new ArrayList[V + 1];
        v = new boolean[V + 1];

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[s].add(new Node(e, w));
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        Arrays.fill(d, Integer.MAX_VALUE);
        d[K] = 0;

        queue.add(new Node(K, 0));

        while(!queue.isEmpty()) {
            Node current = queue.poll();

            for(Node next: adj[current.e]) {
                if(d[next.e] > current.w + next.w) {
                    d[next.e] = current.w + next.w;
                    queue.add(new Node(next.e, d[next.e]));
                }
            }
        }

        for(int i = 1; i < d.length; i++) {
            if(d[i] == Integer.MAX_VALUE) {
                sb.append("INF"+ "\n");
            } else {
                sb.append(d[i] + "\n");
            }
        }
        System.out.print(sb);
    }
}
