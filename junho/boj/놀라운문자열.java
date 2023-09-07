import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class 놀라운문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = br.readLine();
            if(input.equals("*")) break;

            int d = 1;
            boolean flag = false;
            loop:
            for (int i = 0; i < input.length()-1; i++) {
                Map<String, Integer> map = new HashMap<>();
                for (int j = 0; j <input.length()-i-1 ; j++) {

                    String data = Character.toString(input.charAt(j))+Character.toString(input.charAt(j+d));
                    if (map.containsKey(data)) {
                        flag= true;
                        break loop;
                    }
//                    System.out.println("data = " + data);
                    map.put(data, 1);
                }

                d++;

            }

            if (!flag) {
                System.out.println(input+" is surprising.");
            }else{
                System.out.println(input+" is NOT surprising.");
            }
        }
    }
}
