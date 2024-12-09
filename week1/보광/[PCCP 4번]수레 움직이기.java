class Solution {
    class point{
        int x;
        int y;
        
        point(){};
        
        point(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        boolean equal(point comp){
            boolean result = false;
            if(x == comp.x && y == comp.y) result = true;
            return result; 
        }
    }
    
    point b_e, r_e;
    int[] r = {1, -1, 0, 0};
    int[] c = {0, 0, 1, -1};
    boolean[][] visited_r;
    boolean[][] visited_b;
    int min;
    
    public int solution(int[][] maze) {
        point b_s = new point();
        point r_s = new point();
        b_e = new point();
        r_e = new point();        
        visited_r = new boolean[maze.length][maze[0].length];
        visited_b = new boolean[maze.length][maze[0].length];
        min = Integer.MAX_VALUE; 
        
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                int now = maze[i][j];
                if(now == 1){
                    r_s = new point(i, j);
                    visited_r[i][j] = true;
                    maze[i][j] = 0;
                }else if(now == 2){
                    b_s = new point(i, j);
                    visited_b[i][j] = true;
                    maze[i][j] = 0;
                }else if(now == 3)
                    r_e = new point(i, j);
                else if(now == 4)
                    b_e = new point(i, j);
            }
        }
                
        search(maze,r_s, b_s, 0);
        if(min == Integer.MAX_VALUE) min = 0;
        return min;
    }
    
    void search(int[][] maze,point r_now, point b_now, int count){
        point next_r = new point();
        point next_b = new point();
        
        if(r_now.equal(r_e) && b_now.equal(b_e)){
            min = Math.min(count, min);
        }
        else{
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    if(r_now.equal(r_e)) next_r = r_now;
                    else{
                        next_r = new point(r_now.x + r[i], r_now.y + c[i]);
                        if(check(next_r) || visited_r[next_r.x][next_r.y]) continue;
                    }
                    if(b_now.equal(b_e)) next_b = b_now;
                    else {
                        next_b = new point(b_now.x + r[j], b_now.y + c[j]);
                        if(check(next_b) || visited_b[next_b.x][next_b.y]) continue;                    
                    }
                    if(next_r.equal(b_now)&&next_b.equal(r_now)) continue;
                    if(next_r.equal(next_b)) continue;
                    if(maze[next_r.x][next_r.y] == 5 || maze[next_b.x][next_b.y] == 5) continue;
                    
                    visited_b[next_b.x][next_b.y] = true;
                    visited_r[next_r.x][next_r.y] = true;
                    search(maze,next_r, next_b, count+1);
                    visited_b[next_b.x][next_b.y] = false;
                    visited_r[next_r.x][next_r.y] = false;
                }
            }
        }
    }
    
    boolean check(point now){
        boolean result = false; 
        if(now.x < 0 || now.x >= visited_r.length) result = true;
        if(now.y < 0 || now.y >= visited_r[0].length) result = true;
        return result; 
    }
}
