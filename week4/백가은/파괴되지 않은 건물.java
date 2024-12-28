import java.util.*;

class Solution {

    public int solution(int[][] board, int[][] skill) {

        int h = board.length;
        int w = board[0].length;

        int[][] sum = new int[h + 1][w + 1];

        // 1. 누적합 배열에 skill 기록
        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = type == 1 ? -skill[i][5] : skill[i][5];

            sum[r1][c1] += degree;
            sum[r1][c2 + 1] -= degree;
            sum[r2 + 1][c1] -= degree;
            sum[r2 + 1][c2 + 1] += degree;
        }

        // 2. 누적합 계산
        for (int i = 0; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                sum[i][j] += sum[i][j - 1];
            }
        }

        for (int i = 1; i <= h; i++) {
            for (int j = 0; j <= w; j++) {
                sum[i][j] += sum[i - 1][j];
            }
        }

        // 3. board에 반영
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                board[i][j] += sum[i][j];
            }
        }

        // 4. 남아 있는 건물 개수 세기
        int cnt = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i][j] > 0) cnt++;
            }
        }
        return cnt;
    }
}
