package dahye.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/*
LCS 2
 */
public class boj9252 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        String a = br.readLine();
        String b = br.readLine();

        int dp[][] = new int[a.length() + 1][b.length() + 1];

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        int i = a.length();
        int j = b.length();

        Stack<Character> stack = new Stack<>();

        while(i != 0 && j != 0) {
            if(dp[i][j] == dp[i - 1][j]) i--;
            else if(dp[i][j] == dp[i][j - 1]) j--;
            else {
                stack.push(a.charAt(i - 1));
                i--; j--;
            }
        }

        sb.append(dp[a.length()][b.length()] + "\n");

        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }
}
