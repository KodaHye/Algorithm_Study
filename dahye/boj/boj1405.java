package dahye.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1405 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, per[];
    static int dr[] = {0, 0, 1, -1};
    static int dc[] = {1, -1, 0, 0};
    static double percent;
    static boolean[][] visit;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        per = new int[4];
        visit = new boolean[29][29];

        visit[14][14] = true;

        for(int i = 0; i < 4; i++) {
            per[i] = Integer.parseInt(st.nextToken());
        }

        func(0, 14, 14, 1);

        System.out.println(percent);
    }

    static void func(int k, int r, int c, double result) {
        if(k == N) {

            percent += result;

            return;
        }


        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(visit[nr][nc] || per[i] == 0) continue;
            visit[nr][nc] = true;

            func(k+ 1, nr, nc, result * per[i] / 100.0);
            visit[nr][nc] = false;
        }
    }
}