package programmers;

import java.util.Arrays;

// DFS * 3

// 가지 치기 필요 

// 50.0 / 100.0

public class 주사위게임 {

	public static void main(String[] args) {
		
		int[][] dice = {{1,2,3,4,5,6}, {3,3,3,3,4,4}, {1,3,3,4,4,4}, {1,1,4,4,5,5}};
		
		solution(dice);

	}
	
	static int n;
	static int[] answer;
	static int[] player;
	static int[] challenger;
	static int count;
	static int maxCount;
	
	static public int[] solution(int[][] dice) {
		
		n = dice.length;
		
		player = new int[n/2];
		challenger = new int[n/2];

		maxCount = -1;
		
		DFS(0, 0, dice);
		
		for(int i=0; i<answer.length; i++) {
			answer[i] += 1;
		}
		
		return answer;
	}
	
	static void playerScore(int depth, int total, int[][] dice) {
		
		if(depth == n/2) {
			
			challengerScore(0, 0, dice, total);
			
			return;
		}
		
		for(int i=0; i<6; i++) {
			
			playerScore(depth + 1, total + dice[player[depth]][i], dice);
			
		}
		
	}
	
	static void challengerScore(int depth, int total, int[][] dice, int player) {
		
		if(total >= player) {
			return;
		}
		
		if(depth == n/2) {
			
			if(total < player) {
				
				count += 1;
				
			}
			
			return;
		}
		
		for(int i=0; i<6; i++) {
			
			challengerScore(depth + 1, total + dice[challenger[depth]][i], dice, player);
			
		}
		
	}
	
	static void DFS(int depth, int start, int[][] dice) {
		
		if(depth == player.length) {
					
			findChallenger(player);
			
			count = 0;
			
			playerScore(0, 0, dice);
			
			if(count > maxCount) {
				
				maxCount = count;
				
				answer = Arrays.copyOf(player, player.length);
			}
			
			return;
		}
		
		for(int i=start; i<n; i++) {
			
			player[depth] = i;
			
			DFS(depth + 1, i + 1, dice);
			
		}
		
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
