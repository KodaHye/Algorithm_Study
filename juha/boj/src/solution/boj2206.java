package solution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj2206 {
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/input/boj2206.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for(int j = 0; j<M; j++){
                map[i][j] = s.charAt(j) - '0';
            }
        }

        findBfs();

    }

    static class Point{
        int r, c, count;
        boolean punch;

        Point(int r, int c, int count, boolean punch){
            this.r = r;
            this.c = c;
            this.count = count;
            this.punch = punch;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static void findBfs(){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];
        boolean[][] afterVisit = new boolean[N][M];

        visit[0][0] = true;
        queue.offer(new Point(0, 0, 1, false));

        while (!queue.isEmpty()){
            Point p = queue.poll();
            if(p.r == N-1 && p.c == M-1){
                System.out.println(p.count);
                return;
            }
            for(int d = 0; d<4; d++){
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if(!p.punch){
                    if(visit[nr][nc]) continue;

                    if(map[nr][nc] == 1){
                        afterVisit[nr][nc] = true;
                        queue.offer(new Point(nr, nc, p.count+1, true));
                    }else{
                        visit[nr][nc] = true;
                        queue.offer(new Point(nr, nc, p.count+1, false));
                    }
                } else{
                    if(afterVisit[nr][nc]) continue;

                    if(map[nr][nc] == 0){
                        afterVisit[nr][nc] = true;
                        queue.offer(new Point(nr, nc, p.count+1, true));
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
