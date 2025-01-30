import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        
        int num = balls.length;
        int[] answer = new int[num];
        for(int i = 0; i < num; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];
            
            int min = Integer.MAX_VALUE;

            // 튕기기 전에 부딪히는 경우 제거하기 위해 경우 나눠서 생각
            // 일직선으로 좌표 바꿔서 길이 구하기
            
			// 좌
			if (!(startY == targetY && startX >= targetX)) {
				int len = getDistance(startX, startY, targetX * (-1), targetY);
				min = Math.min(min, len);
			}

			// 우
			if (!(startY == targetY && startX <= targetX)) {
				int len = getDistance(startX, startY, m + (m - targetX), targetY);
				min = Math.min(min, len);
			}

			// 상
			if (!(startX == targetX && startY <= targetY)) {
				int len = getDistance(startX, startY, targetX, n + (n - targetY));
				min = Math.min(min, len);
			}

			// 하
			if (!(startX == targetX && startY >= targetY)) {
				int len = getDistance(startX, startY, targetX, targetY * (-1));
				min = Math.min(min, len);
			}
            answer[i] = min;
        }
        
        return answer;
    }
    
    
    int getDistance(int sx, int sy, int tx, int ty) {
		return (int) (Math.pow(sx - tx, 2) + Math.pow(sy - ty, 2));
	}
}