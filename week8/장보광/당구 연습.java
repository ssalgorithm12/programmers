import java.util.*;
class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        Arrays.fill(answer, Integer.MAX_VALUE);
        for(int i = 0; i < balls.length; i++){
            if(!(startX==balls[i][0] && startY <= balls[i][1]))
                answer[i] = Math.min(answer[i], cal(startX, balls[i][0], startY, balls[i][1], n));                        
            if(!(startX==balls[i][0]&&startY >= balls[i][1]))
                answer[i] = Math.min(answer[i], cal(startX, balls[i][0], startY, balls[i][1], 0)); 
            
            if(!(startX <= balls[i][0]&&startY == balls[i][1]))
                answer[i] = Math.min(answer[i], cal(startY, balls[i][1], startX, balls[i][0], m)); 
            
            if(!(startX >= balls[i][0] &&startY == balls[i][1]))
                answer[i] = Math.min(answer[i], cal(startY, balls[i][1], startX, balls[i][0], 0)); 
        }
        
        return answer;
    }
    
    int cal(int a1, int a2, int b1, int b2, int x){
        /*
            - 기능: 두 지점 사이의 거리를 계산하는 함수
            - 입력
                a1: 첫번째 공의 좌표값1
                a2: 두번째 공의 좌표값1
                b1: 첫번째 공의 좌표값2
                b2: 두번째 공의 좌표값2
                x: 벽면의 위치 
            - 출력: 거리(int)
        */
        return (a1 - a2)*(a1 - a2) + (2*x - b1 - b2)*(2*x - b1 - b2);
    }
}
