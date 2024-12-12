import java.util.*;

class Solution {
    public int solution(int k, int n, int[][] reqs) {
        int remain = n - k;
        // 유형 별 기다려야 하는 시간 
        int[][] wait = new int[k+1][remain+3];
        
        Arrays.sort(reqs, Comparator.comparingInt((int[] o) -> o[0]));
        
        // 각 유형별호 상담자 분리
        List<int[]>[] arr = new ArrayList[k+1];
        
        // 리스트 초기화 
        for(int i = 0; i <= k; i++) arr[i] = new ArrayList<>();
        
        // 리스트에 값 할당 
        for(int[] x: reqs) arr[x[2]].add(x);
        
        // 반복문을 통해 기다려야 하는 시간 계산  
        for(int i = 1; i <= k; i++){
            for(int j = 1; j <= remain+1; j++){
                int[] mentor = new int[j];
                int wait_time = 0; 
                for(int[] x: arr[i]){
                    // 각 멘티들을 기다리는 시간이 짧은 애들한 테 할당. 
                    int min_w = 10000000;
                    int mentor_num = 0;
                    for(int l = 0; l < j; l++){
                        if(mentor[l] <= x[0]){
                            min_w = 0;
                            mentor_num = l;
                            break;
                        }else{
                            if(min_w > mentor[l] - x[0]){
                                min_w = mentor[l] - x[0];
                                mentor_num = l;
                            }
                        }
                    }
                    if(min_w!=10000000)
                        wait_time += min_w;
                    mentor[mentor_num] = Math.max(mentor[mentor_num], x[0]) + x[1];
                }
                wait[i][j] = wait_time; 
            }
        }

        // greedy하게 최적의 인원 수 계산 
        int count = k;
        int[] mentor2 = new int[k+1];
        Arrays.fill(mentor2, 1);
        while(count < n){
            // 가장 차이가 큰 것
            int max_d = 0, index = 0;
            for(int i = 1; i <= k; i++){
                int dif = wait[i][mentor2[i]] - wait[i][mentor2[i]+1];
                if(dif > max_d){
                    max_d = dif;
                    index = i;
                }
            }
            
            mentor2[index]++;
            count++;
        }
        
        int answer = 0;
        for(int i = 1; i <= k; i++) answer += wait[i][mentor2[i]];            
        
        return answer;
    }
}
