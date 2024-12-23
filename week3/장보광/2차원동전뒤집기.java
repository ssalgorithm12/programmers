class Solution {
    int answer = Integer.MAX_VALUE;
    public int solution(int[][] beginning, int[][] target) {
        check(beginning, target, 0, 0, 0);
        if(answer==Integer.MAX_VALUE) answer = -1; 
        return answer;
    }
    
    void check(
        int[][] beginning, 
        int[][] target,
        int x,
        int y,
        int count){
        if(x < beginning.length){
            check(beginning, target, x+1, y, count);
            for(int i = 0; i < beginning[0].length; i++)
                beginning[x][i] = beginning[x][i] ^ 1; 
            check(beginning, target, x+1, y, count+1);
            for(int i = 0; i < beginning[0].length; i++)
                beginning[x][i] = beginning[x][i] ^ 1;
        }else if(y < beginning[0].length){
            boolean c = true;
            for(int i = 0; i < beginning.length; i++){
                if(beginning[i][y]!=target[i][y])
                    c = false;
            }
            if(c) check(beginning, target, x, y+1, count);
            c = true;
            for(int i = 0; i < beginning.length; i++){
                if(beginning[i][y]==target[i][y])
                    c = false;
            }
            if(c){
                for(int i = 0; i < beginning.length; i++)
                    beginning[i][y] = beginning[i][y] ^ 1; 
                check(beginning, target, x, y+1, count+1); 
                for(int i = 0; i < beginning.length; i++)
                    beginning[i][y] = beginning[i][y] ^ 1;
            }
        }else{
            if(answer > count) 
                answer = count;
        }        
    }
}
