package hyeonsu.boj;

import java.util.*;
import java.io.*;

public class BOJ18430 {

    static final int[][] dx = {{0, 1}, {-1, 0}, {-1, 0}, {0, 1}};
    static final int[][] dy = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int ans, n, m;
    static int[][] b;
    static boolean[][] c;

    public static void main(String[] args) throws IOException {
        //초기화
        ans = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        b = new int[n][m];
        c = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                b[i][j] = stoi(st.nextToken());
            }
        }

        //로직
        dfs(0, 0, 0);

        //출력
        System.out.println(ans);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void dfs(int x, int y, int sum) {

        if (x == n) {
            ans = Math.max(ans, sum);
            return;
        }

        //모든 좌표에서 조합할 수 있는 모든 부메랑을 조합한다.
        if (!c[x][y]) {
            for (int k = 0; k < 4; k++) {
                int firstX = x + dx[k][0];
                int firstY = y + dy[k][0];
                int secondX = x + dx[k][1];
                int secondY = y + dy[k][1];

                if (isValid(firstX, firstY) && isValid(secondX, secondY)) {
                    c[x][y] = true;
                    c[firstX][firstY] = true;
                    c[secondX][secondY] = true;
                    dfs(y == m - 1 ? x + 1 : x, y == m - 1 ? 0 : y + 1, sum + (b[x][y] * 2 + b[firstX][firstY] + b[secondX][secondY]));
                    c[x][y] = false;
                    c[firstX][firstY] = false;
                    c[secondX][secondY] = false;
                }
            }
        }

        dfs(y == m - 1 ? x + 1 : x, y == m - 1 ? 0 : y + 1, sum);
    }

    static boolean isValid(int x, int y) {

        return x >= 0 && x < n && y >= 0 && y < m && !c[x][y];
    }
}
