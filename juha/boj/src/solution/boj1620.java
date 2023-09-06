package solution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj1620 {
    static int N, M;
    static Map<Integer, String> pocketmon;
    static Map<String, Integer> pocketmonNum;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/input/boj1620.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pocketmon = new HashMap<>();
        pocketmonNum = new HashMap<>();
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();
            pocketmon.put(i, temp);
            pocketmonNum.put(temp, i);
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();

            if(temp.matches("[+-]?\\d*(\\.\\d+)?")){
                int value = Integer.parseInt(temp);
                System.out.println(pocketmon.get(value-1));
            }else{
                System.out.println(pocketmonNum.get(temp)+1);
            }
        }
    }

}
