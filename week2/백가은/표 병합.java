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
        int n = commands.length();
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
                
            } else if(comm.eqauls("MERGE")) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                P p1 = new P(r1, c1);
                P p2 = new P(r2, c2);
                union(p1, p2);
                
            } else if(comm.eqauls("UNMERGE")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                unmerge(new P(r, c));
                
            } else {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                P parent = find(new P(r, c));
                String val = map[parent.r][parent.c];
                if(val == null) list.add("EMPTY");
                else list.add(val);
            }
        }
        int size = ansList.size();
        int[] answer = new int[size];
        for(int i = 0; i < size; i++) {
            answer[i] = ansList.get(i);
        }
        return answer;
    }
    
    static boolean isNumber(char c) {
        if(c - '0' >= 0 && c - '0' <= 50) return true;
        return false;
    }
    
    static void changeByLoc(P p, String val){
        
    }
    
    static void changeByValue(String val1, String val2) {
        
    }
    
    static void union(P p1, P p2) {
        
        P p1 = find(p1);
        P p2 = find(p2);
        
        if(!isSame(find1, find2)) {
            String val1 = map[p1.r][p1.c];
            String val2 = map[p2.r][p2.c];

            if(val1 != null) {
                p[p2.r][p2.c] = new P(p1.r, p1.c);

            } else if(val1 == null && val2 != null) {
                p[p1.r][p1.c] = new P(p2.r, p2.c);          
            }
        }
    }
    
    static void unmerge(P p) {
        P parent = find(p);
        String val = map[parent.r][parent.c];
        
    }
    
    static P find(P p) {
        if(p[p.r][p.c].r == p.r && p[p.r][p.c].c == p.c) return p;
        p[p.r][p.c] = find(p[p.r][p.c]);
    }
    
    static boolean isSame(P p1, P p2) {
        if(p1.r == p2.r && p1.c == p2.c) return true;
        return false;
    }
}