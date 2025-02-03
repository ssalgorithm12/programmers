class Solution {
  public int[] solution(int[] numbers) {
      
      int size = numbers.length;
      int[] answer = new int[size];       // 정답을 저장할 배열
      int[] DP = new int[size];           // index 값을 저장할 DP
      
      int max_num = numbers[size - 1];    // 최대값을 저장할 변수
      DP[size - 1] = size - 1;            // DP 초기값
      answer[size - 1] = -1;              // 정답 배열 초기값
      
      for(int i=size-2; i>=0; i--){
          // 해당 숫자가 최대값보다 크거나 같다면
          // 최대값 갱신하고 해당 DP 값 현재 인덱스 값으로 선택
          if(numbers[i] >= max_num){
              answer[i] = -1;
              max_num = numbers[i];
              DP[i] = i;
              continue;
          }else{
              
              DP[i] = findBig(numbers[i], i+1, numbers, DP);
              answer[i] = numbers[DP[i]];
          }
      }
      
      return answer;
  }
  
  public int findBig(int num, int index, int[] numbers, int[] DP){
      
      if(num >= numbers[index]){
          
          return findBig(num, DP[index], numbers, DP);
      }
      
      return index;
  }
}