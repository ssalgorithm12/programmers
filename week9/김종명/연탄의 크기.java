import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args)throws IOException {
        
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 1부터 100까지의 소수
        int[] prime = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73
                               , 79, 83, 89, 91, 97};

        // 최대 값 저장 변수 
        int max_count = 0;

        for(int i=0; i<prime.length; i++){  
            int count = 0;
            for(int j=0; j<N; j++){

                if(arr[j] % prime[i] == 0){
                    count += 1;
                }
                
            }
            if(count > max_count){
                max_count = count;
            }
        }

        // 출력
        System.out.println(max_count);
    }
}
