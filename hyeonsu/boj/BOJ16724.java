package hyeonsu.boj;

import java.util.*;
import java.io.*;

public class BOJ16724 {

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m, lavel, ans;
    static int[][] map, lavels;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {

        //초기화
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        lavels = new int[n][m];
        v = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                char dir = str.charAt(j);
                if (dir == 'U') map[i][j] = 0;
                if (dir == 'D') map[i][j] = 1;
                if (dir == 'L') map[i][j] = 2;
                if (dir == 'R') map[i][j] = 3;
            }
        }

        //로직
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!v[i][j]) {
                    dfs(i, j);
                    lavel++;
                }
            }
        }

        //출력
        System.out.println(ans);
    }

    static void dfs(int x, int y) {
        v[x][y] = true;
        lavels[x][y] = lavel;

        //다음 좌표 위치
        int nx = x + dx[map[x][y]];
        int ny = y + dy[map[x][y]];

        if (!v[nx][ny]) {
            dfs(nx, ny);
        } else {
            //방문 했는데 같은 라벨링 번호라는 것은 현재 만들어진 싸이클 이라는 뜻이므로 세이프티존을 한 개 추가한다.
            if (lavels[nx][ny] == lavel) {
                ans++;
            }
        }
    }
}
