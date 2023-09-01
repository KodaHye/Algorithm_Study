package solution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class boj1405 {
    static int N;
    static double[] percent;
    static double ans;
    static boolean[][] visit;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("boj/input/boj1405.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        percent = new double[4];
        for(int i = 0; i<4; i++){
            percent[i] = Integer.parseInt(st.nextToken())*0.01;
        }

        visit = new boolean[30][30];
        ans = 0;
        findPercent(14, 14, 0, 1);
        System.out.print(ans);
    }

    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    static void findPercent(int r, int c, int count, double answer){
        if(!visit[r][c]){
            if(count == N){
                ans += answer;
                return;
            }

            visit[r][c] = true;
            for(int d = 0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];

                findPercent(nr, nc, count + 1, answer * percent[d]);
            }
            visit[r][c] = false;

        }

    }

}
