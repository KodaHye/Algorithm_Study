package hyeongseok.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 미친_로봇 {

    static int N;
    static int[] percent = new int[4];
    static boolean[][] visited;
    static double answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[100][100];

        for (int i = 0; i < 4; i++) {
            percent[i] = Integer.parseInt(st.nextToken());
        }

        visited[50][50] = true;

        dfs(50, 50 , 0, 1.0);

        System.out.println(answer);
    }

    // {'E', 'W', 'S', 'N'};
    static int[] nx = {1, -1, 0, 0};
    static int[] ny = {0, 0, -1, 1};

    static public void dfs(int x, int y, int idx, double nowPercent) {
        if (idx == N) {
            answer += nowPercent;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int dx = x + nx[i];
            int dy = y + ny[i];
            if (!visited[dy][dx]) { // 같은곳을 한번 더 들리지 않은 단순한 움직임
                visited[dy][dx] = true;
                dfs(dx, dy, idx + 1, nowPercent * (percent[i] / 100.0));
                visited[dy][dx] = false;
            }
        }
    }
}
