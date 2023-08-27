package dahye.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
막대기
 */
public class boj1094 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());

        int length = 0;
        int init = 64;
        int count = 0;

        while (length != X) {
            if(length + init > X) {
                init >>= 1; // 나누기 2
            } else {
                length += init;
                count++;
            }
        }

        System.out.println(count);
    }
}
