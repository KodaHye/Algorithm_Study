package solution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj20040 {
    static int N, M, ans;
    static int[] paraents;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("boj/input/boj20040.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paraents = new int[N];
        for(int i = 0; i<N; i++){
            paraents[i] = i;
        }

        ans = 0;
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(start > end) union(i, start, end);
            else union(i, end, start);
        }

        System.out.println(ans);
    }

    private static void union(int index, int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y){
            System.out.println(index+1);
            System.exit(0);
        };

        if(x <= y) paraents[y] = x;
        else paraents[x] = y;
        return;
    }

    private static int find(int x) {
        if(x == paraents[x]){
            return x;
        }else{
            return paraents[x] = find(paraents[x]);
        }
    }
}
