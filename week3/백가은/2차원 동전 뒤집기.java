class Solution {
    
    static int ans = Integer.MAX_VALUE;
    static int h, w;
    
    public int solution(int[][] beginning, int[][] target) {
        h = beginning.length;
        w = beginning[0].length;
        check(beginning, target, 0, 0, 0);
        
        if(ans==Integer.MAX_VALUE) return -1; 
        return ans;
    }
    
    static void check(int[][] beginning, int[][] target, int x, int y, int cnt) {
        
        // 먼저 행 뒤집기
        if(x < h){
            
            check(beginning, target, x+1, y, cnt);      // 뒤집지 않는 경우
            
            for(int i = 0; i < w; i++) {                // 뒤집는 경우
                beginning[x][i] = beginning[x][i] ^ 1; 
            }
            check(beginning, target, x+1, y, cnt + 1);
            
            for(int i = 0; i < w; i++) {                // 복구
                beginning[x][i] = beginning[x][i] ^ 1;
            }
          
        // 열 뒤집기
        } else if(y < w) {
            
            // 가지치기
            // 현재 열이 목표상태와 동일한 경우에만 뒤집지 않고 넘어감
            boolean isValid = true;
            for(int i = 0; i < h; i++){
                if(!isValid) break;
                if(beginning[i][y] != target[i][y]) isValid = false;
            }
            if(isValid) check(beginning, target, x, y+1, cnt);      // 뒤집지 않는 경우
            
            // 가지치기
            // 현재 열이 목표상태와 모두 다를 경우에만 뒤집기
            isValid = true;
            for(int i = 0; i < h; i++){
                if(!isValid) break;
                if(beginning[i][y]==target[i][y]) isValid = false;
            }
            if(isValid){
                for(int i = 0; i < h; i++) {                        // 뒤집는 경우
                    beginning[i][y] = beginning[i][y] ^ 1; 
                }
                check(beginning, target, x, y+1, cnt + 1); 
                
                for(int i = 0; i < h; i++) {                        // 복구
                    beginning[i][y] = beginning[i][y] ^ 1;
                }   
            }
            
        } else ans = Math.min(ans, cnt);       
    }
}
