import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//boj 14940
public class 쉬운최단거리 {

    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
    static int n,m;
    static int[][] map;
    static int[][] dist;
    static boolean[][] v;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    bfs(i,j);
                    break;
                }

            }
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!v[i][j] && map[i][j] == 1) {
                    sb.append(-1).append(" ");
                } else {
                    sb.append(dist[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    private static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        v = new boolean[n][m];
        q.add(new Point(x, y));
        v[x][y]=true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if (0 <= nx && 0 <= ny && nx < n && ny < m && !v[nx][ny] && map[nx][ny] != 0) {
                    v[nx][ny] = true;
                    dist[nx][ny]=dist[p.x][p.y]+1;
                    q.add(new Point(nx, ny));

                }

            }


        }


    }
}