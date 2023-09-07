import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//boj 6118
public class 숨바꼭질 {
    static class Point{
        int s,e;
        public Point(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
    static int n,m;
    static ArrayList<Integer> graph[];

    static int r1,r2,r3;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        bfs();

    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        boolean[] v= new boolean[n+1];
        q.add(new Point(1, 0));
        v[1] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            if (p.e > r2) {
                r1 = p.s;
                r2 = p.e;
                r3 = 1;
            } else if (p.e == r2) {
                r3++;
                r1 = Math.min(r1, p.s);
            }

            for (int x : graph[p.s]) {
                if(v[x]) continue;

                v[x] = true;
                q.add(new Point(x, p.e + 1));
            }
        }
        System.out.println(r1+" "+r2+" "+r3);

    }
}
