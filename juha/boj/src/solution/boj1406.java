package solution;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj1406 {
    static Stack<Character> rightCursor, leftCursor;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("boj/input/boj1406.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rightCursor = new Stack<>();
        leftCursor = new Stack<>();

        String s = st.nextToken();
        for(int i = 0; i<s.length(); i++){
            leftCursor.push(s.charAt(i));
//            answer.add(s.charAt(i));
        }

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for(int i = 0; i<n; i++){
            String temp = br.readLine();

            char order = temp.charAt(0);
            char input = '.';
            if(order == 'P') input = temp.charAt(2);

            makeEditor(order, input);
        }

        while(!leftCursor.isEmpty()){
            rightCursor.push(leftCursor.pop());
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(!rightCursor.isEmpty()){
            bw.write(rightCursor.pop());
        }
        bw.flush();
        bw.close();
    }

    static void makeEditor(char order, char input){
        switch (order){
            case 'L':
                if(!leftCursor.isEmpty()){
                    rightCursor.push(leftCursor.pop());
                }
                break;
            case 'D':
                if(!rightCursor.isEmpty()){
                    leftCursor.push(rightCursor.pop());
                }
                break;
            case 'B':
                if(!leftCursor.isEmpty()){
                    leftCursor.pop();
                }
                break;
            case 'P':
                leftCursor.push(input);
                break;
        }
    }
}
