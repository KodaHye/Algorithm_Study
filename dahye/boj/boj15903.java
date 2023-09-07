package dahye.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
카드 합체 놀이
 */
public class boj15903 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long arr[] = new long[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        while(m-- > 0) {
            Arrays.sort(arr);

            long tmp = arr[0] + arr[1];
            arr[0] = tmp;
            arr[1] = tmp;
        }

        long sum = 0;
        for(long num: arr) {
            sum += num;
        }
        System.out.println(sum);
    }
}
