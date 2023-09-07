package dahye.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
LCS
 */
public class boj9251 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        String A = br.readLine();
        String B = br.readLine();

        int D[][] = new int[A.length() + 1][B.length() + 1];

        for(int i = 1; i < A.length() + 1; i++) {
            for(int j = 1; j < B.length() + 1; j++) {
                if(A.charAt(i - 1) == B.charAt(j - 1)) {
                    D[i][j] = D[i - 1][j - 1] + 1;
                } else {
                    D[i][j] = Math.max(D[i][j - 1], D[i - 1][j]);
                }
            }
        }

        System.out.println(D[A.length()][B.length()]);
    }
}
