package programmers;

import java.util.Arrays;

public class Solution {

	static int n;

	static int[] answer;
	static int[] player;
	static int[] challenger;
	static int[] playerScore;
	static int[] challengerScore;
	static double maxPercent;
	
	public static void main(String[] args) {
		
		int[][] dice = {{1,2,3,4,5,6}, {3,3,3,3,4,4}, {1,3,3,4,4,4}, {1,1,4,4,5,5}};
		
		solution(dice);

	}
	
	static public int[] solution(int[][] dice) {
		
		n = dice.length;
		
		player = new int[n/2];
		challenger = new int[n/2];
		
		int scoreLength = (int) Math.pow(6, n/2);
		playerScore = new int[scoreLength];
		challengerScore = new int[scoreLength];
		
		maxPercent = -1;
		
		answer = DFS(0, 0);
		
		return answer;
	}
	
	static int[] DFS(int depth, int start) {
		
		if(depth == player.length) {
			
			System.out.println(Arrays.toString(player));
			
			findChallenger(player);
			
			return new int[] {0};
		}
		
		
		for(int i=start; i<n; i++) {
			
			player[depth] = i;
			
			DFS(depth + 1, i + 1);
			
		}
		
		return null;
		
	}
	
	static void findChallenger(int[] player) {
		
		challenger = new int[player.length];
		
		int step = 0;
		int cStep = 0;
		for(int i=0; i<n; i++) {
			
			if(step < player.length && player[step] == i) {
				step += 1;
				continue;
			}
			
			challenger[cStep] = i;
			cStep += 1;
		}
	}

}
