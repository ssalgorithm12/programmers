import java.util.*;

class Solution {
    
    static StringTokenizer st;
    static List<String[]> list = new ArrayList<>();  // 답 구해야 하는 수식 저장 리스트
    // ans : 몇 진법인지 특정되는 경우 ans 변수에 저장
    // max : 최대값 이상인 (max + 1)진법부터 가능
    static int ans, max;                             
    static boolean found;                            // 몇 진법인지 특정되었는지 여부 저장 변수
    
    public String[] solution(String[] e) {
        
        for(int i = 0; i < e.length; i++) {
            st = new StringTokenizer(e[i]);
            parsing(st);                            // 입력값 파싱하는 메서드
        }
        
        int size = list.size();
        String[] answer = new String[size];
        for(int i = 0; i < size; i++) {
            String[] s = list.get(i);
            String res = "";
            String tmp = "";
            
            // 입력값으로 몇 진수인지 특정되지 않는 경우
            if(ans == 0) {
                String caled = moreThanOne(s);
                tmp = s[0] + " " + s[1] + " " + s[2] + " = " + caled;
                
            } else {
                res = changeFromTen(calculate(s[0], s[1], s[2], ans), ans);
                tmp = s[0] + " " + s[1] + " " + s[2] + " = " + res;
            }
            answer[i] = tmp;
        }
        
        return answer;
    }
    
    static void parsing(StringTokenizer st) {
        
        String first = st.nextToken();                     // 첫 번째 수
        String op = st.nextToken();                        // 연산자
        String second = st.nextToken();                    // 두 번째 수
        st.nextToken();                                    // 등호
        

        // 최대값 갱신
        for(int i = 0; i < first.length(); i++) {
            max = Math.max(max, Integer.parseInt(String.valueOf(first.charAt(i))));
        }
        for(int i = 0; i < second.length(); i++) {
            max = Math.max(max, Integer.parseInt(String.valueOf(second.charAt(i))));
        }

        String result = st.nextToken();
        if(result.equals("X")) {                          // 결과값을 구해야 하는 경우 list에 저장
            list.add(new String[]{first, op, second});
            return;
        }
        
        for(int i = 0; i < result.length(); i++) {
            max = Math.max(max, Integer.parseInt(String.valueOf(result.charAt(i))));
        }
        
        if(!found) find(first, op, second, result);      // 결과값이 나와있는 경우 몇 진수인지 찾는 메서드
    }
    
    static void find(String first, String op, String second, String result){
        
        int cnt = 0;
        int tmp = 0;
        for(int i = max; i <= 9; i++) {
            if(calculate(first, op, second, i).equals(changeToTen(result, i))) {
                tmp = i;
                cnt++;
            }
        }
        
        // 될 수 있는 값이 하나인 경우
        if(cnt == 1) {
            ans = tmp;
            found = true;
        }
    }
    
    // n진수를 10진수로 변환하여 연산자로 계산
    static String calculate(String first, String op, String second, int num) {
        String res1 = changeToTen(first, num);
        String res2 = changeToTen(second, num);
        
        int tmp = 0;
        if(op.equals("+")) {
            tmp = Integer.parseInt(res1) + Integer.parseInt(res2);
            
        } else tmp = Integer.parseInt(res1) - Integer.parseInt(res2);
        
        return String.valueOf(tmp);
    }
    
    // n진수를 10진수로 변환
    static String changeToTen(String target, int num) {
        
        int tmp = 0;
        int len = target.length();
        for(int i = 0; i < len; i++) {
            tmp += Integer.parseInt(String.valueOf(target.charAt(i))) * Math.pow(num, len - i - 1);
        }
        
        return String.valueOf(tmp);
    }
    
    static String moreThanOne(String[] s) {
        
        String first = s[0];
        String op = s[1];
        String second = s[2];
        
        int cnt = 0;
        String res = "";
        for(int i = max + 1; i <= 9; i++) {
            String tmp = changeFromTen(calculate(first, op, second, i), i );
            if(res.equals("")) res = tmp;
            else if(!tmp.equals(res)) return "?";
        }
        
        return res;        
    }
    
    // 10진수를 n진수로 변환
    static String changeFromTen(String target, int num) {
        
        int n = Integer.parseInt(target);
        StringBuilder sb = new StringBuilder();
        
        while (n > 0) {
            int remainder = n % num;
            sb.append(remainder);
            n /= num;
        }
        
        if(sb.length() == 0) return "0";
        return sb.reverse().toString();
    }
                
}