import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args)throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];

        for(int i=0; i<N; i++){
            arr[i] = Long.parseLong(br.readLine(), 2);
        }
        
        long count = 0;

        for(int i=0; i<N-1; i++){
            for(int j=i+1; j<N; j++){

                long cbti = arr[i] ^ arr[j];

                int step = Long.bitCount(cbti);

                if(step < 3){
                    count += 1;
                }
            }
        }
        
        System.out.println(count);
    }
}
