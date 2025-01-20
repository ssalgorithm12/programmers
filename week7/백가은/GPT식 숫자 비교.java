import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());

        // 왼쪽 수 별로 소수점 오른쪽 수를 자연수로 저장할 리스트 배열
        List<Integer>[] list = new List[101];
        for(int i = 0; i <= 100; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i = 0; i < n; i++) {
            int[] nums = parsing(br.readLine());
            list[nums[0]].add(nums[1]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= 100; i++) {
            if(list[i].size() == 0) continue;
            Collections.sort(list[i]);
            for(int a : list[i]) {
                sb.append(i);
                if(a != -1) sb.append("." + a);
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }

    static int[] parsing(String num) {
        StringTokenizer st = new StringTokenizer(num, ".");
        int[] res = new int[2];
        int idx = 0;
        while(st.hasMoreTokens()) {
            res[idx++] = Integer.parseInt(st.nextToken());
        }
        if(idx == 1) res[1] = -1;
        return res;
    }
}
