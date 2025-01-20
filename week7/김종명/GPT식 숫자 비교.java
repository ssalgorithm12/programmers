import java.io.*;
import java.util.*;

public class Main {

    // PriorityQueue에 정렬 기준을 세우기 위한 class 선언
    static class Num implements Comparable<Num>{

        String num;

        public Num(String num){
            this.num = num;
        }

        @Override
        public int compareTo(Num o){

            // 문자열 . 기준으로 자르기
            String[] prev = this.num.split("[.]");
            String[] next = o.num.split("[.]");

            // 앞 숫자 비교
            if(Integer.parseInt(prev[0]) < Integer.parseInt(next[0])){

                return -1;
                
            }else if(Integer.parseInt(prev[0]) > Integer.parseInt(next[0])){

                return 1;
                
            }else{
                // 앞 숫자가 같다면..
                
                // . 뒤에 숫자가 있는지 비교
                if(prev.length > next.length){
                    return 1;
                }else if(prev.length < next.length){
                    return -1;
                }else{
                    // 둘다 . 뒤에 숫자가 있거나 없다면...

                    // 숫자가 없을때
                    if(prev.length == 1){
                        return 0;
                    }

                    // . 뒤에 숫자 크기 비교
                    if(Integer.parseInt(prev[1]) > Integer.parseInt(next[1])){
                        return 1;
                    }else if(Integer.parseInt(prev[1]) < Integer.parseInt(next[1])){
                        return -1;
                    }else {
                        return 0;
                    }
                }
            }
        }
    }
    
    public static void main(String[] args)throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        PriorityQueue<Num> container = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            container.offer(new Num(br.readLine()));
        }

        while(!container.isEmpty()){
            System.out.println(container.poll().num);
        }
        
    }
}