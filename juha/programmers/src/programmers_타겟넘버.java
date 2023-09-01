import java.util.*;
import java.io.*;

class Solution {
    static int tar, ansCnt;
    static int[] arr;
    public int solution(int[] numbers, int target) {
        
        tar = target;
        arr = numbers;
        
        findWay(0, 0);
        return ansCnt;
    }
    
    static void findWay(int cnt, int sum){
        if(cnt == arr.length){
            if(sum == tar){
                ansCnt++;
            }
            return;
        }
        
        findWay(cnt+1, sum + arr[cnt]);
        findWay(cnt+1, sum - arr[cnt]);
    }
}
