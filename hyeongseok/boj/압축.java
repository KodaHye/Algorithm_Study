package hyeongseok.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class 압축 {

    static int[] parents = new int[50]; // 여는 괄호의 인덱스, 닫는 괄호의 인덱스
    static char[] input;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        input = bf.readLine().toCharArray();

        Stack<Integer> stack = new Stack<>();

        for (int i = 0 ; i < input.length ; i++) {
            if (input[i] == '(') { // 여는 괄호일 때
                stack.add(i);
            } else if (input[i] == ')') { // 닫는 괄호일 때
                // stack.pop() = 여는 괄호 인덱스, i = 닫는 괄호 인덱스
                parents[stack.pop()] = i;
            }
        }

        System.out.println(solve(0, input.length));
    }

    /**
     *
     * @param open : 여는 괄호의 인덱스
     * @param close : 닫는 괄호의 인덱스
     * @return
     */
    static int solve(int open, int close) {
        // open과 close 사이에 있는 수의 길이
        int len = 0;

        for (int i = open ; i < close ; i++) {
            if (input[i] == '(') { // 여는 괄호를 발견했으면 여는 괄호의 쌍인 닫는 괄호 안에 있는 모든 괄호들을 해결해야 함
                len += (input[i-1] - '0') * solve(i + 1, parents[i]) - 1; // 여는 괄호 바로 이전의 수 * 그 안에 있는 수의 길이
                i = parents[i];
            } else {
                len++;
            }
        }

        return len;
    }
}