package dahye.programmers;

import java.util.*;

class 수식최대화 {
    static StringTokenizer st;

    public static void main(String[] args) {
        System.out.println(solution("100-200*300-500+20"));;
    }

    public static long solution(String expression) {
        long answer = 0;

        char exp[][] = {{'+', '-', '*'}, {'+', '*', '-'},
                {'*', '-', '+'}, {'*', '+', '-'},
                {'-', '*', '+'}, {'-', '+', '*'}};

        ArrayList<String> operation = new ArrayList<>();

        String num = "";

        for(int i = 0; i < expression.length(); i++) {
            if(expression.charAt(i) == '-' || expression.charAt(i) == '+' || expression.charAt(i) == '*') {
                operation.add(num);
                operation.add(String.valueOf(expression.charAt(i)));
                num = "";
                continue;
            }

            num += expression.charAt(i);

            if(i == expression.length() - 1) operation.add(num);
        }

        for(int i = 0; i < exp.length; i++) {
            ArrayList<String> subList = new ArrayList<String>(operation);

            for(int k = 0; k < 3; k++) {
                for(int j = 0; j < subList.size(); j++) {
                    if(exp[i][k] == subList.get(j).charAt(0)) {
                        subList.set(j - 1, cal(subList.get(j - 1), subList.get(j), subList.get(j + 1)));
                        subList.remove(j);
                        subList.remove(j);
                        j--;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(Long.parseLong(subList.get(0))));
        }
        return answer;
    }

    private static String cal(String a, String op, String b) {
        Long num1 = Long.parseLong(a);
        Long num2 = Long.parseLong(b);

        if(op.equals("+")) return String.valueOf(num1 + num2);
        else if(op.equals("-")) return String.valueOf(num1 - num2);
        else return String.valueOf(num1 * num2);
    }
}