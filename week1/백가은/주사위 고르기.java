import java.util.*;

class Solution {
    
    static int n, max, len;
    static boolean[] visited, used;
    static int[] ans, sumA, sumB, sum;     // sum에 계산 결과 저장하여 a, b 각각에 할당
    static int[][] d;
    
    public int[] solution(int[][] dice) {
        
        d = dice;
        n = dice.length;
        len = (int)Math.pow(6, n / 2);
        sumA = new int[len];
        sumB = new int[len];
        sum = new int[len];
        visited = new boolean[n];        // 조합 방문 배열
        ans = new int[n / 2];            // 정답 주사위 조합
        comb(0, 0);
        
        return ans;
    }
    
    // 주사위 조합
    static void comb(int at, int depth) {
        
        if(depth == n / 2) {
            int tmp = calProb();      // 각 조합마다 확률 계산
            if(tmp > max) {
                tmp = max;
                ans = devide(true);
            }
            return;
        }
        
        for(int i = at + 1; i < n; i++) {
            visited[i] = true;
            comb(i, depth + 1);
            visited[i] = false;
        }
    }
    
    static int calProb() {
        int win = 0;              // 승리 횟수
        
        int[] dice = divide(true);   // 조합 결과에 따라 배열 생성
        used = new boolean[n / 2]; 
        calScore(dice, 0, 0);        // 순열 돌려서 모든 경우에 대한 점수 배열 생성
        sumA = sum;    
        
        dice = divide(false);        // b도 동일하게 계산
        used = new boolean[n / 2]; 
        calScore(dice, 0, 0);
        sumB = sum; 
        
        Arrays.sort(sumA);        // 이분탐색 위한 정렬
        Arrays.sort(sumB);
        
        int lower = 0;
        int upper = len - 1;
        
        // 이분탐색으로 승률 계산
        // 전체 경우의 수 중에 a 점수가 더 높은 경우가 몇 개 있는지 계산
        for(int i = 0; i < len; i++) {
            lower = binarySearch(sumA[i], lower, upper);
            win += (lower + 1);
        }
        
        return win;
    }
    
    static int[] divide(boolean flag) {
        int[] result = new int[n / 2];
        int idx = 0;
        for(int i = 0; i < n; i++) {
            if(visited[i] == flag) result[idx++] = i;
        }
        
        return result;
    }
    
    // 가져간 주사위의 가능한 모든 경우의 점수 합 구하기
    // 구현 못 하겠어요
    static void calScore(int[] dice, int depth, int sum) {
    }
    
    static int binarySearch(int target, int lower, int upper) {
        int mid;
        int idx = -1;
        while(lower <= upper) {
            mid = (lower + upper) / 2;
            if(sumB[mid] < target && mid > idx) {
                idx = mid;
            }
            
            if(sumB[mid] <= target) lower = mid + 1;
            else upper = mid - 1;
        }
        
        return idx;
    }
}