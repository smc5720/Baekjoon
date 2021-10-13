import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		int[] T = new int[N];
		int[] P = new int[N];
		int[] dp = new int[N + 1];

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			T[n] = Integer.parseInt(st.nextToken());
			P[n] = Integer.parseInt(st.nextToken());
			dp[n] = 0;
		}

		int maxVal = 0;

		for (int n = 0; n < N; n++) {
			int fin = n + T[n];
			if (fin <= N) {
				dp[fin] = Math.max(dp[fin], dp[n] + P[n]);
				maxVal = Math.max(dp[fin], maxVal);
			}
			dp[n + 1] = Math.max(dp[n + 1], dp[n]);
		}

		bw.write(String.valueOf(maxVal));

		bw.flush();
		bw.close();
		br.close();
	}
}