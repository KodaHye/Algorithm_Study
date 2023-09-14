package hyeongseok.programmers;

public class 정수_삼각형 {
    public static void main(String[] args) {
        solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}});
    }

    static public int solution(int[][] triangle) {
        int answer = 0;

        int[][] dp = new int[triangle.length+1][triangle.length+1];

        dp[0][0] = triangle[0][0];

        for (int i = 1 ; i < triangle.length ; i++) {
            // 왼쪽 라인
            dp[i][0] = dp[i - 1][0] + triangle[i][0];

            // 오른쪽 라인
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];

            // 빼고 다
            for (int j = 1 ; j < i ; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1] + triangle[i][j], dp[i - 1][j] + triangle[i][j]);
            }
        }

        for (int i = 0 ; i <= triangle.length ; i++) {
            answer = Math.max(answer, dp[triangle.length-1][i]);
        }

        return answer;
    }
}
