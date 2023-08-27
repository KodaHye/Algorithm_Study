package dahye.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
주사위
 */

public class boj10141 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, arr[], min1, min2, min3;
    static BigInteger result;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        arr= new int[6];

        st = new StringTokenizer(br.readLine());

        min1 = Integer.MAX_VALUE;
        min2 = Integer.MAX_VALUE;
        min3 = Integer.MAX_VALUE;

        for(int i = 0; i < 6; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            min1 = Math.min(arr[i], min1);
        }

        int seq2[][] = {
                {0, 1}, {0, 2}, {0, 3}, {0, 4},
                {1, 2}, {1, 3}, {1, 5}, {2, 4},
                {2, 5}, {3, 4}, {3, 5}, {4, 5}
        };

        int seq3[][] = {
                {0, 1, 3}, {0, 1, 2}, {1, 5, 3}, {1, 5, 2}, {4, 5, 3}, {4, 5, 2}, {4, 0, 3}, {4, 0, 2}
        };

        for(int i = 0; i < seq2.length; i++) {
            int tmp = 0;

            for(int j = 0; j < 2; j++) {
                tmp += arr[seq2[i][j]];
            }

            min2 = Math.min(min2, tmp);
        }

        for(int i = 0; i < seq3.length; i++) {
            int tmp = 0;

            for(int j = 0; j < 3; j++) {
                tmp += arr[seq3[i][j]];
            }

            min3 = Math.min(min3, tmp);
        }


        if(N == 1) {
            int tmp = 0;
            Arrays.sort(arr);

            for(int i = 0; i < 5; i++) tmp += arr[i];

            result = BigInteger.valueOf(tmp);
            System.out.println(result);
            return;
        }

        result = BigInteger.valueOf(min1).multiply(BigInteger.valueOf(N - 2)).multiply(BigInteger.valueOf(5 * N - 6))
                .add(BigInteger.valueOf(4 * min2).multiply(BigInteger.valueOf(2 * N - 3)))
                .add(BigInteger.valueOf(4 * min3));

        System.out.println(result);
    }
}
