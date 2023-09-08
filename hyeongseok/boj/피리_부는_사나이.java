package hyeongseok.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 피리_부는_사나이 {

    static int N, M, answer;
    static int[][] map;
    static boolean[][] visited, cycle;
    static int[] nx = {0, 0, 1, -1};
    static int[] ny = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        cycle = new boolean[N][M];

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();

            for (int j = 0 ; j < M ; j++) {
                switch (s.charAt(j)) {
                    case 'U':
                        map[i][j] = 0;
                        break;
                    case 'D':
                        map[i][j] = 1;
                        break;
                    case 'R':
                        map[i][j] = 2;
                        break;
                    case 'L':
                        map[i][j] = 3;
                        break;
                }
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (!visited[i][j]) {
                    dfs(j, i);
                }
            }
        }

        System.out.println(answer);
    }

    /**
     * 내가 다음 이동할 위치가 사이클인지 판별
     * 사이클이 아니라면? answer++
     */
    static void dfs(int x, int y) {
        if (visited[y][x]) {
            return;
        }

        visited[y][x] = true;

        int dx = x + nx[map[y][x]];
        int dy = y + ny[map[y][x]];

        if (!visited[dy][dx]) {
            dfs(dx, dy);
        } else { // 다음 위치에 이미 방문한 경우,
            if (!cycle[dy][dx]) // 하지만 다음 위치가 사이클이 아니라면?
                answer++; // 사이클이 하나 생성되므로 answer++
        }

        // dfs 함수가 실행되는 동안 방문한 모든 위치는 하나의 사이클이므로 cycle 배열 방문처리
        cycle[y][x] = true;
    }
}
