package dahye.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1405 {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, per[], sel[];
    static int dr[] = {0, 0, 1, -1};
    static int dc[] = {1, -1, 0, 0};
    static double percent;
    static boolean[][] visit;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        per = new int[4];
        sel = new int[N];
        visit = new boolean[29][29];

        Point init = new Point(14, 14);

        visit[init.r][init.c] = true;

        for(int i = 0; i < 4; i++) {
            per[i] = Integer.parseInt(st.nextToken());
        }

        func(0, init, 1);

        System.out.println(percent);
    }

    static void func(int k, Point point, double result) {
        if(k == N) {

            percent += result;

            return;
        }


        for(int i = 0; i < 4; i++) {

            sel[k] = i;

            Point next = new Point(point.r + dr[i], point.c + dc[i]);

            if(visit[next.r][next.c] || per[sel[k]] == 0) continue;
            visit[next.r][next.c] = true;

            func(k+ 1, next, result * per[sel[k]] / 100.0);
            visit[next.r][next.c] = false;
        }
    }
}