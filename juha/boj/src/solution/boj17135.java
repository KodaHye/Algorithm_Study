package solution;

import java.io.*;
import java.util.*;

public class boj17135 {
    static int N, M, D, enemyNum, killCount;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/input/boj17135.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M];
        enemyNum = 0;
        for(int i = 0; i <N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) enemyNum++;
            }
        }

        killCount = 0;
        batchArcher(3, 0);
        System.out.println(killCount);
    }

    static void batchArcher(int archerCount, int index){
        if(archerCount == 0){
            simulateAttack();
            return;
        }

        if(index == M) return;

        map[N][index] = 2;
        batchArcher(archerCount-1, index+1);
        map[N][index] = 0;
        batchArcher(archerCount, index+1);
    }

    static class Point{
        int r, c, distance;

        Point(int r, int c, int distance){
            this.r = r;
            this.c = c;
            this.distance = distance;
        }
    }

    static int[] dr = {0, -1, 0};
    static int[] dc = {-1, 0, 1};
    static void simulateAttack(){
        int enemy = enemyNum;
        int[][] cloneMap = new int[N+1][M];
        Queue<Point> queue;
        boolean[][] killEnemy = new boolean[N+1][M];

        int count = 0;
        int index = 0;
        while(enemy > 0){
            for(int i = 0; i<N+1 - index; i++){
                for(int j = 0; j<M; j++){
                    if(index == 0) cloneMap[i][j] = map[i][j];

                    if(cloneMap[i][j] == 2){
                        queue = new LinkedList<>();
                        boolean[][] visit = new boolean[N+1][M];
                        queue.offer(new Point(i, j, 0));
                        visit[i][j] = true;

                        L: while(!queue.isEmpty()){
                            Point p = queue.poll();
                            for(int d = 0; d<3; d++){
                                int nr = p.r + dr[d];
                                int nc = p.c + dc[d];

                                if(nr < 0 || nr >= N || nc < 0 || nc >= M || visit[nr][nc] || p.distance+1 > D) continue;

                                visit[nr][nc] = true;
                                if(!killEnemy[nr][nc] && cloneMap[nr][nc] == 1){
                                    killEnemy[nr][nc] = true;
                                    count++;
                                    enemy--;
                                }
                                if(cloneMap[nr][nc] != 1) queue.offer(new Point(nr, nc, p.distance+1));
                                else break L;
                            }
                        }
                    }
                }
            }

            for(int i = 0; i<N-index; i++){
                for(int j = 0; j<M; j++){
                    if(killEnemy[i][j]){
                        cloneMap[i][j] = 0;
                    }
                }
            }

            index++;
            for(int j = 0; j<M; j++){
                if(N-index < 0) break;
                if(cloneMap[N-index][j] == 1) enemy--;
                cloneMap[N-index][j] = cloneMap[N-index+1][j];
            }
        }

        killCount = Math.max(killCount, count);
    }

}
