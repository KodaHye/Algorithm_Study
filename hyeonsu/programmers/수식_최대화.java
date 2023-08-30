package hyeonsu.programmers;

import java.util.*;

class 수식_최대화 {

    static char[] exp, operators = {'+', '*', '-'};
    static Long max = Long.MIN_VALUE;
    static List<Long> l = new ArrayList<>();
    static List<Character> cl = new ArrayList<>();

    public long solution(String expression) {

        //1. 숫자와 연산자를 구분한다.
        exp = expression.toCharArray();
        Queue<Character> q = new LinkedList<>();

        for (int i = 0; i < exp.length; i++) {
            if (exp[i] == '+' || exp[i] == '*' || exp[i] == '-') {
                cl.add(exp[i]);
                StringBuilder sb = new StringBuilder();
                while (!q.isEmpty()) {
                    sb.append(q.poll());
                }
                l.add(Long.parseLong(sb.toString()));
            } else {
                q.offer(exp[i]);
            }
        }
        if (!q.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            while (!q.isEmpty()) {
                sb.append(q.poll());
            }
            l.add(Long.parseLong(sb.toString()));
        }

        //모든 연산자의 우선순위를 구하고 최댓값을 찾는다.
        perm(0);

        return max;
    }

    public void perm(int k) {

        if (k == 3) {
            Queue<Long> q = new ArrayDeque<>(l);
            Queue<Character> oq = new ArrayDeque<>(cl);
            //우선순위
            for (int i = 0; i < 3; i++) {
                int cnt = q.size();
                int qIdx = 0;
                if (cnt == 1) break;
                //피연산자를 순회하며 현재 우선순위의 연산인지 확인하고 연산
                long a = 0;
                long b = 0;
                while (qIdx < cnt) {
                    char operator = oq.poll();
                    if (qIdx == 0) {
                        a = q.poll();
                        b = q.poll();
                        qIdx += 2;
                    } else {
                        a = b;
                        b = q.poll();
                        qIdx++;
                    }
                    if (operators[i] == operator) {
                        long c = 0;
                        if (operator == '+') {
                            b = a + b;
                        } else if (operator == '*') {
                            b = a * b;
                        } else {
                            b = a - b;
                        }
                        if (qIdx >= cnt) {
                            q.add(b);
                        }
                    } else {
                        if (qIdx >= cnt) {
                            q.add(a);
                            q.add(b);
                        } else {
                            q.add(a);
                        }
                        oq.add(operator);
                    }
                }
            }

            max = Math.max(max, Math.abs(q.poll()));
            return;
        }

        for (int i = k; i < 3; i++) {
            swap(k, i);
            perm(k + 1);
            swap(k, i);
        }
    }

    public void swap(int a, int b) {
        char tmp = operators[a];
        operators[a] = operators[b];
        operators[b] = tmp;
    }
}
