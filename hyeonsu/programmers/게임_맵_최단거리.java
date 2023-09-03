package hyeonsu.programmers;

import java.util.*;

class 게임_맵_최단거리 {

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    int n, m;

    boolean[][] v;

    public int solution(int[][] maps) {

        //초기화
        n = maps.length;
        m = maps[0].length;
        v = new boolean[n][m];

        //로직
        int ans = bfs(maps);

        return ans;
    }

    public int bfs(int[][] maps) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0, 1));
        v[0][0] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            if (cur.x == n - 1 && cur.y == m - 1) {
                return cur.cnt;
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || v[nx][ny] || maps[nx][ny] == 0) continue;

                q.add(new Pair(nx, ny, cur.cnt + 1));
                v[nx][ny] = true;
            }
        }

        return -1;
    }

    class Pair {
        int x;
        int y;
        int cnt;

        Pair(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
