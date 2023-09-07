package dahye.programmers;

class 카펫 {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        int rec = brown + yellow;

        for(int x = rec; x >= 1; x--) {
            if(rec % x == 0) {
                int y = rec / x;

                if((x - 2) * (y - 2) == yellow && ((x * y) - (x - 2) * (y - 2)) == brown) {
                    answer[0] = x;
                    answer[1] = y;
                    break;
                }
            }
        }
        return answer;
    }
}