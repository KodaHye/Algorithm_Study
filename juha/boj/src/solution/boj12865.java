package solution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj12865 {
    static int N, K;
    static int[][] objects, findOpt;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("boj/input/boj12865.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        objects = new int[N+1][2];
        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<2; j++){
                objects[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findOpt = new int[N+1][K+1];
        makeOptDP();
    }

    static void makeOptDP(){
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=K; j++){
                if(j - objects[i][0] < 0){
                    findOpt[i][j] = findOpt[i-1][j];
                    continue;
                }

                findOpt[i][j] = Math.max(findOpt[i-1][j], objects[i][1] + findOpt[i-1][j - objects[i][0]]);
            }
        }

        System.out.println(findOpt[N][K]);
    }
}
