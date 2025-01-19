import java.util.*;

/*
 * 피드백
 * 
 * [가은]
 * - 시간 변경 -> 메서드로 만들면 가독성 높아질듯
 * 
 * [종명]
 * - 위와 같은 생각
 * 
 */

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        // book_time은 대실 시작 시간, pq는 종료 시간으로 오름차순
        Arrays.sort(book_time, (x, y) -> x[0].compareTo(y[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);

        for (String[] book: book_time) {
            String[] starts = book[0].split(":");
            String[] ends = book[1].split(":");

            // 분으로 시간 변경
            // 종료 시간은 +10
            int start = Integer.parseInt(starts[0]) * 60 + Integer.parseInt(starts[1]);
            int end = Integer.parseInt(ends[0]) * 60 + Integer.parseInt(ends[1]) + 10;

            // 만약 pq가 비어있으면 방 개수 ++
            if (pq.isEmpty()) {
                answer++;
                pq.offer(new int[]{start, end});
                continue;
            }

            int[] p = pq.poll();
            int p_End = p[1];

            // 만약 현재 대실 시작 시간이 p_End보다 나중이라면 offer
            // 아니라면 방 개수 추가 후 p와 book을 offer(두 예약을 다시 pq에 추가)
            if (start >= p_End) {
                pq.offer(new int[]{start, end});
            } else {
                answer++;
                pq.offer(new int[]{start, end});
                pq.offer(p);
            }
        }
        return answer;    }
}