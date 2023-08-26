import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj14391 {
    static int N, M, map[][], result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        result = Integer.MIN_VALUE;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int r = 0; r < N; r++) {
            String input = br.readLine();
            for(int c = 0; c < M; c++) {
                map[r][c] = input.charAt(c) - '0';
            }
        }

        for(int s = 0; s < (1 << N * M); s++) {
            int sum = 0;
            for(int r = 0; r < N; r++) {
                int cur = 0;
                for(int c = 0; c < M; c++) {
                    int k = r * M + c;
                    if((s & (1 << k)) == 0) cur = cur * 10 + map[r][c];
                    else {
                        sum += cur;
                        cur = 0;
                    }
                }

                sum += cur;
            }

            for(int c = 0; c < M; c++) {
                int cur = 0;
                for(int r = 0; r < N; r++) {
                    int k = r * M + c;
                    if((s & (1 << k)) != 0) cur = cur * 10 + map[r][c];
                    else {
                        sum += cur;
                        cur = 0;
                    }
                }

                sum += cur;
            }

            result = Math.max(result, sum);
        }

        System.out.println(result);
    }
}