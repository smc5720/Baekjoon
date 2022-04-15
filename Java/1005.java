import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static int N, K, T;
	public static int[] dp, d;
	public static Method[] method;

	public static class Method {
		ArrayList<Integer> need;
		int build;

		public Method(int build) {
			need = new ArrayList<Integer>();
			this.build = build;
		}

		public void add(int n) {
			need.add(n);
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine(), " ");

			dp = new int[N + 1];
			d = new int[N + 1];
			method = new Method[N + 1];

			for (int i = 1; i <= N; i++) {
				d[i] = Integer.parseInt(st.nextToken());
				dp[i] = -1;
				method[i] = new Method(i);
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");

				int need = Integer.parseInt(st.nextToken());
				int build = Integer.parseInt(st.nextToken());

				method[build].add(need);
			}

			int W = Integer.parseInt(br.readLine());
			bw.write(recursive(W) + "\n");
		}

		br.close();
		bw.close();
	}

	public static int recursive(int n) {
		if (dp[n] != -1) {
			return dp[n];
		}

		int max = 0;

		for (int need : method[n].need) {
			max = Math.max(max, recursive(need));
		}

		dp[n] = d[n] + max;
		return dp[n];
	}
}