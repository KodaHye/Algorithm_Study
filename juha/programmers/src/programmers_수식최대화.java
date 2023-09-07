
import java.util.*;

public class programmers_수식최대화 {
    static char[] operator = {'+', '-', '*'};
    static List<Character> ops, opsClone;
    static List<Long> numbers, numbersClone;
    static long answer;

    public long solution(String expression){
        answer = 0;
        String splitOp = expression.replaceAll("[0-9]", "");
        String[] nums = expression.split("\\+|-|\\*");

        ops = new LinkedList<>();
        opsClone = new LinkedList<>();
        for(int i = 0; i<splitOp.length(); i++){
            ops.add(splitOp.charAt(i));
            opsClone.add(splitOp.charAt(i));
        }

        numbers = new LinkedList<>();
        numbersClone = new LinkedList<>();
        for(int i = 0; i<nums.length; i++){
            numbers.add(Long.parseLong(nums[i]));
            numbersClone.add(Long.parseLong(nums[i]));
        }

        perm(new char[3], new boolean[3], 0);

        return answer;
    }

    static void perm(char[] save, boolean[] visit, int index){
        if(index == save.length){
            long a = Math.abs(calculate(save));
            answer = Math.max(a, answer);

            recover();
            return;
        }

        for(int i = 0; i<3; i++){
            if(!visit[i]){
                visit[i] = true;
                save[index] = operator[i];
                perm(save, visit, index+1);
                visit[i] = false;
            }
        }
    }

    static long calculate(char[] save){
        for(int i = 0; i<3; i++){
            int index = 0;
            while(index < ops.size()){
                if(ops.get(index) != save[i]){
                    index++;
                    continue;
                }

                long a = numbers.get(index);
                long b = numbers.get(index+1);

                numbers.remove(index);
                numbers.remove(index);

                long ans = 0;
                switch(ops.get(index)){
                    case '+':
                        ans = a + b;
                        break;
                    case '-':
                        ans = a - b;
                        break;
                    case '*':
                        ans = a * b;
                        break;
                }

                numbers.add(index, ans);
                ops.remove(index);
            }
        }

        return numbers.get(0);
    }

    static void recover(){
        ops = new LinkedList<>();
        numbers = new LinkedList<>();

        for(int i = 0; i<opsClone.size(); i++){
            ops.add(opsClone.get(i));
        }
        for(int i = 0; i<numbersClone.size(); i++){
            numbers.add(numbersClone.get(i));
        }
    }
}