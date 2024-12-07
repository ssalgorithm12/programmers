import java.util.*;

// 완탐 -> 시간, 메모리 초과
// dp 구현을 못 하겠어요
class Solution {
    
    static int coins, ans, len;
    static int[] cardArr;
    static Queue<Round> q;
    static Round dfsRound;
    static class Round{
        int n, cardIdx, coinLeft;
        List<Integer> cardList;
        
        Round(){};
        Round(int n, int cardIdx, int coinLeft, List<Integer> cardList) {
            this.n = n;
            this.cardIdx = cardIdx;
            this.coinLeft = coinLeft;
            this.cardList = cardList;
        }
    }
    
    public int solution(int coin, int[] cards) {
        coins = coin;
        cardArr = cards;
        len = cardArr.length;
        
        bfs();
        return ans;
    }
    
    // 완전히 동일한 상태일 때 중복체크를 통해 q에 추가 방지해야 함
    static void bfs() {
        q = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < len / 3; i++) {
            list.add(cardArr[i]);
        }
        q.offer(new Round(1, len / 3, coins, list));
        
        while(!q.isEmpty()) {
            Round cur = q.poll();
            ans = cur.n;
            
            if(cur.cardIdx == len - 1 || cur.cardList.isEmpty()) continue;
            
            int c1 = cardArr[cur.cardIdx];
            int c2 = cardArr[cur.cardIdx + 1];
            
            List<Integer> newList;
            
            dfsRound = new Round();
            dfsRound.n = cur.n;
            dfsRound.cardIdx = cur.cardIdx + 2;
            
            if(cur.coinLeft >= 2) {
                newList = new ArrayList<>();
                for(int c : cur.cardList) {
                    newList.add(c);
                }
                newList.add(c1);
                newList.add(c2);
                dfsRound.coinLeft = cur.coinLeft - 2;
                dfsRound.cardList = newList;
                dfs(0, 0, new int[2]);
            }
            
            if(cur.coinLeft >= 1) {
                newList = new ArrayList<>();
                for(int c : cur.cardList) {
                    newList.add(c);
                }
                newList.add(c1);
                dfsRound.coinLeft = cur.coinLeft - 1;
                dfsRound.cardList = newList;
                dfs(0, 0, new int[2]);
                
                newList.remove(newList.size() - 1);
                newList.add(c2);
                dfsRound.cardList = newList;
                dfs(0, 0, new int[2]);
            }
            
            newList = new ArrayList<>();
            for(int c : cur.cardList) {
                newList.add(c);
            }
            dfsRound.coinLeft = cur.coinLeft;
            dfsRound.cardList = newList;
            if(newList.size() >= 2) dfs(0, 0, new int[2]);
            
        }
    }
    
    static void dfs(int at, int depth, int[] pick) {
        
        if(depth == 2) {
            if(pick[0] + pick[1] == len + 1) {
                List<Integer> tmp = new ArrayList<>();
                for(int i = 0; i < dfsRound.cardList.size(); i++) {
                    int num = dfsRound.cardList.get(i);
                    if(num == pick[0] || num == pick[1]) continue;
                    tmp.add(num);
                }
                q.offer(new Round(dfsRound.n + 1, dfsRound.cardIdx, dfsRound.coinLeft, tmp));
            }
            
            return;
        }
        
        for(int i = at; i < dfsRound.cardList.size(); i++) {
            int num = dfsRound.cardList.get(i);
            if(num >= len + 1) continue;
            pick[depth] = num;
            dfs(i + 1, depth + 1, pick);
        }
    }
    
}