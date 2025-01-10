import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        
        List<Integer> table = new ArrayList<>();        // 표 만들기
        for(int i = 0; i < n; i++) {
            table.add(i);
        }
        Stack<Integer> removed = new Stack<>();         // 삭제된 행 저장
        int cur = k;                                    // 현재 인덱스
        
        for(int i = 0; i < cmd.length; i++) {
            String[] comm = cmd[i].split(" ");
            String type = comm[0];
            
            if(type.equals("U")) {
                int val = Integer.parseInt(comm[1]);
                cur -= val;
                
            } else if(type.equals("D")) {
                int val = Integer.parseInt(comm[1]);
                cur += val;
                
            } else if(type.equals("C")) {
                removed.push(table.remove(cur));        // 현재 선택된 행을 삭제
                if(cur == table.size()) cur -= 1;       // 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택
                
            } else {
                int restore = removed.pop();            // 가장 최근에 삭제된 행의 값
                int idx = binarySearch(restore, table); // 복구 장소
                table.add(idx, restore);                // 복구
                if(idx <= cur) cur += 1;                // 현재 선택된 행 유지
            }
        }
        
        String[] ansArr = new String[n];
        Arrays.fill(ansArr, "X");
        for(int num : table) {
            ansArr[num] = "O";
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(ansArr[i]);
        }

        return sb.toString();
    }
    
    static int binarySearch(int target, List<Integer> list) {
        
        int len = list.size();
        int lower = 0;
        int upper = list.size() - 1;
        int mid = 0;
        
        while(lower <= upper) {
            mid = (lower + upper) / 2;
            
            if(mid == 0 && list.get(mid) > target) return 0;
            if(mid == len - 1 && list.get(mid) < target) return len;
            
            int l = list.get(mid);
            int r = list.get(mid + 1);
            if(l < target && r > target) return mid + 1;
            
            if(r < target) lower = mid + 1;
            if(l > target) upper = mid - 1;            
        }
        
        return -1;
    }
}