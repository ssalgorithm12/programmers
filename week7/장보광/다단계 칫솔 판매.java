import java.util.*;

/**
 * [종명]
 * - 가독성 좋게 잘 만든 것 같습니다.
 * 
 */

class Solution {
    int[] parent;
    int[] income;
    Map<String, Integer> map = new HashMap<>();
    int length = 0;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        length = enroll.length; 
        parent = new int[length];
        income = new int[length];
        
        // map에 문자열(key)과 index(value)로 저장  
        for(int i = 0; i < length; i++)
            map.put(enroll[i], i);

        //부모를 인덱스 값으로 저장 
        for(int i = 0; i < length; i++){
            if(referral[i].equals("-"))
                parent[i] = -1; 
            else 
                parent[i] = map.get(referral[i]); 
        }
        
        for(int i = 0; i < seller.length; i++){
            int sell = map.get(seller[i]);
            int price = amount[i] * 100; 
            multiLevel(sell, price);
        }
        
        return income;
    }
    
    // 다단계 함수 
    void multiLevel(int sell, int price){
        /*
            - 기능: 본인의 수익을 저장하고 수익의 10%를 부모에게 전달(재귀 함수) 
            - 입력
                sell: 수익을 얻은 사람의 인덱스 
                price: 수익 
            - 출력: 없음 
        */
        
        int next = (price - price % 10) / 10;
        income[sell] += (price - next);
        if(next > 0 && parent[sell] != -1)
            multiLevel(parent[sell], next);
    }
}
