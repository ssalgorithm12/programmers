import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        
        Stack<Integer> removed = new Stack<>();
        int cur = k;
        int size = n;
        
        for(int i = 0; i < cmd.length; i++) {
            String[] comm = cmd[i].split(" ");
            String type = comm[0];
            
            if(type.equals("U")) cur -= Integer.parseInt(comm[1]);                
            else if(type.equals("D")) cur += Integer.parseInt(comm[1]);
            
            else if(type.equals("C")) {
                removed.push(cur);              // 삭제할 행의 상대적 위치를 스택에 저장
                size--;                         // O 개수 줄이기
                if(cur == size) cur--;          // 마지막 행이라면 행 번호 줄이기
                    
            } else{
                if(removed.pop() <= cur) cur++; // 삭제된 행 복구
                size++;                         // O 개수 늘리기
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) {         // 현재 남아있는 O의 개수만큼만 추가하기
            sb.append("O");
        }
        
        while(!removed.isEmpty()) {             // 삭제 당시의 상대적 위치에 X 넣기
            sb.insert(removed.pop().intValue(), "X");
        }
            
        return sb.toString();
    }
}