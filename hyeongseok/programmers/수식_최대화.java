package hyeongseok.programmers;

import java.util.ArrayList;
import java.util.List;

public class 수식_최대화 {

    static public List<Long> number = new ArrayList<>();
    static public List<Integer> recoverNumber = new ArrayList<>();
    static public List<Character> operator = new ArrayList<>();
    static public List<Character> recoverOperator = new ArrayList<>();
    static public char[] possibleOperator = {'+', '*', '-'};
    static public long answer;

    public static void main(String[] args) {
        solution("100-200*300-500+20");
    }


    // 100-200*300-500+20
    public static long solution(String expression) {
        answer = 0;

        String[] input_num = expression.split("-|\\*|\\+");

        for (int i = 0 ; i < input_num.length ; i++) {
            number.add(Long.parseLong(input_num[i]));
            recoverNumber.add(Integer.parseInt(input_num[i]));
        }

        for (int i = 0 ; i < expression.length() ; i++) {
            char c = expression.charAt(i);

            if (c == '+' || c == '-' || c == '*' || c == '/') {
                operator.add(c);
                recoverOperator.add(c);
            }
        }

        permutation(0, new char[3], new boolean[3]);

        return answer;
    }

    static public void permutation(int idx, char[] input, boolean[] check) {
        if (idx == 3) {
            findMax(input);
            return;
        }

        for (int i = 0 ; i < 3 ; i++) {
            if (!check[i]) {
                input[idx] = possibleOperator[i];
                check[i] = true;
                permutation(idx + 1, input, check);
                check[i] = false;
            }
        }
    }

    static public void findMax(char[] permutationOperator) {
        int idx = 0;

        while (idx != 3) {
            for (int i = 0; i < operator.size(); i++) {
                if (operator.get(i) == permutationOperator[idx]) {
                    long tmpResult = operatedCaculation(number.get(i), number.get(i + 1), operator.get(i));
                    number.set(i, tmpResult);
                    number.remove(i+1);
                    operator.remove(i);
                    i--;
                }
            }

            idx++;
        }

        answer = Math.max(answer, number.get(0) >= 0 ? number.get(0) : -1 * number.get(0));

        recoverList();
    }

    static public long operatedCaculation(long num1, long num2, char inputOperator) {
        long sum = 0;

        switch (inputOperator) {
            case '+':
                sum = num1 + num2;
                break;
            case '*':
                sum = num1 * num2;
                break;
            case '-':
                sum = num1 - num2;
                break;
        }

        return sum;
    }

    static public void recoverList() {
        number.remove(0);

        for (long l : recoverNumber) {
            number.add(l);
        }

        for (Character c : recoverOperator) {
            operator.add(c);
        }
    }
}
