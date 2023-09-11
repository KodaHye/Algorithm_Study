package dahye.programmers;

import java.util.*;

class 올바른괄호 {
    static Stack<Character> stack = new Stack<>();

    boolean solution(String s) {

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') stack.push(s.charAt(i));
            if(s.charAt(i) == ')') {
                if(!stack.isEmpty()) stack.pop();
                else return false;
            }
        }

        return stack.isEmpty();
    }
}