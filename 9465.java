import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		Integer T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			Integer n = Integer.parseInt(br.readLine());
			int[][] map = new int[2][n];
			int[][] dp = new int[2][n];

			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dp[0][0] = map[0][0];
			dp[1][0] = map[1][0];

			for (int x = 1; x < n; x++) {
				if (x == 1) {
					dp[0][x] = map[0][x] + dp[1][x - 1];
					dp[1][x] = map[1][x] + dp[0][x - 1];
				} else {
					dp[0][x] = map[0][x] + Math.max(dp[1][x - 1], dp[1][x - 2]);
					dp[1][x] = map[1][x] + Math.max(dp[0][x - 1], dp[0][x - 2]);
				}
			}
			
			Integer result = Math.max(dp[0][n - 1], dp[1][n - 1]);
			bw.write(result.toString());
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}
}