// 시간: 0.114ms
import java.io.*;
import java.util.*;

public class Main {
    public static class ver implements Comparable<ver>{
        String version;
        int index;

        public ver(String version, int index){
            this.version = version;
            this.index = index;
        }

        @Override
        public int compareTo(ver o){
            return index - o.index;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<ver> arr = new ArrayList<>();

        for(int i = 0; i < n; i++){
            String s = br.readLine();
            String[] s2 = s.split("\\.");
            int a = 0, b = 0;
            if(s2.length > 1){
                a = Integer.parseInt(s2[0]);
                b = Integer.parseInt(s2[1])+1;
            }else{
                a = Integer.parseInt(s);
            }
            arr.add(new ver(s, a*10000+b));
        }

        Collections.sort(arr);

        for(ver x: arr){
            System.out.println(x.version);
        }
    }
}
