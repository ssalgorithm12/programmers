// 12.04
class Solution {
    String answer = "impossible";
    int[] xr = {1, 0, 0, -1};
    int[] yc = {0, -1, 1, 0};
    String[] add = {"d", "l", "r", "u"};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        find("", x, y, n, m, r, c, k);    
        return answer;
    }
    
    void find(String input, int x, int y, int n, int m, int r, int c, int k){
        if(answer == "impossible"){
         if(k==0){
            if(x==r && y == c){
                answer = input;
            }
        }
        else if(((k - Math.abs(x-r) - Math.abs(y - c)) & 1) != 1 && Math.abs(x-r) + Math.abs(y - c)<= k){
            for(int i = 0; i < 4; i++){
                int xt = x + xr[i];
                int yt = y + yc[i];
                if(xt > 0 && xt <= n && yt > 0 && yt <= m){
                    find(input + add[i], xt, yt, n, m, r, c, k - 1);
                }
            }
        }   
        }
    } 
}
