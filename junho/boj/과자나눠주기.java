package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 과자나눠주기 {

    static int n,m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int left = 1;
        int right = arr[n - 1];
        int max = Integer.MIN_VALUE;
        while (left <= right) {
            int mid = (left+right)/2;
            int cnt=0;

            for (int i = 0; i <n ; i++) {
                cnt += arr[i] / mid;
            }
            //과제를 나눠줄수 있다
            if (cnt >= m) {
                max = Math.max(max, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(max==Integer.MIN_VALUE?0:max);

        }

}
