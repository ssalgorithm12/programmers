package programmers;

import java.util.PriorityQueue;

public class 호텔대실 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		solution(new String[][] {{"15:00", "17:00"},{"16:40", "18:20"},{"14:20", "15:20"},{"14:10", "19:20"},{"18:20", "21:20"}});
	}
	
	// 시간에 대한 정보들을 priorityQueue에 담으며 항상 시간 순서로 항상 정렬하게 해서 방을 제거하는 방식으로 접근 중
	
	static class Time{
		
		String start;
		String finish;
		
		public Time(String start, String finish) {
			this.start = start;
			this.finish = finish;
		}
	}
	
	static PriorityQueue<Time> startContainer;
	static PriorityQueue<Time> finishContainer;
	
    static public int solution(String[][] book_time) {
    	
    	startContainer = new PriorityQueue<Time>((o1, o2) -> (o1.start.compareTo(o2.start)));
    	finishContainer = new PriorityQueue<Time>((o1, o2) -> (o1.finish.compareTo(o2.finish)));
    	
    	for(int i=0; i<book_time.length; i++) {
    		
    		String start_time = book_time[i][0];
    		String finish_time = book_time[i][1];
    		
    		startContainer.offer(new Time(start_time, finish_time));	
    	}
    	
    	// 시작은 방 1개로 시작
    	int min_room = 1;
    	Time s = startContainer.poll();
    	finishContainer.offer(new Time(s.start, increaseTime(s.finish)));
    	
    	// startContainer안의 시간이 다 사라질때까지
    	while(!startContainer.isEmpty()) {
    		
    		Time cur = startContainer.poll();
    		
    		String c_start = cur.start;
    		String c_finish = cur.finish;
    		
    		Time fin = finishContainer.peek();
    		
    		if(c_start.compareTo(fin.finish) < 0) {
    			// 시작 시간이 끝 시간 보다 빠르다면
    			
    			min_room += 1;
    			finishContainer.offer(new Time(c_start, increaseTime(c_finish)));
    		}else {
    			finishContainer.poll();
    			finishContainer.offer(new Time(c_start, increaseTime(c_finish)));
    		}
    	}
    	
    	
//    	// 출력 테스트
//    	while(!startContainer.isEmpty()) {
//    		Time cur = startContainer.poll();
//    		
//    		System.out.println(cur.start + " " + cur.finish);
//    	}
    	
        return min_room;
    }
    
    // 10분 증가 함수
    static String increaseTime(String time) {
    	
    	String[] t = time.split(":");
    	
    	int hour = Integer.parseInt(t[0]);
    	int minute = Integer.parseInt(t[1]);
    	
    	minute += 10;
    	
    	if(minute >= 60) {
    		
    		minute -= 60;
    		hour += 1;
    	}
    	
    	return hour + ":" + minute;
    }

}
