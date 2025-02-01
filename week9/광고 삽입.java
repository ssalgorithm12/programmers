import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int len = convertToSecond(play_time);
        int[] time = new int[len + 1];
        
        // stack.size()로 개수 세기?
        for(int i = 0; i < logs.length; i++) {
            String[] s = logs[i].split("-");
            int start = convertToSecond(s[0]);
            int end = convertToSecond(s[1]);
            
            for(int j = start; j <= end; j++) {
                time[j]++;
            }
        }
        
        int max = 0;
        int ans = 0;
        
        int left = 0;
        int right = convertToSecond(adv_time);
        
        int cnt = 0;
        for(int i = left; i <= right; i++) {
            cnt += time[i];
        }
        
        // 슬라이딩 윈도우
        while(true) {
            if(cnt > max) {
                max = cnt;
                ans = left;
            }
            if(right == len) break;
            cnt -= time[left++];
            cnt += time[++right];
        }
        
        return convertToTime(ans);
    }
    
    static String convertToTime(int time) {
        String h = String.valueOf(time / 3600);
        if(h.length() == 1) h = "0" + h;
        time %= 3600;
        
        String m = String.valueOf(time / 60);
        if(m.length() == 1) m = "0" + m;
        
        String s = String.valueOf(time % 60);
        if(s.length() == 1) s = "0" + s;
        
        return  h + ":" + m + ":" + s;
    }
    
    static int convertToSecond(String time) {
        String[] s = time.split(":");
        return Integer.parseInt(s[2]) + 
            Integer.parseInt(s[1]) * 60 + 
            Integer.parseInt(s[0]) * 60 * 60;
    }
}