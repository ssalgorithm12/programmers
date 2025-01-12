import java.util.*;


/*
 * <가은 피드백>
 * - 배열로 접근해서 탐색 시간을 줄이려 했지만 while 반복으로 시간복잡도가 해결이 안 된게 맞나요?
 * - boolean 배열의 이름을 좀더 명확히 명명하면 보기 더 좋을 것 같습니다
 */
class Solution {
    public String solution(int n, int k, String[] cmd) {
        boolean[] arr = new boolean[n];
        Stack<Integer> s = new Stack<Integer>(); 
        StringTokenizer st; 
        int now = k;
        
        for(String x: cmd){
            st = new StringTokenizer(x);
            char c = st.nextToken().toCharArray()[0];
            if(c=='U'){
                int count = Integer.parseInt(st.nextToken());
                while(count>0){
                    now--;
                    if(!arr[now])
                        count--;
                }
            }else if(c=='D'){
                int count = Integer.parseInt(st.nextToken());
                while(count>0){
                    now++;
                    if(!arr[now])
                        count--;
                }
            }else if(c=='C'){
                arr[now] = true;
                int pre = now; 
                s.push(now);
                while(now < n && arr[now])
                    now++;
                
                if(now >= n)
                    now = pre;
                    while(now > -1 && arr[now])
                        now--;
            }else if(c=='Z'){
                arr[s.pop()] = false;
            }
        }
        
        String answer = "";
        for(boolean t: arr){
            if(t)
                answer+="X";
            else
                answer+="O";
        }
        return answer;
    }
}
