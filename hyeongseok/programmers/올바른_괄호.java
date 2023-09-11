package hyeongseok.programmers;

import java.util.Stack;

public class 올바른_괄호 {
    public static void main(String[] args) throws Exception {
        solution("(()(");
    }

    static boolean solution(String s) {
        boolean answer = true;

        Stack<Character> open = new Stack<>();

        for (int i = 0 ; i < s.length() ; i++) {
            if (s.charAt(i) == '(') {
                open.add('(');
            } else {
                if (open.size() == 0) {
                    answer = false;
                    break;
                }
                open.pop();
            }
        }

        if (!open.isEmpty()) answer = false;


        return answer;
    }
}
