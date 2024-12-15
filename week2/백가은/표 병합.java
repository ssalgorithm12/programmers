import java.util.*;

class Solution {
    static StringTokenizer st;
    static String[][] map = new String[51][51];
    static P[][] parent = new P[51][51];
    static class P{
        int r, c;
        P(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public String[] solution(String[] commands) {
        List<String> ansList = new ArrayList<>();
        for(int i = 1; i <= 50; i++) {
            for(int j = 1; j <= 50; j++) {
                parent[i][j] = new P(i, j);
            }
        }
        int n = commands.length;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(commands[i]);
            String comm = st.nextToken();
            
            if(comm.equals("UPDATE")) {
                String next = st.nextToken();
                
                if(isNumber(next.charAt(0))) {
                    int r = Integer.parseInt(next);
                    int c = Integer.parseInt(st.nextToken());
                    String val = st.nextToken();
                    changeByLoc(new P(r, c), val);
                    
                } else {
                    String val1 = next;
                    String val2 = st.nextToken();
                    changeByValue(val1, val2);
                }
                
            } else if(comm.equals("MERGE")) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                merge(new P(r1, c1), new P(r2, c2));
                
            } else if(comm.equals("UNMERGE")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                unmerge(new P(r, c));
                
            } else {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                P p = find(new P(r, c));
                String val = map[p.r][p.c];
                if(val == null) ansList.add("EMPTY");
                else ansList.add(val);
            }
        }
        int size = ansList.size();
        String[] answer = new String[size];
        for(int i = 0; i < size; i++) {
            answer[i] = ansList.get(i);
        }
        return answer;
    }
    
    static boolean isNumber(char c) {
        if(c - '0' >= 0 && c - '0' <= 9) return true;
        return false;
    }
    
    static void changeByLoc(P p, String val){
        p = find(p);
        map[p.r][p.c] = val;
    }
    
    static void changeByValue(String val1, String val2) {
        for(int i = 1; i <= 50; i++) {
            for(int j = 1; j <= 50; j++) {
                P p = find(new P(i, j));
                if(map[p.r][p.c] != null && map[p.r][p.c].equals(val1)) {
                    map[p.r][p.c] = val2;
                }
            }
        }
    }
    
    static void merge(P p1, P p2) {
        p1 = find(p1);
        p2 = find(p2);
        
        if(!isSame(p1, p2)) {
            String val1 = map[p1.r][p1.c];
            String val2 = map[p2.r][p2.c];

            if(val1 != null) {
                parent[p2.r][p2.c] = p1;

            } else if(val1 == null && val2 != null) {
                parent[p1.r][p1.c] = p2;          
            }
        }
    }
    
    // 부모가 분산됐을 수 있음?
    static void unmerge(P p) {
        P par = find(p);
        String val = map[par.r][par.c] != null ? new String(map[par.r][par.c]) : null;
        for(int i = 1; i <= 50; i++) {
            for(int j = 1; j <= 50; j++) {
                if(isSame(find(new P(i, j)), par)) {
                    parent[i][j] = new P(i, j);
                    if(isSame(new P(i, j), p)) map[i][j] = val;
                    else map[i][j] = null;
                }
            }
        }
    }
    
    static P find(P p) {
        if(parent[p.r][p.c].r == p.r && parent[p.r][p.c].c == p.c) return p;
        return parent[p.r][p.c] = find(parent[p.r][p.c]);
    }
    
    static boolean isSame(P p1, P p2) {
        if(p1.r == p2.r && p1.c == p2.c) return true;
        return false;
    }
}