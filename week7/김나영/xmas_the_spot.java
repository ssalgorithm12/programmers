package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class xmas_the_spot {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static char[] s, t;
    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            s = st.nextToken().toCharArray();
            t = st.nextToken().toCharArray();

            for (int j = s.length-1; j >= s.length/2; j--) {
                int m = s.length-1 - j;
                if (s[j]=='X' || s[j]=='x') {
                    if ('A'-t[j] < -25) sb.append(Character.toUpperCase(t[j]));
                    else sb.append(t[j]);
                    break;
                } else if (s[m]=='X' || s[m]=='x') {
                    if ('A'-t[m] < -25) sb.append(Character.toUpperCase(t[m]));
                    else sb.append(t[m]);
                    break;
                }
            }
        }
        System.out.println(sb.toString());
    }
}
