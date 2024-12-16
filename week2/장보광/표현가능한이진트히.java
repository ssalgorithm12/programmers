class Solution {
    public int[] solution(long[] numbers) {
        int size = numbers.length;
        boolean[] check;
        int count, index;
        int[] answer = new int[size]; 
        int[] div = {0, 1, 3, 7, 15, 31};
        
        for(int i = 0; i < size; i++){
            long temp = numbers[i];
            index = 0;
            count = 0;
            check = new boolean[64];
            while(temp > 0){
                if(temp%2 == 1){
                    check[index] = true;
                    count++;
                }
                temp/=2;
                index++;
            }
            
            for(int x: div){
                if(!check[x]) continue;
                if(find(check, x, x+1)==count)
                    answer[i] = 1;
            }
        }
        
        return answer;
    }
    
    int find(boolean[] check, int x, int y){
        if(y==0) return 0;
        
        int result = 0;
        if(check[x]) {
            result++;
            y/=2; 
            return result + find(check, x - y, y) + find(check, x + y, y); 
        }else{
            return 0;   
        }
    }
}
