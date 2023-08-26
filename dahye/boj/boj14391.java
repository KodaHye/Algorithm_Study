import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj14391 {
    static int N, M, map[][], result;
    static boolean sel[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        result = Integer.MIN_VALUE;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        sel = new boolean[N * M];

        for(int r = 0; r < N; r++) {
            String input = br.readLine();
            for(int c = 0; c < M; c++) {
                map[r][c] = input.charAt(c) - '0';
            }
        }
        func(0);

        System.out.println(result);
    }

    private static void func(int k) {
        if(k == N * M) {

            int tmp = 0;
            String str = "";
            boolean check[] = new boolean[N * M];


            for(int i = 0; i < sel.length; i++) {
                if(!sel[i] && !check[i]) {

                    int count = 0;

                    while(count < M - (i % M) && !sel[i + count] && !sel[i + count]) {
                        check[i + count] = true;
                        str += map[(i + count) / M][(i + count) % M];
                        count++;
                    }
                    tmp += Integer.parseInt(str);
                }

                if(sel[i] && !check[i]) {
                    if(!str.equals("")) tmp += Integer.parseInt(str);
                    str = String.valueOf(map[i / M][i % M]);
                    check[i] = true;

                    int idx = i;
                    while((idx + M) < sel.length && !check[idx + M] && sel[idx + M]) {
                        idx += M;
                        str += map[idx / M][idx % M];
                        check[idx] = true;
                    }

                    tmp += Integer.parseInt(str);
                }

                str = "";
            }
            result = Math.max(result, tmp);

            return;
        }

        sel[k] = false;
        func(k + 1);

        sel[k] = true;
        func(k + 1);
    }
}