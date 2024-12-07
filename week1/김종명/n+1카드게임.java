package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 실패
// BFS 사용

public class 카드게임 {

	public static void main(String[] args) {
	
		System.out.println(solution(4, new int[]{3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4}));
	}
	
	static int n;
	
	static class Hand{
		
		ArrayList<Integer> card;
		int coin;
		int index;
		
		public Hand(int coin) {
			card = new ArrayList<>();
			this.coin = coin;
		}
		
	}
	
	static Hand hand;
	static Queue<Hand> container;
	static int turn;
	
	
	static public int solution(int coin, int[] cards) {
		
		n = cards.length;

		hand = new Hand(coin);
		
		for(int i=0; i<n/3; i++) {
			
			hand.card.add(cards[i]);
			
		}
		
		turn = 1;
		
		BFS(cards);
	
		return turn;
	}
	
	static void BFS(int[] cards) {
		
		container = new LinkedList<>();
		container.offer(hand);
		hand.index = n/3;
		
		while(!container.isEmpty()) {
			
			int size = container.size();
			
			for(int i=0; i<size; i++) {
				
				Hand cur = container.poll();
				
				if(cur.coin <= 0) {
					continue;
				}
				
				int curIndex = cur.index;
				
				// 카드 한 개도 안집었을 때
				Hand new_a = new Hand(cur.coin);
				
				new_a.card.addAll(cur.card);
				
				loopA:
				for(int x=0; x<new_a.card.size(); x++) {
					
					for(int y=0; y<new_a.card.size(); y++) {
						
						if(new_a.card.get(x) + new_a.card.get(y) == n+1) {
							
							if(x > y) {
							    new_a.card.remove(x);
							    new_a.card.remove(y);
							} else {
							    new_a.card.remove(y);
							    new_a.card.remove(x);
							}
							
							new_a.index = curIndex + 2;
							
							container.offer(new_a);
							
							break loopA;
						}
						
					}
				}
				
				// 카드 한 개 집었을 때
				Hand new_b = new Hand(cur.coin);
				
				new_b.card.addAll(cur.card);
				
				if(curIndex < n) {
					
					new_b.card.add(cards[curIndex]);
					
					loopB:
					for(int x=0; x<new_b.card.size(); x++) {
						
						for(int y=0; y<new_b.card.size(); y++) {
							
							if(new_b.card.get(x) + new_b.card.get(y) == n+1) {
								
								if(x > y) {
								    new_b.card.remove(x);
								    new_b.card.remove(y);
								} else {
								    new_b.card.remove(y);
								    new_b.card.remove(x);
								}
								
								new_b.coin -= 1;
								
								if(new_b.coin < 0) {
									continue;
								}
								
								new_b.index = curIndex + 2;
								
								container.offer(new_b);
								
								break loopB;
							}	
							
						}
					}
					
				}
				
				// 카드 두 개 집었을 때
				Hand new_c = new Hand(cur.coin);
				
				new_c.card.addAll(cur.card);
				
				if(curIndex < n-1) {
					new_c.card.add(cards[curIndex]);
					new_c.card.add(cards[curIndex + 1]);
					
					loopC:
					for(int x=0; x<new_c.card.size(); x++) {
						
						for(int y=0; y<new_c.card.size(); y++) {
							
							if(new_c.card.get(x) + new_c.card.get(y) == n+1) {
								
								if(x > y) {
								    new_c.card.remove(x);
								    new_c.card.remove(y);
								} else {
								    new_c.card.remove(y);
								    new_c.card.remove(x);
								}
								
								new_c.coin -= 2;
								
								if(new_c.coin < 0) {
									continue;
								}
								
								new_c.index = curIndex + 2;
								
								container.offer(new_c);
								
								break loopC;
							}
							
						}
					}
				}
				
			}// size for
			
			turn += 1;
		}
		
	}


}
