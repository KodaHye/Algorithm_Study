package solution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj21736 {
    static int N, M;
    static char[][] campus;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/input/boj21736.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        campus = new char[N][M];

        int r = 0, c = 0;
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for(int j = 0; j<M; j++){
                campus[i][j] = s.charAt(j);
                if(campus[i][j] == 'I'){
                    r = i;
                    c = j;
                }
            }
        }

        findPeople(r, c);


    }

    static class Point{
        int r, c;
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static void findPeople(int r, int c){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];
        int count = 0;

        queue.offer(new Point(r, c));
        visit[r][c] = true;

        while (!queue.isEmpty()){
            Point p = queue.poll();
            for(int d = 0; d<4; d++){
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M || visit[nr][nc] || campus[nr][nc] == 'X') continue;

                visit[nr][nc] = true;
                queue.offer(new Point(nr, nc));
                if(campus[nr][nc] == 'P') count++;
            }
        }

        if(count == 0) System.out.println("TT");
        else System.out.println(count);
    }
}
