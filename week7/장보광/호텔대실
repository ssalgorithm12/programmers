class Solution {
    public static int time2num(String time) {
        // "HH:MM" 형식의 문자열을 분 단위로 변환
        int hours = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(3, 5));
        return hours * 60 + minutes;
    }

    public static int solution(String[][] book_time) {
        // 배열로 시간의 예약 횟수를 저장 (1440분 = 24시간)
        int[] timeArray = new int[1441]; // 1440 + 1 크기 배열 (종료 시간 포함)

        for (String[] book : book_time) {
            int startTime = time2num(book[0]);
            int endTime = time2num(book[1]);

            // 시작 시간부터 예약 종료 시간까지 1 증가
            for (int t = startTime; t < endTime; t++) 
                timeArray[t]++;

            // 청소 시간 처리 (종료 시간 이후 10분 동안만 체크)
            for (int t = endTime; t < endTime + 10 && t < 1441; t++) 
                timeArray[t]++;
        }

        // 배열에서 최대값 찾기
        int maxCount = 0;
        for (int count : timeArray) 
            maxCount = Math.max(maxCount, count);
    

        return maxCount;
    }
}
