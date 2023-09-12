package solution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj11722 {
    static int N;
    static int[] nums;
    static int[] numsDP;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/input/boj11722.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nums = new int[N+1];
        numsDP = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        numsDP[1] = 1;
        int max = 1;
        for(int i = 2; i<=N; i++){
            numsDP[i] = 1;
            for(int j = 0; j<i; j++){
                if(nums[i] < nums[j] && numsDP[i] <= numsDP[j]){
                    numsDP[i] = numsDP[j] + 1;
                } else if(nums[i] == nums[j]){
                    numsDP[i] = numsDP[j];
                }
            }
            max = Math.max(max, numsDP[i]);
        }

        System.out.println(max);

    }
}
