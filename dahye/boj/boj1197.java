package dahye.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
최소 스패닝 트리
 */
public class boj1197 {
    static class Edge implements Comparable<Edge> {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.w, edge.w);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int V, E, result, p[];

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        p = new int[V + 1];

        for(int i = 0; i < p.length; i++) p[i] = i;

        PriorityQueue<Edge> queue = new PriorityQueue<>();

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            queue.add(new Edge(s, e, w));
        }

        int i = E;
        while(i-- > 0) {
            Edge current = queue.poll();

            if(find(current.s) != find(current.e)) {
                union(current.s, current.e);
                result += current.w;
            }
        }

        System.out.println(result);
    }

    private static void union(int a, int b) {
        a = p[a];
        b = p[b];

        if(a != b) p[b] = a;
    }

    private static int find(int a) {
        return p[a] = p[a] == a ? a : find(p[a]);
    }
    
    
}
