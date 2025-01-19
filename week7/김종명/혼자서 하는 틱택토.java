package programmers;

public class 혼자서하는틱택토 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 조건 처리를 세워가며 나올 수 없는 게임 종류들을 처리하는 방식으로 접근 중
	
	static char[][] map;
	
	public int solution(String[] board) {
		
		map = new char[3][3];
		
		int oCount = 0;
		int xCount = 0;
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				map[i][j] = board[i].charAt(j);
				
				if(map[i][j] == 'O') {
					oCount += 1;
				}else if(map[i][j] == 'X') {
					xCount += 1;
				}
			}
		}
		
		// X 개수가 O 개수보다 많으면 규칙위반
		if(xCount > oCount) {
			return 0;
		}
		
		// X 개수와 O 개수가 2개 이상 차이나면 규칙 위반
		if(Math.abs(xCount - oCount) > 1) {
			return 0;
		}
		
		// 만약 O가 1줄을 만들었을때
		if(checkMade('O')) {
			
			if(xCount >= oCount) {
				return 0;
			}
		
		}
		
		// 만약 X가 1줄을 만들었을때
		if(checkMade('X')) {
			
			if(oCount > xCount) {
				return 0;
			}
		}
		
        return 1;
    }
	
	static boolean checkMade(char player) {
		
		int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
		int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				
				if(map[i][j] == player) {
					
					for(int d=0; d<8; d++) {
						int count = 0;
						int cy = i;
						int cx = j;
						
						while(true) {
							
							cy += dr[d];
							cx += dc[d];
							
							if(!check(cy, cx)) {
								break;
							}
							
							if(map[cy][cx] != player) {
								break;
							}
							
							count += 1;
						}// while 
						
						if(count == 3) {
							return true;
						}
						
					}// for d
					
				}// if
				
			}// for j
		}// for i
		
		return false;
	}
	
	static boolean check(int y, int x) {
		return y >= 0 && y < 3 && x >= 0 && x < 3;
	}
}
