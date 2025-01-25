package programmers;

public class 당구연습 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	static int x, y;
	
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
    	
    	int length = balls.length;
    	
    	int[] answer = new int[length];
    	
    	x = startX;
    	y = startY;
    	
    	for(int i=0; i<length; i++) {
    	
    	answer[i] = findMinWay(balls[i], m, n);	
    		
    	}
    	
        return answer;
    }
    
    // 4방을 돌려서 최단 거리 탐색
    static int findMinWay(int[] way, int m, int n) {
    	
    	int cx = way[0];
    	int cy = way[1];
    	
    	int[] way_1 = {cx, 2 * n - cy};
    	int[] way_2 = {2 * m - cx, cy};
    	int[] way_3 = {cx, -cy};
    	int[] way_4 = {-cx, cy};
    
    	int min_dist = Integer.MAX_VALUE - 1;
    	
      // 각 위치 별로 최단 거리 찾기
    	if(x != way_1[0] && y != way_1[1] && Math.pow(Math.abs(x - way_1[0]), 2) + Math.pow(Math.abs(y - way_1[1]), 2) < min_dist) {
    		min_dist = (int) (Math.pow(Math.abs(x - way_1[0]), 2) + Math.pow(Math.abs(y - way_1[1]), 2));
    	}
    	
    	if(x != way_2[0] && y != way_2[1] && Math.pow(Math.abs(x - way_2[0]), 2) + Math.pow(Math.abs(y - way_2[1]), 2) < min_dist) {
    		min_dist = (int) (Math.pow(Math.abs(x - way_2[0]), 2) + Math.pow(Math.abs(y - way_2[1]), 2));
    	}
    	
    	if(x != way_3[0] && y != way_3[1] && Math.pow(Math.abs(x - way_3[0]), 2) + Math.pow(Math.abs(y - way_3[1]), 2) < min_dist) {
    		min_dist = (int) (Math.pow(Math.abs(x - way_3[0]), 2) + Math.pow(Math.abs(y - way_3[1]), 2));
    	}
    	
    	if(x != way_4[0] && y != way_4[1] && Math.pow(Math.abs(x - way_4[0]), 2) + Math.pow(Math.abs(y - way_4[1]), 2) < min_dist) {
    		min_dist = (int) (Math.pow(Math.abs(x - way_4[0]), 2) + Math.pow(Math.abs(y - way_4[1]), 2));
    	}
    	
    	return min_dist;
    }

}
