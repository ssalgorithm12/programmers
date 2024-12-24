class Solution {
    int answer = Integer.MAX_VALUE; 
    int size;
    int[] r = {1, -1, 0, 0};
    int[] c = {0, 0, -1, 1};
    
    public int solution(int[][] clockHands) {
        size = clockHands.length;
        // dfs 함수 호출 
        find(clockHands, 0, 0, 0);
        return answer;
    }

    // 이 문제는 배열의 첫 번째 줄만 결정이 되면 그 이후의 줄의 값은 고정이다.   
    // 백트랙킹
    void find(int[][] clockHands, int x, int y, int count){
        if(x == size && y == 0){
            if(answer > count)
                answer = count;
        }else{
         for(int i = 0; i < 4; i++){
            boolean check = false; 
            clockHands[x][y] += i;
            for(int j = 0; j < 4; j++){
                int xr = x + r[j];
                int yc = y + c[j];
                if(xr > -1 && xr < size && yc > -1 && yc < size)
                    clockHands[xr][yc] += i;
            }
            if(x > 0 && clockHands[x-1][y]%4 != 0) check = true;
            if(x == size - 1&& y > 0 && clockHands[x][y-1]%4 != 0) check = true;
            if(x == size - 1&& y == size - 1 && clockHands[x][y]%4 != 0) check = true;
            if(!check){
                if((y+1)%size==0)
                    find(clockHands, x + 1, 0, count + i);
                else 
                    find(clockHands, x, y + 1, count + i);
            }
            clockHands[x][y] -= i;
            for(int j = 0; j < 4; j++){
                int xr = x + r[j];
                int yc = y + c[j];
                if(xr > -1 && xr < size && yc > -1 && yc < size)
                    clockHands[xr][yc] -= i;
            }
        }   
        }
    }
}
