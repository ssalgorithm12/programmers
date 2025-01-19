/**
 * [종명]
 * - 뭔가 간결해보이는데 이해하기는 힘든 느낌이에요
 * 
 */

class Solution {
    public int solution(String[] board) {
        int answer = 1;
        char[][] arr = new char[3][3]; // 틱택토 
        char[] check = {'O', 'X'}; 
        int[] sum = new int[2]; // O, X의 개수  

        // 스트링을 char형으로 변환, O, X의 개수 확인  
        for(int i = 0; i < 3; i++){
            arr[i] = board[i].toCharArray();
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 2; k++){
                    if(arr[i][j]==check[k]) sum[k]++;
                }
            }
        }

        // 완성된 줄의 개수 저장 
        int[] win_rate = new int[2];

        // 한 줄 완성된 개수 저장 
        for(int j = 0; j < 2; j++){
            for(int i = 0; i < 3; i++){
                if(arr[0][i]==check[j]&&arr[1][i]==check[j]&&arr[2][i]==check[j])
                    win_rate[j]++;
                if(arr[i][0]==check[j]&&arr[i][1]==check[j]&&arr[i][2]==check[j])
                    win_rate[j]++;
            }
            if(arr[0][0]==check[j]&&arr[1][1]==check[j]&&arr[2][2]==check[j])
                win_rate[j]++;
            if(arr[2][0]==check[j]&&arr[1][1]==check[j]&&arr[0][2]==check[j])
                win_rate[j]++;
        }

        // 불 가능한 조건 판별 
        if(win_rate[0]!=0&&win_rate[1]!=0)
            answer = 0;
        else if(win_rate[0]!=0&&sum[0]==sum[1])
            answer = 0;
        else if(win_rate[1]!=0&&sum[1]+1==sum[0])
            answer = 0; 
        else if(sum[0] != sum[1] && sum[0] != sum[1]+1)
            answer = 0;
        
        return answer;
    }
}
