package programmers;

public class 사라지는발판 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	static class Play{
		
		int[][] map;
		int[] playerA;
		int[] playerB;
		// true : A, false : B
		boolean turn;
		int count;
		
		public Play(int[][] map, int[] playerA, int[] playerB, boolean turn, int count) {
			
			this.map = new int[map.length][];
			
			for(int i=0; i<map.length; i++) {
				this.map[i] = map[i].clone();
			}
			
			this.playerA = playerA.clone();
			this.playerB = playerB.clone();
			
			this.turn = turn;
			this.count = count;
		}
		
	}
	
    public int solution(int[][] board, int[] aloc, int[] bloc) {
    	
    	Play start = new Play(board, aloc, bloc, true, 0);
    	
    	BFS(start);
    	
        int answer = -1;
        return answer;
    }
    
    static void BFS(Play p) {
    	
    	
    	
    }

}
