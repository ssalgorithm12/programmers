import java.util.Stack;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();
        int size = cmd.length;

        // boolean 배열로 삭제된 행을 표시
        boolean[] check = new boolean[n];

        // Stack으로 최근에 삭제된 인덱스를 저장
        Stack<Integer> s = new Stack();

        // LinkedList로 활용
        int[] prev = new int[n];
        int[] next = new int[n];

        // 배열 초기화
        for (int i=0; i<n; i++) {
            prev[i] = i-1;
            next[i] = i+1;
        }

        for (int i = 0; i < size; i++) {
            String[] ss = cmd[i].split(" ");
            if(ss[0].equals("U")) {
                int step = Integer.parseInt(ss[1]);

                // step만큼 이전 행으로 이동
                while(step-- > 0) {
                    k = prev[k];
                }
            } else if(ss[0].equals("D")) {
                int step = Integer.parseInt(ss[1]);

                // step만큼 이후 행으로 이동
                while(step-- > 0) {
                    k = next[k];
                }
            } else if(ss[0].equals("C")) {
                // 삭제된 행을 stack에 저장
                s.push(k);

                // 해당 행을 삭제 처리함
                check[k] = true;

                // LinkedList처럼 현재 행 이전의 행과 이후의 행을 연결시킴
                if(prev[k]!=-1) next[prev[k]] = next[k];
                if(next[k]!=n) prev[next[k]] = prev[k];

                k = next[k] != n ? next[k] : prev[k];
            } else if (ss[0].equals("Z")) {
                // 최근에 삭제된 행을 복구
                int m = s.pop();
                check[m] = false;

                // 삭제된 행의 이전 행과 이후 행을 다시 연결시킴
                if (prev[m] != -1) next[prev[m]] = m;
                if (next[m] != n) prev[next[m]] = m;
            }
        }

        // check 배열로 삭제된 행의 위치 확인
        for (int i = 0; i < check.length; i++) {
            if (check[i]) sb.append("X");
            else sb.append("O");
        }

        return sb.toString();
    }
}