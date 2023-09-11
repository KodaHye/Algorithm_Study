package solution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1003 {
    static int T;
    static int[][] fibonacciDP;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/input/boj1003.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        fibonacciDP = new int[41][2];
        fibonacciDP[0][0] = 1;
        fibonacciDP[1][1] = 1;

        for(int i = 2; i<41; i++){
            fibonacciDP[i][0] = fibonacciDP[i-2][0] + fibonacciDP[i-1][0];
            fibonacciDP[i][1] = fibonacciDP[i-2][1] + fibonacciDP[i-1][1];
        }

        for(int i = 0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());

            System.out.println(fibonacciDP[value][0] + " " + fibonacciDP[value][1]);
        }
    }
}
