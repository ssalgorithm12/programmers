import java.util.*;

// 110이 항상 앞으로 가면 최소를 보장할 수 없음
class Solution {
    static char[] arr;
    public String[] solution(String[] s) {
        
        int n = s.length;
        String[] answer = new String[n];
        
        for(int i = 0; i < n; i++) {
            int cnt = 0;                                // 1개수
            StringBuilder sb = new StringBuilder();
            
            for(int j = 0; j < s[i].length(); j++) {                
                if(s[i].charAt(j) == '0') {             // 0일 경우
                    int add = 0;                        // 앞에 있는 1의 개수
                    String tmp = "0";                   
                    while(cnt > 0 && add < 2) {         // 최대 110까지 만들 수 있음
                        cnt--;                          // 1의 개수 줄이기
                        tmp = "1" + tmp;
                        add++;                          // 1을 몇 개 사용했는지
                    }
                    sb.append(tmp);
                    
                } else cnt++;                           // 1 개수 추가
            }
            for(int j = 0; j < cnt; j++) {
                sb.append("1");
            }
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}