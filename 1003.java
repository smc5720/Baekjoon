import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		int[][] dp = new int[2][41];

		dp[0][0] = 1;
		dp[0][1] = 0;
		dp[1][0] = 0;
		dp[1][1] = 1;

		for (int i = 2; i <= 40; i++) {
			dp[0][i] = dp[0][i - 1] + dp[0][i - 2];
			dp[1][i] = dp[1][i - 1] + dp[1][i - 2];
		}

		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			bw.write(String.valueOf(dp[0][n]) + " " + String.valueOf(dp[1][n]));
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}
}