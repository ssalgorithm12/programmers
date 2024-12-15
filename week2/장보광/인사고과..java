import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = -2;
        int a = scores[0][0];
        int b = scores[0][1];
        
        Arrays.sort(scores, Comparator.comparingInt((int[] o) -> o[0]).reversed());
        List<int[]> arr = new ArrayList<>();

        // 정렬  
        int min = 0;
        int min2 = 0; 
        int pre = scores[0][0];
        for(int i = 0; i < scores.length; i++){
            if(scores[i][0] != pre) min = min2; 
            if(scores[i][1] < min) continue;
            if(a==scores[i][0] && b == scores[i][1]) answer = -1;
            if(min2 < scores[i][1]) min2 = scores[i][1];
            pre = scores[i][0];
            arr.add(new int[] {scores[i][0], scores[i][1], scores[i][0]+scores[i][1]});              
        }
        
        if(answer == -2) return -1;
        
        // 3번째 열(o[2])을 기준으로 내림차순 정렬
        arr.sort((o1, o2) -> Integer.compare(o2[2], o1[2]));
        int index = 1;
        for(int[] x: arr){
            if(x[2]==a+b){
              answer = index;
              break;
            }  
            index++;
        }
        
        return answer;
    }
}
