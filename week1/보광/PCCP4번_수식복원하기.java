import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        StringTokenizer st;
        // 결과가 정해진 문장
        List<String> str1 = new ArrayList<>();
        
        // 결과로 출력할 값 
        List<String> str2 = new ArrayList<>();
        
        // 최댓값 저장 
        int max = -1;
        int div = -1; 
        
        // 문장 분류
        for(String x: expressions){
            st = new StringTokenizer(x);
            int a = Integer.parseInt(st.nextToken());
            char op = st.nextToken().toCharArray()[0];
            int b = Integer.parseInt(st.nextToken());
            st.nextToken();
            max = Math.max(max_value(a),max);
            max = Math.max(max_value(b),max);
            String c = st.nextToken();
            
            if(c.equals("X")) str2.add(x);
            else {
                max = Math.max(max_value(Integer.parseInt(c)),max);
                str1.add(x);
            }
        }
        
        // 결과 리스트 저장 
        String[] answer = new String[str2.size()];
        
        // 진법 테스트 
        int[] check = new int[str2.size()];
        Arrays.fill(check, -1);
        for(int i = max+1; i < 10; i++){
            boolean ok = false;
            for(String x: str1){
                st = new StringTokenizer(x);
                int a = Integer.parseInt(st.nextToken());
                char op = st.nextToken().toCharArray()[0];
                int b = Integer.parseInt(st.nextToken());
                st.nextToken();
                int c = Integer.parseInt(st.nextToken());
                 System.out.println(a+" "+b+" "+c);
                int d = -1;
                if (op == '+') d = convert(a, 10, i) + convert(b, 10, i);
                else d = convert(a, 10, i) - convert(b, 10, i);
                
                if (d != convert(c, 10, i)) {
                    ok = true;
                    break;
                }
            }
            
            if(ok) continue;
            div = i;
            
            for(int j = 0; j < str2.size(); j++){
                st = new StringTokenizer(str2.get(j));
                int a = Integer.parseInt(st.nextToken());
                char op = st.nextToken().toCharArray()[0];
                int b = Integer.parseInt(st.nextToken());
                
                int d = -1;
                if (op == '+') d = convert(a, 10, i) + convert(b, 10, i);
                else d = convert(a, 10, i) - convert(b, 10, i);
                
                if (d >= 0 && check[j] == -1) check[j] = convert(d, i, 10);
                else if (check[j] != -1 && check[j] != convert(d, i, 10)) check[j] = -2;
            }    
        }
        
        for(int i = 0; i < str2.size(); i++){
            if(check[i] > -1)
                answer[i] = str2.get(i).replaceAll("X", String.valueOf(check[i]));
            else
                answer[i] = str2.get(i).replaceAll("X", "?");
        }
        
        //결과 출력 
        for(String x: answer)
            System.out.println(x);
        return answer;
    }
    
    //n -> m
    int convert(int c, int n, int m) {
        if (c < 0) return -1;
        int temp = 0, mult = 1;
        while (c > 0) {
            temp += (c % n) * mult;
            c /= n;
            mult *= m;
        }
        return temp;
    }
    
    int max_value(int c){
        int max = 0;
        while(c > 0){
            if(c % 10 > max) max = c % 10;
            c /= 10;
        }
        return max;
    }
}
