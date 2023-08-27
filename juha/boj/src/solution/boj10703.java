package solution;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj10703 {
    static int R, S;
    static char[][] picture, answer;
    static boolean isStar;

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("boj/input/boj10703.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        picture = new char[R][S];
        answer = new char[R][S];

        isStar = false;
        int moveCount = Integer.MAX_VALUE;
        int[] maxStar = new int[S];
        int[] minGround = new int[S];

        Arrays.fill(maxStar, Integer.MIN_VALUE);
        Arrays.fill(minGround, Integer.MAX_VALUE);

        for(int  i = 0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for(int j = 0; j<S; j++){
                picture[i][j] = s.charAt(j);
                answer[i][j] = '.';
                if(picture[i][j] == 'X'){
                    isStar = true;
                    maxStar[j] = i;
                }else if(picture[i][j] == '#'){
//                    minGround[j] = Math.min(minGround[j], i);
                    if(maxStar[j] >= 0) moveCount = Math.min(moveCount, i-maxStar[j]-1);
                    answer[i][j] = '#';
                }

//                if(i == R-1){
////                    System.out.println(j + ": " + minGround[j] + ", " + maxStar[j]);
//                    moveCount = Math.min(moveCount, minGround[j] - maxStar[j]-1);
//                }
            }


        }
//        System.out.println(moveCount);
        makeAfterPicture(moveCount);
    }

    static void makeAfterPicture(int moveCount) throws IOException {
        if(isStar) {
            moveStar(moveCount);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0; i<R; i++){
            for(int j = 0; j<S; j++){
                bw.write(answer[i][j]);
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

//    static int findMinDis(){
//        int min = Integer.MAX_VALUE;
//
//        for(int i = 0; i<S; i++){
//            int count = 0;
//            for(int j = R-2; j>= 0; j--){
//                if(picture[j][i] == '.') count++;
//            }
//            min = Math.min(min, count);
//        }
//
//        return min;
//    }

    static void moveStar(int moveCount){
        for(int i = 0; i<S; i++){
            for(int j = 0; j<R; j++){
                if(picture[j][i] == '#') continue;
                else if(picture[j][i] == 'X'){
                    answer[j+moveCount][i] = 'X';
                }
            }
        }
    }
}
