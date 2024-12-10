package 백가은;
import java.util.*;

class Solution {
    public int solution(int[][] arr) {
        int n = arr.length;
        int a = arr[0][0];          // 완호의 근무 태도 점수
        int b = arr[0][1];          // 완호의 동료 평가 점수
        
        // 근무 태도 점수를 기준으로 내림차순으로 정렬
        // 근무 태도 점수가 같을 경우 동료 평가 점수를 기준으로 내림차순
        Arrays.sort(arr, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]) return Integer.compare(o2[0], o1[0]);
                else return Integer.compare(o2[1], o1[1]);
            }
        });
        
        // 현재까지 동료 평가 점수의 최대값 저장 배열
        int[] maxScore = new int[n];
        int max = -1;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, arr[i][1]);
            maxScore[i] = max;
        }
        
        // 인센티브 받는 사원의 점수 합만 리스트에 저장
        List<Integer> list = new ArrayList<>();
        int prev = -1;                           // 근무 태도 점수가 더 높은, 가장 가까운 사원의 인덱스
        for(int i = 0; i < n; i++) {
            // 나보다 근무 태도 점수가 높은 사원의 동료 평가 점수보다 동료 평가 점수가 낮은 경우
            if(i > 0 && prev >= 0 && arr[i][1] < maxScore[prev]) {       
                if(arr[i][0] == a && arr[i][1] == b) return -1;     // 완호의 점수와 모두 동일한 경우 -1 반환
                continue;                                           // list에 추가하지 않음
            }
            // 다음 사원의 근무 태도 점수가 더 낮아지는 경우 prev 갱신
            if(i < n - 1 && arr[i][0] != arr[i + 1][0]) prev = i;
            list.add(arr[i][0] + arr[i][1]);                        // 두 점수의 합을 list에 넣기
        }
        Collections.sort(list);         // 점수 합을 기준으로 정렬
        int rank = 1;                   // 등수
        int idx = list.size() - 1;      // 오름차순 정렬돼있으므로 뒤에서부터 탐색 시작
        int before = -1;                // 앞 사람의 점수 합
        while(idx >= 0){
            int score = list.get(idx);  // 사원의 점수 합
            if(score != before) rank = list.size() - idx;   // 이전 사원의 점수 합과 동일하지 않을 경우 rank 갱신
            if(score == a + b) return rank;                 // 완호의 등수 반환
            before = score;                                 // before 갱신
            idx--;                                          // 다음 사원으로 넘어감
        }
        return -1;
    }
}