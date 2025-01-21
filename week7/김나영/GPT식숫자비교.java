package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GPT식숫자비교 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n;
    static String [][] st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new String[n][2];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            String[] ex = s.split("\\.");
            st[i][0] = ex[0];
            if (ex.length==2) st[i][1] = ex[1];
            else st[i][1] = "-1";
        }

        // 문자열 정렬
        Arrays.sort(st, (o1, o2) -> {
            if (Integer.parseInt(o1[0]) == Integer.parseInt(o2[0])) {
                return Integer.parseInt(o1[1]) - Integer.parseInt(o2[1]);
            }
            return Integer.parseInt(o1[0]) - Integer.parseInt(o2[0]);
        });

        for (int i = 0; i < n; i++) {
            if (Integer.parseInt(st[i][1]) == -1) sb.append(st[i][0]).append("\n");
            else sb.append(st[i][0]).append(".").append(st[i][1]).append("\n");
        }

        System.out.println(sb.toString());

    }
}
