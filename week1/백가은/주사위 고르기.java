import java.util.*;

class Solution {
    
    static int n, max, len;
    static boolean[] visited;
    static int[] ans, sumA, sumB, sum;
    static int[][] d;
    
    public int[] solution(int[][] dice) {
        
        d = dice;
        n = dice.length;
        len = (int)Math.pow(6, n / 2);
        visited = new boolean[n];
        ans = new int[n / 2];
        comb(0, 0);
        
        return ans;
    }
    
    static void comb(int at, int depth) {
        
        if(depth == n / 2) {
            int tmp = calProb();
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
        int win = 0;
        
        int[] a = devide(true);
        int[] b = devide(false);
        
        calScore(a, 0, 0);
        sumA = sum;
        calScore(b, 0, 0);
        sumB = sum;
        Arrays.sort(sumA);
        Arrays.sort(sumB);
        
        int lower = 0;
        int upper = len - 1;
        for(int i = 0; i < len; i++) {
            lower = binarySearch(sumA[i], lower, upper);
            win += (lower + 1);
        }
        
        return win;
    }
    
    static int[] devide(boolean flag) {
        int[] result = new int[n / 2];
        int idx = 0;
        for(int i = 0; i < n; i++) {
            if(visited[i] == flag) result[idx++] = i;
        }
        
        return result;
    }
    
    static void calScore(int[] dice, int idx, int at) {
        
        for(int i = 0; i < len; i++) {
            
        }
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