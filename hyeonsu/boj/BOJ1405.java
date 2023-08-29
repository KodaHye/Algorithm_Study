package hyeonsu.boj;

import java.util.*;
import java.io.*;

public class BOJ1405 {

    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {1, -1, 0, 0};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, complexPath, notComplexPath;
    static double[] percent = new double[4];
    static double ans;
    static boolean[][] v = new boolean[100][100];

    public static void main(String[] args) throws IOException {
        //입력
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        for (int i = 0; i < 4; i++) {
            percent[i] = Double.parseDouble(st.nextToken()) * 0.01;
        }

        //로직
        v[49][49] = true;
        dfs(49, 49, 0, 1);

        //출력
        System.out.printf("%.10f\n", ans);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void dfs(int x, int y, int depth, double mul) {

        if (depth == n) {
            ans += mul;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (percent[i] > 0 && !v[nx][ny]) {
                v[nx][ny] = true;
                dfs(nx, ny, depth + 1, mul * percent[i]);
                v[nx][ny] = false;
            }
        }
    }
}
