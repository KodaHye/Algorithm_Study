package solution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj17144 {
    static int[][] room;
    static int R, C, T, sum;
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("boj/input/boj17144.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        room = new int[R][C];

        boolean findFirst = false;
        int r = 0, c = 0;

        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<C; j++){
                room[i][j] = Integer.parseInt(st.nextToken());
                if(!findFirst && room[i][j] == -1){
                    findFirst = true;
                    r = i;
                    c = j;
                }
            }
        }

        sum = 0;
        blowDirty(r, c, T);
    }

    static void blowDirty(int r, int c, int T){
        if(T == 0){
            getSum();
            System.out.println(sum);
            return;
        }

        spreadDirty();
        moveCounterclockwise(r, c);
        moveClockwise(r+1, c);

        blowDirty(r, c, T-1);
    }

    static void spreadDirty(){
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        int[][] cloneRoom = new int[R][C];

        for(int i = 0; i<R; i++){
            for(int j = 0; j<C; j++){
                if(room[i][j] > 0) {
                    int count = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if(nr < 0 || nr >= R || nc < 0 || nc>= C || room[nr][nc] == -1) continue;

                        cloneRoom[nr][nc] += room[i][j]/5;
                        count++;
                    }
                    cloneRoom[i][j] += room[i][j] - room[i][j] / 5 * count;
                }
            }
        }

        for(int i = 0; i<R; i++){
            for(int j = 0; j<C; j++){
                if(room[i][j] == -1) continue;
                room[i][j] = cloneRoom[i][j];
            }
        }
    }

    static void moveCounterclockwise(int r, int c){
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int cr = r + dr[0];
        int cc = c + dc[0];
        for(int d = 0; d<4; d++){
            while(true){
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if(nr < 0 || nr > r  || nc < 0 || nc >= C) break;

                if(nr == r && nc == c){
                    room[cr][cc] = 0;
                    return;
                }

                room[cr][cc] = room[nr][nc];
                cr = nr;
                cc = nc;
            }
        }
    }

    static void moveClockwise(int r, int c){
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};

        int cr = r + dr[0];
        int cc = c + dc[0];
        for(int d = 0; d<4; d++){
            while(true){
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if(nr < r || nr >= R || nc < 0 || nc >= C) break;

                if(nr == r && nc == c){
                    room[cr][cc] = 0;
                    return;
                }

                room[cr][cc] = room[nr][nc];
                cr = nr;
                cc = nc;
            }
        }
    }

    static void getSum(){
        for(int i = 0; i<R; i++){
            for(int j = 0; j<C; j++){
                if(room[i][j] <= 0) continue;
                sum += room[i][j];
            }
        }
    }
}
